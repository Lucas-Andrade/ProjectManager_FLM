package commandRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public abstract class HttpRequest {
 
	private String requestURL;
	private String path;
	private HttpURLConnection connection;


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
	  */
	 public String receiveRequest() throws IOException{
		
		 InputStream inputStream = connection.getInputStream();
		int numBytes = Integer.parseInt(connection.getHeaderField("Content-Length"));
		byte[] bytes = new byte [numBytes];
		inputStream.read(bytes);
		
		String result = new String (bytes);
 
		 
		 return result;
		 
	 }
}
