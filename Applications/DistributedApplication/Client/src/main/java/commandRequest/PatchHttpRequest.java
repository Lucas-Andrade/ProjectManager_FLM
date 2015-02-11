package commandRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

/**
 * Class responsible for sending the HTTP request using Patch method and to
 * receive and treat the result.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 06/02/2015
 */
public class PatchHttpRequest extends HttpRequest{


	public static OutputStreamWriter writer;
	public static HttpURLConnection connection = null;
	public String requestURL;
	public String path;
	private String parameters;
	
	public PatchHttpRequest(String requestURL, String path, String parameters) {
		super(requestURL, path);
		this.parameters = parameters;
	}

	
	
	/**
	 * Method with the responsibility to send a HTTP request using PUT method
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
			connection.setDoOutput(true);// true indicates PUT request
			connection.setRequestMethod("PATCH");
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			
			OutputStream writer = connection.getOutputStream();
			writer.write(parameters.getBytes());
			writer.flush();
			writer.close();
	
			return connection;   
	}
}
