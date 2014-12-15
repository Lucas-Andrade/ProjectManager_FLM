package app.elements;

import app.AppProjectManager;
import app.commands.BasePostCommand;

/**
 * Class that represents the first {@code User} of the {@code AppProjectManager}
 * . This {@code User} is created by the time the {@link InMemoryUserRepo} is
 * instantiated in the {@link AppProjectManager} ({@see
 * InMemoryUserRepo#addAdmin(String, String)}). {@code AppProjectManager} needs
 * at least one {@code User} in the {@link UserRepository} or it won't be
 * possible to use {@link BasePostCommand}s.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class Admin extends User
{

	/**
	 * The constructor of {@code Admin}.
	 */
	public Admin(String username, String password)
	{
		super(username, password, "admin_" + username + "@administration.com",
				"Administator");
	}
}
