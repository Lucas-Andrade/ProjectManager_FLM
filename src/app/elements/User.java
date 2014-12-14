package app.elements;

/**
 * Class whose instances represent Users that use the App. A {@code User} is
 * defined by a Username, a Password, Email and Full Name.
 */
public class User implements UserInterface
{

	private final String username;
	private String password;
	private String email;
	private final String fullname;

	/**
	 * The constructor of {@code User}.
	 * 
	 * @param username
	 *            The User's Username.
	 * @param password
	 *            The User's Password.
	 * @param email
	 *            The User's Email.
	 * @param fullname
	 *            The User's Full Name.
	 */
	public User(String username, String password, String email, String fullname)
	{
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
	}

	/**
	 * Method for getting the User's Username.
	 * 
	 * @return The Username.
	 */
	@Override
	public String getLoginName()
	{
		return username;
	}

	/**
	 * Method for getting the User's Password.
	 * 
	 * @return The Password.
	 */
	@Override
	public String getLoginPassword()
	{
		return password;
	}

	/**
	 * Method for getting the User's Email.
	 * 
	 * @return The Email.
	 */
	@Override
	public String getEmail()
	{
		return email;
	}

	/**
	 * Method for getting the User's Full Name.
	 * 
	 * @return The Full Name.
	 */
	@Override
	public String getFullName()
	{
		return fullname;
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Name: ").append(fullname).append(", Email: ")
				.append(email).append(", Username: ").append(username);

		return builder.toString();
	}

	/**
	 * @see Object#equals(Object)
	 */
	@Override
	public boolean equals(UserInterface user)
	{
		if (user == null)
			return false;

		if (fullname.equals(user.getFullName())
				&& email.equals(user.getEmail())
				&& username.equals(user.getLoginName())
				&& password.equals(user.getLoginPassword()))
			return true;
		else
			return false;
	}

}
