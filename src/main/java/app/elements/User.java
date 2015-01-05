package app.elements;

import org.json.JSONObject;

/**
 * Class whose instances represent Users that use the App. A {@code User} is
 * defined by a Username, a Password, Email and Full Name.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class User implements UserInterface, AppElement
{

	private final String username;
	private String password;
	private String email;
	private final String fullname;
	public final static int minCharInPass = 4;

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
		if (password.length() < minCharInPass)
			throw new IllegalArgumentException();
		
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
	}
	
	public User(String username, String password, String email)
	{
		this(username, password, email, "No full name has been introduced.");
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
	
	@Override
	public JSONObject getJson() 
	{
		JSONObject json = new JSONObject();
		json.put("Username", username.replaceAll("%20", " "));
		json.put("Email", email);
		json.put("Full name", fullname.replaceAll("%20", " "));
		return json;
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

	@Override
	public boolean setNewPassword(String newPassword) {
		
		if (newPassword.length() < minCharInPass)
			return false;
		else
			password = newPassword;
		return true;
	}



}
