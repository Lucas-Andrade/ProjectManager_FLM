package parserCommands;

import java.io.FileNotFoundException;
import java.util.Map;

import outputMethods.Result;
import parserCommands.exceptions.InvalidUserException;
import app.AppElement;
import app.elements.User;
import app.repository.UserRepository;

/**
 * Abstract Command to be supported by all Commands that require an {@link User}
 * authentication. The end result of this Command is a {@link Result}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public abstract class BaseCommandUserAuthentication extends BaseCommand{

	/**
	 * {@code String} with the Login Name argument's name. This argument is used
	 * for {@code User}'s authentication.
	 */
	public static final String LOGINNAME = "loginName";

	/**
	 * {@code String} with the Login Password argument's name. This argument is
	 * used for {@code User}'s authentication.
	 */
	public static final String LOGINPASSWORD = "loginPassword";

	/**
	 * An array of {@code String}s with the names of all mandatory arguments.
	 */
	public static final String[] DEMANDING_PARAMETERS = new String[] {
			LOGINNAME, LOGINPASSWORD };

	/**
	 * The {@link UserRepository} with the {@code User}s. This
	 * {@code UserRepository} is accessed to authenticate the {@code User}
	 * requesting the POST {@code Command}.
	 */
	private final UserRepository repository;

	/**
	 * The constructor for {@code BasePostCommand}.
	 * 
	 * @param repository   The {@code UserRepository} with the {@code User}s.
	 * @param parameters   The {@code Command} arguments.
	 */
	public BaseCommandUserAuthentication(UserRepository userRepository,
			Map<String, String> parameters){
		super(parameters);
		repository = userRepository;
	}

	/**
	 * This method creates an object Result with the {@code DatabaseElement}s
	 * returned by {@code this#internalCall()}, after validating the mandatory
	 * parameters (if not stops the execution) and after authenticates the
	 * {@code User}. If authentication is incorrect throws
	 * {@link InvalidUserException}, if correct proceeds with the execution.
	 * @throws Exception 
	 * @throws FileNotFoundException 
	 */
	@Override
	public Result call() throws Exception {
		validateDemandingParameters(getMandatoryParameters());
		String username = parameters.get(LOGINNAME);
		String password = parameters.get(LOGINPASSWORD);
		if (authenticateUser(username, password)){
			return new Result(internalCall(), null, null);
		}else{
			throw new InvalidUserException(username);
		}
	}

	/**
	 * Authenticates a {@code User}. If the Login Name corresponds to the
	 * Username and if the Login Password corresponds to the Password, returns
	 * {@code True}, if not returns {@code False}.
	 * 
	 * @param loginName   The Login Name.
	 * @param loginPassword   The Login Password.
	 * @return {@code True} if authentication success, {@code False} otherwise.
	 */
	protected boolean authenticateUser(String loginName, String loginPassword){
		return repository.isPasswordCorrectForUser(loginName, loginPassword);
	}

	/**
	* @see BaseCommand#internalCall()
	 * 
	 * @throws Exception
	 */
	abstract protected AppElement[] internalCall() throws Exception;
}
