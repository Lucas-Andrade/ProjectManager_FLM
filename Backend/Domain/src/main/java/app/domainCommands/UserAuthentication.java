package app.domainCommands;

import app.AppElement;
import app.domainCommands.exceptions.CommandExecutionException;
import app.domainCommands.exceptions.IncorrectPasswordException;
import app.domainCommands.exceptions.NoSuchUsernameException;
import app.elements.IUser;
import app.repository.UserRepository;

/**
 * This {@code Command} allows to verify if a certain set of {@code username} + {@code password} 
 * belongs to any of the {@code User}s of the {@code UserRepository}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class UserAuthentication implements Command {

	/**
	 * The {@code UserRepository} where to search for the {@code User} to be authenticated.
	 */
	UserRepository uRepo;

	/**
	 * The {@code loginName} of the {@code User} to be authenticated.
	 */
	String username;

	/**
	 * The {@code loginPassword} of the {@code User} to be authenticated.
	 */
	String password;

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
	 */
	public UserAuthentication(UserRepository uRepo, String username, String password) {
		if (uRepo == null || username == null || password == null) {
			throw new IllegalArgumentException();
		}
		this.uRepo = uRepo;
		this.username = username;
		this.password = password;
	}

	/**
	 * @return an array of {@code AppElement}s containing the authenticated
	 *         {@code User}.
	 * @throws CommandExecutionException 
	 * @see Command#call()
	 */
	@Override
	public AppElement[] call() throws CommandExecutionException {
		IUser user = uRepo.getUserByUsername(username);
		if(user == null){
			throw new NoSuchUsernameException("No such user.");
		}
		if(!user.getLoginPassword().equals(password)){
			throw new IncorrectPasswordException("Incorrect password.");
		}
		
		return new AppElement[]{user};
	}
	
}
