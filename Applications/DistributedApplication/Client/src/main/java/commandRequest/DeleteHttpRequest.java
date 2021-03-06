package commandRequest;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Class responsible for sending the HTTP request using Delete method and to
 * receive and treat the result.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 06/02/2015
 */
public class DeleteHttpRequest extends HttpRequest{

	public DeleteHttpRequest(String requestURL, String path) {
		super(requestURL, path);
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
		  
			connection = super.sendRequest();
			connection.setUseCaches(true );
			connection.setDoInput(true); // true if we want to read server's response
			connection.setDoOutput(true); // TRUE indicates this is a DELETE request
			connection.setRequestMethod("DELETE");
			connection.connect();

			return connection;
	
	 }
}

