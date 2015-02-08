package commandRequest;

import java.io.IOException;
import java.net.HttpURLConnection;

public interface HttpRequest {
 
	/**
     * Makes an HTTP request using GET, PUT, POST or DELETE method to the specified URL.
     *
     * @param requestURL
     *            the URL of the remote server
     * @return An HttpURLConnection object
     * @throws IOException
     *             thrown if any I/O error occurred
     */
	 public abstract HttpURLConnection sendRequest(String requestURL, String path) throws IOException;
	 
	 
	 public abstract String receiveRequest() throws IOException;
}
