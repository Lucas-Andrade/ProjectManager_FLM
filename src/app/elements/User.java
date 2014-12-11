package app.elements;


/**
 * @author amiguinhos do Maia
 * Eva's Seal of Approval
 */
public class User implements UserInterface
{

	private final String username;
	private String password;
	private String email;
	private final String fullname;

	public User(String username, String password, String email, String fullname)
	{
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
	}

	/**
	 * @return the username
	 */
	public String getLoginName()
	{
		return username;
	}

	/**
	 * @return the password
	 */
	public String getLoginPassword()
	{
		return password;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName()
	{
		return fullname;
	}

	/**
	 * @return information about this user
	 */
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Name: ").append(fullname).append(", Email: ").append(email)
			.append(", Username: ").append(username);
		
		return builder.toString();
	}
}
