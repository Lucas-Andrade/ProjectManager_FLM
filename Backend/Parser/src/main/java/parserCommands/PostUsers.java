package parserCommands;

import java.util.Map;
import java.util.concurrent.Callable;

import outputMethods.Result;
import parserUtils.CommandFactory;
import app.AppElement;
import app.domainCommands.AddUserToRepo;
import app.domainCommands.exceptions.IllegalEmailException;
import app.domainCommands.exceptions.PasswordLengthOutOfBoundsException;
import app.domainCommands.exceptions.RepeatedUsernameException;
import app.elements.Message;
import app.elements.User;
import app.repository.UserRepository;

/**
 * Class whose instances are commands that create new {@link User}s.
 * Caller {@code String}: POST /users {parameter list}
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class PostUsers extends BaseCommandUserAuthentication{

	/**
	 * {@code String} with the to be added {@code User}'s Username argument.
	 */
	private String username;

	/**
	 * {@code String} with the to be added {@code User}'s Password argument.
	 */
	private String password;

	/**
	 * {@code String} with the to be added {@code User}'s Email argument.
	 */
	private String email;

	/**
	 * {@code String} with the to be added {@code User}'s Full Name argument.
	 */
	private String fullname;

	/**
	 * {@code String} with the {@code User} Username argument's name.
	 */
	private static final String USERNAME = "username";

	/**
	 * {@code String} with the {@code User} Password argument's name.
	 */
	private static final String PASSWORD = "password";

	/**
	 * {@code String} with the {@code User} Email argument's name.
	 */
	private static final String EMAIL = "email";

	/**
	 * {@code String} with the {@code User} Full Name argument's name.
	 */
	private static final String FULLNAME = "fullname";

	/**
	 * An array of {@code String}s with the names of all mandatory arguments.
	 */
	private static final String[] DEMANDING_PARAMETERS = {USERNAME, PASSWORD,
			EMAIL};

	/**
	 * The {@link UserRepository} with the {@code User}s. The created
	 * {@code User}s are stored in this {@code UserRepository}. Also, the
	 * {@code User}'s Username is checked to see if the {@code User} already
	 * exists in the {@code UserRepository}.
	 */
	private final UserRepository repository;

	/**
	 * Class that implements the {@link PostUsers} factory, according to the
	 * {@link CommandFactory}.
	 */
	public static class Factory implements CommandFactory{

		/**
		 * The {@link UserRepository} with the {@code User}s. The created
		 * {@code User}s are stored in this {@code UserRepository}. Also, the
		 * {@code User}'s Username is checked to see if the {@code User} already
		 * exists in the {@code UserRepository}.
		 * 
		 * @see BaseCommandUserAuthentication#repository
		 */
		private final UserRepository uRepository;

		/**
		 * The constructor for {@code Factory}.
		 * 
		 * @param uRepository   The {@code UserRepository}.
		 */
		public Factory(UserRepository uRepository){
			this.uRepository = uRepository;
		}

		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Callable<Result> newInstance(Map<String, String> parameters){
			return new PostUsers(uRepository, parameters);
		}

	}

	/**
	 * The constructor for {@code PostUsers}.
	 * 
	 * @param repository   The {@code UserRepository}.
	 * @param parameters   The {@code Command} arguments.
	 */
	public PostUsers(UserRepository repository, Map<String, String> parameters){
		super(repository, parameters);
		this.repository = repository;
	}

	/**
	 * @see app.commands.BaseCommand#getMandatoryParameters()
	 */
	@Override
	protected String[] getMandatoryParameters(){
		return DEMANDING_PARAMETERS;
	}

	/**
	 * Creates a new {@code User} and adds it to the {@code UserRepository} (if
	 * he doesn't exist already and if the Email argument is valid). Outputs
	 * successful message if successful.
	 * @throws Exception 
	 * 
	 * @see PostUsers#validEmail()
	 * @see BaseCommandUserAuthentication#internalCall()
	 */
	@Override
	protected AppElement[] internalCall() throws Exception{
		
		this.username = getParameterAsString(USERNAME);
		this.password = getParameterAsString(PASSWORD);
		this.email = getParameterAsString(EMAIL);
		if(getParameterAsString(FULLNAME)==null)
			this.fullname=this.username;
		else
			this.fullname = getParameterAsString(FULLNAME);
		
		AppElement [] result;
		try{
			result = new AddUserToRepo(repository, username, password, email, fullname).call();
		} catch(PasswordLengthOutOfBoundsException e) {
			return new AppElement[]{new Message("User's password must have at least 4 characters.")};
		} catch(IllegalEmailException e) {
			return new AppElement[]{new Message("The Email is not valid.")};
		} catch(RepeatedUsernameException e) {
			return new AppElement[]{new Message("The Specified Username is already being used.")};
		}
		
		return result;
	}

}
