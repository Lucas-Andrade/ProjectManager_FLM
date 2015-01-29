package app.elements;

/**
 * Class that represents the {@code User} created by the time the method
 * {@link InMemoryUserRepo#addAdmin()} is used. {@code AppProjectManager} needs
 * at least one {@code User} in the {@link UserRepository} or it won't be
 * possible to use {@link BaseCommandUserAuthentication}s.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class Admin extends User{

	/**
	 * The constructor of {@code Admin}.
	 */
	public Admin(String username, String password){
		super(username, password, "admin_" + username + "@administration.com",
				"Administator");
	}
}
