package app.elements;

/**
 * Class that defines the contract for the Users of use the App. A
 * {@code UserInterface} is defined by a Username, a Password, Email and Full
 * Name. Extends {@link DatabaseElement}.
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

}
