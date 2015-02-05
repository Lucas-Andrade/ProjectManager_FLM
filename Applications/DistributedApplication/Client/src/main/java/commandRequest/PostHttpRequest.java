package commandRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;


/**
 * This {@code Command} allows to construct a new {@code User} and add it to the
 * repository.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class PostHttpRequest implements HttpRequest{


	public static OutputStreamWriter writer;
	public static HttpURLConnection connection = null;
	public PostHttpRequest() {
	}

	/**
	 * Method with the responsibility to send a HTTP request using POST method
	 * to the specified URL
	 * 
	 * @param requestURL the URL of the remote server
	 * @param params
	 * @param path
	 * @return An HttPURLConnectionobeject
	 * @throws IOException
	 */
	public static HttpURLConnection sendPostRequest(String requestURL,
			Map<String, String> params, String path) throws IOException {
		URL url;
	      
	    try {
			url = new URL(requestURL);
			connection = (HttpURLConnection) url.openConnection();
			
			connection.setDoInput(true); // true indicates the server returns response
			connection.setDoOutput(true);// true indicates POST request
			connection.setRequestMethod("POST");
	
			StringBuffer requestParams = new StringBuffer();
		
			Iterator<String> paramIterator = params.keySet().iterator(); 	// creates the params string, encode them using URLEncoder
			while (paramIterator.hasNext()) {
				String key = paramIterator.next();
				String value = params.get(key);
	
				requestParams.append(URLEncoder.encode(key, "UTF-8"));
				requestParams.append("=").append(URLEncoder.encode(value, "UTF-8"));
				requestParams.append("&");
			}
			
			requestParams.deleteCharAt(requestParams.length() - 1);  //Delete last &. 
	
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(path + requestParams.toString());
			writer.flush();
			writer.close();
	
			return connection;

			  
		
		    } catch (Exception e) {
		
		      e.printStackTrace();
		      return null;
		
		    } finally {
		
		      if(connection != null) {
		        connection.disconnect(); 
		      }
		    }
	}
	
	
	    public static String receivePostRequest() throws IOException {
	    	//TODO
	    	//Get Response    
		      InputStream is = connection.getInputStream();
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		      String line;
		      StringBuffer response = new StringBuffer(); 
		      while((line = rd.readLine()) != null) {
			        response.append(line);
			        response.append('\r');
		      }
		      rd.close();
		      return response.toString();
	}
}