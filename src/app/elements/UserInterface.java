package app.elements;



/**
 * @author amiguinhos do Maia
 *
 */
public interface UserInterface extends DatabaseElements
{
	/**
	 * @return the username
	 */
	public String getLoginName();

	/**
	 * @return the password
	 */
	public String getLoginPassword();
}