package commandProxy;

import app.AppElement;
import app.domainCommands.exceptions.NoSuchUsernameException;
import app.repository.UserRepository;

/**
 * This {@code Command} allows to get a {@code User} from the repository.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015 
 */
public class GetUserFromRepo implements Command{

	
	/**
	 * The {@code username} of the {@code User}
	 */
	String username;
	
	/**
	 * Constructor of the {@code Command}.
	 * 
	 * @param uRepo
	 * The {@code UserRepository} where the {@code User} is stored
	 * @param username
	 * The {@code username} of the {@code User}
	 */
	public GetUserFromRepo(String username){
		if (username == null){
			throw new IllegalArgumentException();
		}
		this.username = username;
	}
	
	/**
	 * @return an array of {@code AppElement}s containing the {@code User}.
	 * @see Command#call()
	 */
	@Override
	public AppElement[] call() throws NoSuchUsernameException {
		
		AppElement[] user = new AppElement[]{uRepo.getUserByUsername(username)};
		if(user[0] == null){
			throw new NoSuchUsernameException("That username does not exist.");
		}
		return user;
	}

}
