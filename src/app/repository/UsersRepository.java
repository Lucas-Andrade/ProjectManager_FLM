/**
 * 
 */
package app.repository;

import app.elements.UserInterface;
/**
 * @author amiguinhos do Maia
 *
 */
public interface UsersRepository extends Repository<UserInterface>
{
	/**
	 * Gets the user with the given loginName, or {@code null} if none exists
	 *  
	 * @param loginName the user identifier
	 * @return the instance with the given identifier
	 */
	public UserInterface getUserByUsername(String loginName);

	public UserInterface[] getAllUsers();
}