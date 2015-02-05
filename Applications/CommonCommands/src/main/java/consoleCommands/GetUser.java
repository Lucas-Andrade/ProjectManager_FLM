package consoleCommands;

import java.util.Map;
import java.util.concurrent.Callable;

import parserUtils.CommandFactory;
import parserUtils.ParserResult;
import app.AppElement;
import app.domainCommands.GetUserFromRepo;
import app.domainCommands.exceptions.NoSuchUsernameException;
import app.elements.Message;
import app.elements.User;
import app.repository.UserRepository;

/**
 * Class whose instances are {@link Command}s that return a {@link User}.
 * Caller {@code String}: GET /users/{username}
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class GetUser extends BaseCommandResultsOutputMethod{

	/**
	 * {@code String} with the {@code User}'s Username argument. This argument
	 * is used for getting the right {@code User} from the
	 * {@code UserRepository}.
	 */
	private String username;

	/**
	 * The {@link UserRepository} with the {@code User}s. This
	 * {@code UserRepository} is accessed to get the {@code User}.
	 */
	private final UserRepository repository;

	/**
	 * The {@link CommandParser.Node.content} to be used (between "{" and "}",
	 * see {@link CommandParser#Node#isPlaceHolderNode()}) in the
	 * {@link CommandParser#Node} that has the {@code GetUser#Factory} in the
	 * field {@link CommandParser#Node#factory}.
	 */
	public static final String USERNAME = "username";

	/**
	 * Class that implements the {@link GetUser} factory, according to the
	 * AbstratFactory design pattern.
	 */
	public static class Factory implements CommandFactory{
		/**
		 * The {@link UserRepository} with the {@code User}s. This
		 * {@code UserRepository} is accessed to get the {@code User}.
		 */
		private final UserRepository repository;

		/**
		 * The constructor for {@code Factory}.
		 * 
		 * @param repository
		 *            The {@code UserRepository}.
		 */
		public Factory(UserRepository repository){
			this.repository = repository;
		}

		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Callable<ParserResult> newInstance(Map<String, String> parameters){
			return new GetUser(repository, parameters);
		}
	}

	/**
	 * The constructor for {@code GetUser}.
	 * 
	 * @param repository  The {@code UserRepository}.
	 * @param parameters  The {@code Command} arguments.
	 */
	private GetUser(UserRepository repository, Map<String, String> parameters){
		super(parameters);
		this.username = parameters.get(USERNAME);
		this.repository = repository;
	}

	/**
	 * @see app.commands.BaseCommand#getMandatoryParameters()
	 */
	@Override
	protected String[] getMandatoryParameters(){
		return new String[] { USERNAME };
	}

	/**
	 * Get's the {@code User} with the {@code GetUser#username} in the
	 * {@code UserRepository}, if it exists.
	 * 
	 * @return An array of {@code DatabaseElement} with one element carrying 
	 * the {@code User}.
	 * @see BaseCommandResultsOutputMethod#internalCall()
	 */
	@Override
	protected AppElement[] internalCall() {
		username = getParameterAsString(USERNAME);
		
		AppElement[] user = null;
		try{
			user = new GetUserFromRepo(repository, username).call();
		} catch(NoSuchUsernameException e) {
			return new AppElement[]{new Message("User " + username + " not found!")};
		}

		return user;
	}
}
