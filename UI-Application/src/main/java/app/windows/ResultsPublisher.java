package app.windows;

import java.util.List;

import app.AppElement;

public interface ResultsPublisher {
	
	/**
	 * Publishes information about each element of the array of
	 * {@code AppElement}s.
	 * 
	 * @param appElements
	 *            The {@code AppElement} objects about which information will be
	 *            published.
	 */
	public void publish(AppElement[] appElements);
	
	public void publish(List<String> chunks);
}
