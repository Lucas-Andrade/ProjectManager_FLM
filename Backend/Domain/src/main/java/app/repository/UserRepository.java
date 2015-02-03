package app.repository;

import app.AppElement;
import app.elements.User;
import app.elements.IUser;
import app.elements.mutable.UserCreationDescriptor;

/**
 * The interface to be implemented by all {@link User}s {@link Repository}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/20
 */
public interface UserRepository extends Repository<AppElement> {
	/**
	 * Gets the user with the given loginName, or {@code null} if none exists
	 * 
	 * @param loginName
	 *            the user identifier
	 * @return the instance with the given identifier
	 */
	public IUser getUserByUsername(String loginName);

	/**
	 * Method that creates and adds a {@code User} to the repository using an
	 * {@code UserCreationDescriptor}.
	 * 
	 * @param userCreationDescriptor
	 *            The {@code UserCreationDescriptor} for creating the
	 *            {@code User} to be added.
	 * @return True if successful, False if not.
	 */
	public boolean addUser(UserCreationDescriptor userCreationDescriptor);

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

}
