package app.domainCommands;

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
	 * The {@code UserRepository} where the {@code User} is stored
	 */
	UserRepository uRepo;

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
	 * @param uRepo
	 *            The {@code UserRepository} where the {@code User} is stored
	 * @param username
	 *            The {@code username} of the {@code User} whose password is to
	 *            be changed
	 * @param oldPassword
	 *            The {@code User}'s current password
	 * @param newPassword
	 *            The {@code User}'s new password (must have at least 4
	 *            characters)
	 */
	public SetUserPropertiesFromRepo(UserRepository uRepo, String username,
			String oldPassword, String newPassword) {

		if (uRepo == null || username == null || oldPassword == null
				|| newPassword == null) {
			throw new IllegalArgumentException();
		}
		this.uRepo = uRepo;
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

		if (!uRepo.isPasswordCorrectForUser(username, oldPassword)) {
			throw new IncorrectPasswordException(
					"That password is not correct.");
		}

		IUser user = uRepo.getUserByUsername(username);
		if (user == null) {
			throw new NoSuchUsernameException("That username does not exist.");
		}
		if (!user.setNewPassword(newPassword)) {
			throw new PasswordLengthOutOfBoundsException(
					"A password must have at least four characters.");
		}

		return new AppElement[] { user };
	}

}
