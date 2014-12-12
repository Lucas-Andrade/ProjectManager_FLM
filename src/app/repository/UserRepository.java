
package app.repository;

import app.elements.DatabaseElement;
import app.elements.User;
import app.elements.UserInterface;


/**
 * @author amiguinhos do Maia
 *
 */
public interface UserRepository extends Repository<DatabaseElement>
{
	/**
	 * Gets the user with the given loginName, or {@code null} if none exists
	 *  
	 * @param loginName the user identifier
	 * @return the instance with the given identifier
	 */
	public UserInterface getUserByUsername(String loginName);
	
	public boolean addUser(User user);
	
	public boolean isPasswordCorrectForUser(String username, String userPassword);

	public UserInterface[] getAllUsers();

	boolean addAdmin(String username, String password);
	
}
