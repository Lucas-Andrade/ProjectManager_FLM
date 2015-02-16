package publishers;

/**
 * Interface of all the {@code Publisher} classes that will publish
 * information about an encountered error. This allows for the errors to be 
 * presented in a different manner than the results.
 * 
 * It also allows to publish a message, received in a {@code String} in JSON
 * fomat. The {@code String} should be as follows:
 * 
 * {"Message": "This will be published in an ErrorDialog"}
 * 
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

	/**
	 * Will publish the message passed as parameter. This {@code String}
	 * should be in JSON format, which should have a single element named
	 * "Message". The String that follows that property is published in the 
	 * {@code ErrodDialog}.
	 * 
	 * @param message
	 * 			String in JSON format.
	 */
	public void publishJsonFormatString(String message);
}
