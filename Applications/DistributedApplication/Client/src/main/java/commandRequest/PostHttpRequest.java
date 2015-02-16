package commandRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;


/**
 * Class responsible for sending the HTTP request using Post method and to
 * receive and treat the result.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 06/02/2015
 */
public class PostHttpRequest extends HttpRequest{

	private String parameters;
	
	public PostHttpRequest(String requestURL, String path, String parameters) {
		super(requestURL,path);
		this.parameters = parameters;
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
	     
	    	connection = super.sendRequest();
	    	
			connection.setDoInput(true); // true indicates the server returns response
			connection.setDoOutput(true);// true indicates POST request
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		
			OutputStream writer = connection.getOutputStream();
			writer.write(parameters.getBytes("UTF-8"));
			writer.flush();
			writer.close();
	
			return connection;

	}
}