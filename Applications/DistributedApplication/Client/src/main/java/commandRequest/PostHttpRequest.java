package commandRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Class responsible for sending the HTTP request using Post method and to
 * receive and treat the result.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 06/02/2015
 */
public class PostHttpRequest implements HttpRequest{


	public static OutputStreamWriter writer;
	public static HttpURLConnection connection = null;
	
	

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
	public HttpURLConnection sendRequest(String requestURL, String path) throws IOException {
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
	
	 /**
	  * Receive and treat the response to the HTTP request using POST method.
	  */
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