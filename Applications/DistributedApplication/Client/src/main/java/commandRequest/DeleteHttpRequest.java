package commandRequest;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeleteHttpRequest implements HttpRequest{
	
	public static OutputStreamWriter writer;

	public DeleteHttpRequest() {}

	/**
     * Makes an HTTP request using DELETE method to the specified URL.
     *
     * @param requestURL
     *            the URL of the remote server
     * @return An HttpURLConnection object
     * @throws IOException
     *             thrown if any I/O error occurred
     */
	 public static HttpURLConnection sendGetRequest(String requestURL, String path)
	            throws IOException {
		 
		    URL url = new URL(requestURL);
			//OutputStream output = null;
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			
			connection.setUseCaches(true );
			connection.setDoInput(true); // true if we want to read server's response
			connection.setDoOutput(false); // false indicates this is a DELETE request
			connection.setRequestMethod("DELETE");

			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(path);
			writer.flush();
			writer.close();

			return connection;
	 }
	
}

