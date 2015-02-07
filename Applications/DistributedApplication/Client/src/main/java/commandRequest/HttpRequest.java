package commandRequest;

import java.io.IOException;
import java.net.HttpURLConnection;

public interface HttpRequest {
 
	/**
     * Makes an HTTP request using GET, PUT, POST or DELETE method to the specified URL.
     *
     * @throws IOException
     *             thrown if any I/O error occurred
     */
	 public abstract HttpURLConnection sendRequest() throws IOException;
	 
	 
	 public abstract String receiveRequest() throws IOException;
}
