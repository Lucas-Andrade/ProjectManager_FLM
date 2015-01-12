package app.repository;

import app.elements.AppElement;
import app.elements.User;
import app.elements.UserInterface;

/**
 * The interface to be implemented by all {@link User}s {@link Repository}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/20
 */
public interface UserRepository extends Repository<AppElement>
{
	/**
	 * Gets the user with the given loginName, or {@code null} if none exists
	 * 
	 * @param loginName
	 *            the user identifier
	 * @return the instance with the given identifier
	 */
	public UserInterface getUserByUsername(String loginName);

	/**
	 * Method that adds a User {@code User} to the repository.
	 * 
	 * @param user
	 *            The User to add.
	 * @return True if successful, False if not.
	 */
	public boolean addUser(User user);

	/**
	 * Checks if the password corresponds to the User with the username.
	 * 
	 * @param username
	 *            The User's username.
	 * @param userPassword
	 *            The password to be checked.
	 * @return True if it's the User's password, False if not.
	 */
	public boolean isPasswordCorrectForUser(String username, String userPassword);

	/**
	 * Adds to the repository an {@code Admin} {@code User}.
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	boolean addAdmin(String username, String password);

}
