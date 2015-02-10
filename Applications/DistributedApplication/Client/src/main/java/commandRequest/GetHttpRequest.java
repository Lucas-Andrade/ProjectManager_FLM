package commandRequest;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Class responsible for sending the HTTP request using Get method and to
 * receive and treat the result.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 06/02/2015
 */
public class GetHttpRequest  extends HttpRequest{


	
	private HttpURLConnection connection;

	public GetHttpRequest(String requestURL, String path) {
		super(requestURL, path);
	}

	/**
     * Makes an HTTP request using GET method to the specified URL.
     *
     * @param requestURL
     *            the URL of the remote server
     * @return An HttpURLConnection object
     * @throws IOException
     *             thrown if any I/O error occurred
     */
	 public HttpURLConnection sendRequest()
	            throws IOException {
		  try { 
		 	connection = super.sendRequest();
		  
			connection.setUseCaches(true );
			connection.setDoInput(true); // true if we want to read server's response
			connection.setDoOutput(false); // false indicates this is a GET request
			connection.setRequestMethod("GET");

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
	
}
