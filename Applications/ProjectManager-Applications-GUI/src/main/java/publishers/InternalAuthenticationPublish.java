package publishers;

/**
 * A publisher class with the express purpose of not publishing.
 * Instead, the {@code Authentication} class is updated.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 05/02/2015
 *
 */
public abstract class InternalAuthenticationPublish extends PublishWithLoadingDialog implements ResultsPublisher{

	/**
	 * Will not publish.
	 */
	@Override
	public void publish(String element) {
	}

	public abstract void authenticate(String name, String password);

}
