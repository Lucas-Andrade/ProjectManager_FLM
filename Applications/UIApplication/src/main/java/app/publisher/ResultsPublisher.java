package app.publisher;

import java.util.List;

import app.AppElement;

/**
 * Interface of all publishers that publish information about the expected
 * results (when it does not occur any errors of any kind).
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
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
	
	/**
	 * Will publish a String from the {@code List}. This overload of the
	 * {@code publish} is used in the method {@code SwingWorkerCommand#process(List)},
	 * to publish information mid computing in background.
	 * 
	 * @param chunks
	 * The {@code List} where the information to publish is saved.
	 */
	public void publish(List<String> chunks);
}
