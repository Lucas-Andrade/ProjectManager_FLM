package app.elements;


/**
 * Class that represents the {@code User} created by the time the method
 * {@link InMemoryUserRepo#removeAll()} is used. {@code AppProjectManager} needs
 * at least one {@code User} in the {@link UserRepository} or it won't be
 * possible to use {@link BaseCommandUserAuthentication}s.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class ImmutableAdmin extends Admin {

	/**
	 * The constructor of {@code ImmutableAdmin}.
	 */
	public ImmutableAdmin(){
		super("admin", "admin");
	}

}
