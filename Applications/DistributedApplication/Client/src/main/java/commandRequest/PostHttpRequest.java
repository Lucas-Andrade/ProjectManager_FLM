package commandRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;


/**
 * Class responsible for sending the HTTP request using Post method and to
 * receive and treat the result.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 06/02/2015
 */
public class PostHttpRequest extends HttpRequest{


	public static OutputStreamWriter writer;
	public static HttpURLConnection connection = null;
	public String requestURL;
	public String path;
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
	      
	    try {
	    	HttpURLConnection connection = super.sendRequest();
	    	
			connection.setDoInput(true); // true indicates the server returns response
			connection.setDoOutput(true);// true indicates POST request
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
		
			OutputStream writer = connection.getOutputStream();
			writer.write(parameters.getBytes());
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
	  * Receive and treat the response to the HTTP request using POST method.
	  */
	    public String receiveRequest() throws IOException {
	    	//TODO
	    	//Get Response    
		      InputStream is = connection.getInputStream();
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		      String line;
		      StringBuffer response = new StringBuffer(); 
		      while((line = rd.readLine()) != null) {
			        response.append(line);
			        response.append('\r');
		      }
		      rd.close();
		      return response.toString();
	}
}
