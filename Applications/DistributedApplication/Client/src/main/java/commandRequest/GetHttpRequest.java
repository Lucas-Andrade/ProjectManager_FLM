package commandRequest;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * This {@code Command} allows to get all users from a repository.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class GetHttpRequest  implements HttpRequest{

	public static OutputStreamWriter writer;

	public GetHttpRequest() {}

	/**
     * Makes an HTTP request using GET method to the specified URL.
     *
     * @param requestURL
     *            the URL of the remote server
     * @return An HttpURLConnection object
     * @throws IOException
     *             thrown if any I/O error occurred
     */
	 public HttpURLConnection sendRequest(String requestURL, String path)
	            throws IOException {
		 
		    URL url = new URL(requestURL);
			//OutputStream output = null;
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			
			connection.setUseCaches(true );
			connection.setDoInput(true); // true if we want to read server's response
			connection.setDoOutput(false); // false indicates this is a GET request
			connection.setRequestProperty("Content-Type", "application/Json" );
			connection.setRequestMethod("GET");

			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(URLEncoder.encode(path, "UTF-8"));
			writer.flush();
			writer.close();

			return connection;
	 }

	@Override
	public String receiveRequest() throws IOException {
		
		
		return null;
	}
	
}
