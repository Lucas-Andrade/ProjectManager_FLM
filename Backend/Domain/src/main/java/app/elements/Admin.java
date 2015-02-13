package app.elements;

/**
 * Class that represents the {@code User} created by the time the
 * {@link InMemoryUserRepo} is instantiated. All {@code Admin} instances are
 * thread-safe.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class Admin extends User {

	/**
	 * The constructor of {@code Admin}.
	 */
	public Admin(String username, String password) {
		super(username, password, "admin_" + username + "@administration.com",
				"Administator");
	}
}
