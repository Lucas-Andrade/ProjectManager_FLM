package App.elements;


/**
 * @author amiguinhos do Maia
 * Eva's Seal of Approval
 */
public class User implements UserInterface
{

	private final String loginName;
	private String loginPassword;
	private String email;
	private final String fullName;

	public User(String username, String password, String email, String fullName)
	{
		this.loginName = username;
		this.loginPassword = password;
		this.email = email;
		this.fullName = fullName;
	}

	/**
	 * @return the username
	 */
	public String getLoginName()
	{
		return loginName;
	}

	/**
	 * @return the password
	 */
	public String getLoginPassword()
	{
		return loginPassword;
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
		return fullName;
	}

	/**
	 * @return information about this user
	 */
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Name: ").append(fullName).append(", Email: ").append(email)
			.append(", Username: ").append(loginName);
		
		return builder.toString();
	}
}
