package app.elements.mutable;

import app.elements.IUser;
import app.elements.User;

/**
 * Class with the task of creating a new {@code IUser} with the supplied
 * parameters. Instances of this class aren't immutable.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 03/02/2015
 */
public class UserCreationDescriptor {

	/**
	 * The User's Username.
	 */
	private String username;

	/**
	 * The User's Password.
	 */
	private String password;

	/**
	 * The User's Email.
	 */
	private String email;

	/**
	 * The User's Full Name.
	 */
	private String fullname;

	/**
	 * An empty constructor for this class.
	 */
	public UserCreationDescriptor() {
	}

	/**
	 * A constructor for this class with the required fields for creating a
	 * {@code User}.
	 */
	public UserCreationDescriptor(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname=this.username;
	}

	/**
	 * A constructor for this class with all fields for creating a {@code User}.
	 */
	public UserCreationDescriptor(String username, String password,
			String email, String fullName) {
		this(username, password, email);
		this.fullname = fullName;
	}

	/**
	 * Method to update the {@code this#username}.
	 * 
	 * @param username
	 *            The User's username.
	 * @return {@code this}.
	 */
	public UserCreationDescriptor username(String username) {
		this.username = username;
		return this;
	}

	/**
	 * Method to update the {@code this#password}.
	 * 
	 * @param password
	 *            The User's password.
	 * @return {@code this}.
	 */
	public UserCreationDescriptor password(String password) {
		this.password = password;
		return this;
	}

	/**
	 * Method to update the {@code this#email}.
	 * 
	 * @param email
	 *            The User's email.
	 * @return {@code this}.
	 */
	public UserCreationDescriptor email(String email) {
		this.email = email;
		return this;
	}

	/**
	 * Method to update the {@code this#fullname}.
	 * 
	 * @param fullname
	 *            The User's full name.
	 * @return {@code this}.
	 */
	public UserCreationDescriptor fullname(String fullname) {
		this.fullname = fullname;
		return this;
	}

	/**
	 * @return this{@link #username}.
	 */
	public String getLoginName() {
		return this.username;
	}

	/**
	 * Method that instantiates an {@code IUser} with the fields from
	 * {@code this} object.
	 * 
	 * @return The created {@code IUser}.
	 */
	public IUser build() {
		return new User(username, password, email, fullname);
	}

}
