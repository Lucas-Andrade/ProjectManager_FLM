package app.elements;

/**
 * Class that defines the contract for the Users of use the App. A
 * {@code UserInterface} is defined by a Username, a Password, Email and Full
 * Name. Extends {@link DatabaseElement}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public interface UserInterface extends DatabaseElement
{

	/**
	 * Method for getting the User's Username.
	 * 
	 * @return The Username.
	 */
	public String getLoginName();

	/**
	 * Method for getting the User's Password.
	 * 
	 * @return The Password.
	 */
	public String getLoginPassword();

	/**
	 * @see Object#toString()
	 */
	public String toString();

	/**
	 * Method for getting the User's Full Name.
	 * 
	 * @return The Full Name.
	 */
	public String getFullName();

	/**
	 * Method for getting the User's Email.
	 * 
	 * @return The Email.
	 */
	public String getEmail();

	/**
	 * @see Object#equals(Object)
	 */
	public boolean equals(UserInterface user);

	/**
	 * allows to set a new password for the user, provided it has the minimum number of 
	 * characters
	 * @param newPassword - the new password
	 * @return {@code true} if the new password was set as the user's password
	 * @return {@code false} if the new password was not set as the user's password
	 */
	public boolean setNewPassword(String newPassword);

}
