package app.windows;

import java.util.List;

public interface ErrorPublisher {

	/**
	 * Will publish the message passed as parameter
	 * 
	 * @param message
	 * 			Message to be published.
	 */
	public void publish(String message);
	
	public void publish(List<String> chunks);
}
