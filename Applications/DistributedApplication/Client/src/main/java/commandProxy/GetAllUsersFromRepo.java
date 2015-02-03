package commandProxy;

import app.AppElement;
import app.repository.UserRepository;

/**
 * This {@code Command} allows to get all users from a repository.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class GetAllUsersFromRepo implements Command {

	/**
	 * The {@code UserRepository} from where the {@code User}s are to be
	 * extracted.
	 */
	UserRepository uRepo;

	/**
	 * Constructor of the {@code Command}
	 * 
	 * @param uRepo
	 *            The {@code UserRepository} from where the {@code User}s are to
	 *            be extracted.
	 */
	public GetAllUsersFromRepo() {}

	/**
	 * @return an array of {@code AppElement}s containing all the {@code User}s
	 *         of the repository.
	 * @see Command#call()
	 */
	@Override
	public AppElement[] call() {
		return uRepo.getAll();
	}

}
