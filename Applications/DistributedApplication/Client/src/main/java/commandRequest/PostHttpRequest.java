package commandRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


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
	public String requestURL;
	public String path;
	
	public PostHttpRequest(String requestURL, String path) {
		this.requestURL = requestURL;
		this.path = path;
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
	public HttpURLConnection sendRequest() throws IOException {
		URL url;
	      
	    try {
			url = new URL(requestURL);
			connection = (HttpURLConnection) url.openConnection();
			
			connection.setDoInput(true); // true indicates the server returns response
			connection.setDoOutput(true);// true indicates POST request
			connection.setRequestProperty("Content-Type", "application/Json" );
			connection.setRequestMethod("POST");
		
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(path);
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
	
	
	    public String receiveRequest() throws IOException {
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