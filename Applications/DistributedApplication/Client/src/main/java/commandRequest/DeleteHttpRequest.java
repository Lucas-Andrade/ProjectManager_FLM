package commandRequest;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class responsible for sending the HTTP request using Delete method and to
 * receive and treat the result.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 06/02/2015
 */
public class DeleteHttpRequest implements HttpRequest{
	
	public static OutputStreamWriter writer;
	public static HttpURLConnection connection = null;
	public String requestURL;
	public String path;
	
	public DeleteHttpRequest(String requestURL, String path) {
		this.requestURL = requestURL;
		this.path = path;
	}

	/**
     * Makes an HTTP request using DELETE method to the specified URL.
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
		    URL url = new URL(requestURL);
			//OutputStream output = null;
			connection = (HttpURLConnection) url.openConnection();
			
			
			connection.setUseCaches(true );
			connection.setDoInput(true); // true if we want to read server's response
			connection.setDoOutput(true); // TRUE indicates this is a DELETE request
			connection.setRequestProperty("Content-Type", "application/Json" );
			connection.setRequestMethod("DELETE");
			connection.connect();

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
	  * Receive and treat the response to the HTTP request using DELETE method.
	  *
	  */
	@Override
	public String receiveRequest() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
