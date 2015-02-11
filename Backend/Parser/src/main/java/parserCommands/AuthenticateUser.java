package parserCommands;

import java.util.Map;
import java.util.concurrent.Callable;

import outputMethods.Result;
import parserUtils.CommandFactory;
import app.AppElement;
import app.domainCommands.UserAuthentication;
import app.repository.UserRepository;

/**
 * Class whose instances are commands that authenticate an {@code User}.
 * Caller {@code String}: GET/authenticate
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 05/01/2015
 */
public class AuthenticateUser extends BaseCommandResultsOutputMethod{

	/**
	 * {@code String} with the {@code User} Login Name argument's name.
	 */
	private static final String USERNAME = "loginName";

	/**
	 * {@code String} with the {@code User} Login Password argument's name.
	 */
	private static final String PASSWORD = "loginPassword";

	/**
	 * An array of {@code String}s with the names of all mandatory arguments.
	 */
	private static final String[] DEMANDING_PARAMETERS = {USERNAME, PASSWORD};

	/**
	 * The {@link UserRepository} with the {@code User}s.
	 */
	private final UserRepository repository;

	/**
	 * Class that implements the {@link AuthenticateUser} factory, according to the
	 * {@link CommandFactory}.
	 */
	public static class Factory implements CommandFactory{

		/**
		 * The {@link UserRepository} with the {@code User}s.
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
			return new AuthenticateUser(uRepository, parameters);
		}

	}

	/**
	 * The constructor for {@code AuthenticateUser}.
	 * 
	 * @param repository   The {@code UserRepository}.
	 * @param parameters   The {@code Command} arguments.
	 */
	public AuthenticateUser(UserRepository repository, Map<String, String> parameters){
		super(parameters);
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
	 * 
	 * @see BaseCommandUserAuthentication#internalCall()
	 */
	@Override
	protected AppElement[] internalCall() throws Exception{
		String username = getParameterAsString(USERNAME);
		String password = getParameterAsString(PASSWORD);
		
		return new UserAuthentication(repository, username, password).call();
	}

}
