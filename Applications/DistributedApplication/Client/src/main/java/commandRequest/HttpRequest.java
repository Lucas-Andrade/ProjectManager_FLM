package commandRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class HttpRequest {
 
	private String requestURL;
	private String path;
	private HttpURLConnection connection;
	private String result;


	public HttpRequest (String requestURL, String path){
		this.requestURL = requestURL;
		this.path = path;
	}
	
	
	
	/**
     * Makes an HTTP request using GET, PUT, POST or DELETE method to the specified URL.
     *
     * @throws IOException
     *             thrown if any I/O error occurred
     */
	 public HttpURLConnection sendRequest() throws IOException{
		 
		 URL url = new URL(requestURL+path);
		
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Content-Type", "application/Json" );	
				
		return connection;
	 }
	 
	 
	 /**
	  * Receive the response to the HTTP request using GET method to the specified URL.
	  * @return
	  * @throws IOException
	 * @throws HttpConnectionException 
	  */
	 public String receiveRequest() throws IOException, HttpConnectionException{
		
		InputStream inputStream = connection.getInputStream();
		if(	connection.getResponseCode() == 10000){
			int numBytes = Integer.parseInt(connection.getHeaderField("Content-Length"));
			byte[] bytes = new byte [numBytes];
			inputStream.read(bytes);
			
			result = new String (bytes);
		} else{
			throw new HttpConnectionException(connection.getResponseMessage());	
		}
				 
		 return result; 

	 }
}
