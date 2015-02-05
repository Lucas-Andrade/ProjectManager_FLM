package swingApp.app.windows;


/**
 * Abstract class of all the {@code Publisher} classes that will publish
 * information about an encountered error. This allows for the errors to be 
 * presented in a different manner than the results, and will present
 * a {@code LoadingDialog} while lengthy work in background occurs.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public abstract class ErrorPublisherWithLoadingDialog extends PublishWithLoadingDialog implements ErrorPublisher{
	
}
