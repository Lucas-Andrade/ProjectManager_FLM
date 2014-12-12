package app.elements;



/**
 * @author amiguinhos do Maia
 *
 */
public interface UserInterface extends DatabaseElement
{
	/**
	 * @return the username
	 */
	public String getLoginName();

	/**
	 * @return the password
	 */
	public String getLoginPassword();
	
	public String toString();
	
	public String getFullName();
	
	public String getEmail();
	
	public boolean equals(UserInterface user);
}
