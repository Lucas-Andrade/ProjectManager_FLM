package app.windows;

/**
 * Interface of all the {@code Publisher} classes that will publish
 * information about an encountered error. This allows for the errors to be 
 * presented in a different manner than the results.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public interface ErrorPublisher {

	/**
	 * Will publish the message passed as parameter
	 * 
	 * @param message
	 * 			Message to be published.
	 */
	public void publish(String message);
}
