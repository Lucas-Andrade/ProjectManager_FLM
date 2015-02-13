package commandRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Class responsible for sending the HTTP request using Patch method and to
 * receive and treat the result.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 06/02/2015
 */
public class PatchHttpRequest extends HttpRequest{


	public static OutputStreamWriter writer;
	public static HttpURLConnectionForPatch connection = null;
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

		URL url = new URL(requestURL + path);

		connection = (HttpURLConnectionForPatch) url.openConnection();
		connection.setRequestProperty("Accept", "application/json");

//    	System.out.println("is connection null? " + (connection == null));
//    	System.out.println("ele chegou aqui1");
		connection.setDoInput(true); // true indicates the server returns response
//    	System.out.println("ele chegou aqui2");
		connection.setDoOutput(true);// true indicates PUT request
//		System.out.println("ele chegou aqui3");
		connection.setRequestMethod("PUT");
//		System.out.println("ele chegou aqui4");
		connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
//		System.out.println("ele chegou aqui5");
		
		OutputStream writer = connection.getOutputStream();
//		System.out.println("is writer null? " + (writer == null));
		writer.write(parameters.getBytes());
		writer.flush();
		writer.close();

		return connection;   
	}
	
	private abstract class HttpURLConnectionForPatch extends HttpURLConnection {

		protected HttpURLConnectionForPatch(URL u) {
			super(u);
		}
		
		@Override
		public void setRequestMethod(String method) throws ProtocolException {
			if (super.connected) {
	            throw new ProtocolException("Can't reset method: already connected");
	        }
			if(!"PATCH".equals(method)){
				super.method = method;
			}
			throw new ProtocolException("Invalid HTTP method: " + method);
		}
		
	}
}
