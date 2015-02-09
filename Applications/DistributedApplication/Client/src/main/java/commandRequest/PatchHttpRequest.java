package commandRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class responsible for sending the HTTP request using Patch method and to
 * receive and treat the result.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 06/02/2015
 */
public class PatchHttpRequest implements HttpRequest{


	public static OutputStreamWriter writer;
	public static HttpURLConnection connection = null;
	
	public PatchHttpRequest() {
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
	public HttpURLConnection sendRequest(String requestURL, String path) throws IOException {
		URL url;
	      
	    try {
	    	url = new URL(requestURL);
	    	
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true); // true indicates the server returns response
			connection.setDoOutput(true);// true indicates PUT request
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Content-Type",  "application/json");
			
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(path);
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
	  * Receive and treat the response to the HTTP request using PATCH method.
	  *
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
