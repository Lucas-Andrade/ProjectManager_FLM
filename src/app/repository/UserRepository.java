package app.repository;

import app.elements.DatabaseElements;
import app.elements.User;
import app.elements.UserInterface;

public interface UserRepository extends Repository<DatabaseElements>{

	/**
	 * Gets the user with the given loginName, or {@code null} if none exists
	 *  
	 * @param loginName the user identifier
	 * @return the instance with the given identifier
	 */
	public UserInterface getUserByUsername(String loginName);

	public UserInterface[] getAllUsers();

}
