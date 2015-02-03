package commandProxy;

import app.AppElement;
import app.domainCommands.exceptions.IncorrectPasswordException;
import app.domainCommands.exceptions.NoSuchUsernameException;
import app.domainCommands.exceptions.PasswordLengthOutOfBoundsException;
import app.elements.IUser;
import app.repository.UserRepository;

/**
 * This {@code Command} allows to change a {@code User}'s password.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class SetUserPropertiesFromRepo implements Command {


	/**
	 *  The {@code username} of the {@code User} whose password is to be changed
	 */
	String username;
	
	/**
	 *  The {@code User}'s current password
	 */
	String oldPassword;
	
	/**
	 * The {@code User}'s new password (must have at least 4 characters)
	 */
	String newPassword;

	/**
	 * Constructs the {@code Command}.
	 * 
	 * @param username
	 *            The {@code username} of the {@code User} whose password is to
	 *            be changed
	 * @param oldPassword
	 *            The {@code User}'s current password
	 * @param newPassword
	 *            The {@code User}'s new password (must have at least 4
	 *            characters)
	 */
	public SetUserPropertiesFromRepo(String username,String oldPassword, String newPassword) {

		if (username == null || oldPassword == null	|| newPassword == null) {
			throw new IllegalArgumentException();
		}
		this.username = username;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	/**
	 * @return an array of {@code AppElement}s containing the modified {@code User}.
	 * @see Command#call()
	 */
	@Override
	public AppElement[] call() throws IncorrectPasswordException,
			NoSuchUsernameException, PasswordLengthOutOfBoundsException {

		
		return new AppElement[] { user };
	}

}
