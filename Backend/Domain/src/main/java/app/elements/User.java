package app.elements;

import org.json.JSONObject;

/**
 * Class whose instances represent Users that use the App. A {@code User} is
 * defined by a Username, a Password, Email and Full Name.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class User implements IUser{

	/**
	 * A volatile String with the password.
	 */
	private volatile String password;

	private final String username;
	private final String email;
	private final String fullname;
	public final static int MIN_CHAR_IN_PASS = 4;

	/**
	 * The lock to be used inside {@code this#setNewPassword(String)}.
	 */
	private Object lockSetNewPassword;
	
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
	public User(String username, String password, String email, String fullname){
		if (password.length() < MIN_CHAR_IN_PASS){
			throw new IllegalArgumentException();
	}
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
	}
	
	public User(String username, String password, String email){
		this(username, password, email, username);
	}

	/**
	 * Method for getting the User's Username.
	 * 
	 * @return The Username.
	 */
	@Override
	public String getLoginName(){
		return username;
	}

	/**
	 * Method for getting the User's Password.
	 * 
	 * @return The Password.
	 */
	@Override
	public String getLoginPassword(){
		return password;
	}

	/**
	 * Method for getting the User's Email.
	 * 
	 * @return The Email.
	 */
	@Override
	public String getEmail(){
		return email;
	}

	/**
	 * Method for getting the User's Full Name.
	 * 
	 * @return The Full Name.
	 */
	@Override
	public String getFullName(){
		return fullname;
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Name: ").append(fullname).append(", Email: ")
				.append(email).append(", Username: ").append(username);

		return builder.toString();
	}
	
	@Override
	public JSONObject getJson() {
		JSONObject json = new JSONObject();
		json.put("Username", username.replaceAll("%20", " "));
		json.put("Email", email);
		json.put("Full name", fullname.replaceAll("%20", " "));
		return json;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((fullname == null) ? 0 : fullname.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	/**
	 * @see Object#equals(Object)
	 */
	@Override
	public boolean equals(Object user)
	{
		if (this == user)
		{
			return true;
		}
		if (user == null)
		{
			return false;
		}
		if (getClass() != user.getClass())
		{
			return false;
		}

		return hasSameParameters((User) user);
	}
	
	/**
	 * Verifies if the {@code User} passed as parameter has the same properties as {@code this}.
	 * @param worker
	 * @return true if the {@code User} passed as parameter has the same properties as {@code this}
	 * @return false if the {@code User} passed as parameter has not the same properties as 
	 * {@code this}
	 */
	public boolean hasSameParameters(User user){
		if (fullname.equals(user.getFullName())
				&& email.equals(user.getEmail())
				&& username.equals(user.getLoginName())
				&& password.equals(user.getLoginPassword())){
			return true;
		}
		return false;
	}

	/**
	 * Synchronized update.
	 */
	@Override
	public boolean setNewPassword(String newPassword) {
		
		if (newPassword.length() < MIN_CHAR_IN_PASS){
			return false;
		}else{
			synchronized(lockSetNewPassword){
			password = newPassword;}
			return true;
		}
	}
}
