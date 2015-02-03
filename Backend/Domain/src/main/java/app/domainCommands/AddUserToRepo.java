package app.domainCommands;

import app.AppElement;
import app.domainCommands.exceptions.IllegalEmailException;
import app.domainCommands.exceptions.PasswordLengthOutOfBoundsException;
import app.domainCommands.exceptions.RepeatedUsernameException;
import app.elements.User;
import app.repository.UserCreationDescriptor;
import app.repository.UserRepository;

/**
 * This {@code Command} allows to construct a new {@code User} and add it to the
 * repository.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class AddUserToRepo implements Command {

	/**
	 * The {@code UserRepository} to which the user will be added.
	 */
	UserRepository uRepo;

	/**
	 * The {@code loginName} of the new {@code User}.
	 */
	String username;

	/**
	 * The {@code loginPassword} of the new {@code User}. It must have at least
	 * four characters.
	 */
	String password;

	/**
	 * The {@code email} of the new {@code User}.
	 */
	String email;

	/**
	 * The {@code fullName} of the new {@code User}. As this parameter is
	 * optional for the {@code User}, {@code null} or an empty {@code String},
	 * are accepted as a way of providing no full name for the {@code User}.
	 */
	String fullName;

	/**
	 * Constructs the {@code Command}.
	 * 
	 * @param uRepo
	 *            The {@code UserRepository} to which the user will be added.
	 * @param username
	 *            The {@code loginName} of the new {@code User}.
	 * @param password
	 *            The {@code loginPassword} of the new {@code User}. It must
	 *            have at least four characters.
	 * @param email
	 *            The {@code email} of the new {@code User}.
	 * @param fullName
	 *            The {@code fullName} of the new {@code User}. As this
	 *            parameter is optional for the {@code User}, {@code null} or an
	 *            empty {@code String}, are accepted as a way of providing no
	 *            full name for the {@code User}.
	 */
	public AddUserToRepo(UserRepository uRepo, String username,
			String password, String email, String fullName) {
		if (uRepo == null || username == null || password == null
				|| email == null) {
			throw new IllegalArgumentException();
		}
		this.uRepo = uRepo;
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullName = fullName;

		setEmptyStringsToNull();
	}

	/**
	 * @return an array of {@code AppElement}s containing the constructed
	 *         {@code User}.
	 * @throws Exception 
	 * @see Command#call()
	 */
	@Override
	public AppElement[] call() throws Exception {

		if (password.length() < User.MIN_CHAR_IN_PASS) {
			throw new PasswordLengthOutOfBoundsException(
					"A password must have at least four characters.");
		}
		validEmail();

		UserCreationDescriptor userCreation;
		if (fullName == null) {
			userCreation = new UserCreationDescriptor(username, password, email);
		} else {
			userCreation = new UserCreationDescriptor(username, password, email, fullName);
		}

		if (!uRepo.addUser(userCreation)) {
			throw new RepeatedUsernameException(
					"That username is already being used.");
		}

		return new AppElement[] { uRepo.getUserByUsername(username) };
	}

	/**
	 * Validates the Email. If the email is not valid throws an
	 * IllegalEmailException
	 * 
	 * @throws IllegalEmailException
	 * 
	 */
	private void validEmail() throws IllegalEmailException {
		if (!email.contains("@")
				|| email.substring(email.indexOf("@") + 1, email.length())
						.contains("@")
				|| email.lastIndexOf(".") < email.lastIndexOf("@")) {
			throw new IllegalEmailException("That email is not valid.");
		}
	}

	/**
	 * Sets the {@code fullName} to {@code null} if an empty {@code String} was
	 * introduced in the constructor.
	 */
	private void setEmptyStringsToNull() {
		if ("".equals(fullName)) {
			fullName = null;
		}
	}
}
