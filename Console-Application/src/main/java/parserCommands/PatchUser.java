package parserCommands;

import java.util.Map;
import java.util.concurrent.Callable;

import outputMethods.Result;
import app.AppElement;
import app.domainCommands.SetUserPropertiesFromRepo;
import app.domainCommands.exceptions.IncorrectPasswordException;
import app.domainCommands.exceptions.NoSuchUsernameException;
import app.domainCommands.exceptions.PasswordLengthOutOfBoundsException;
import app.elements.Message;
import app.elements.User;
import app.repository.UserRepository;

/**
 * Class whose instances are commands that modifies {@link User}s
 * {@code password}. Caller {@code String}: PATCH /users/{username} {parameter
 * list}
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 05/01/2015
 */
public class PatchUser extends BaseCommandUserAuthentication{

	/**
	 * {@code String} with the to be added {@code User}'s Username argument.
	 */
	private String username;

	/**
	 * {@code String} with the to be added {@code User}'s Password argument.
	 */
	private String newPassword;

	/**
	 * {@code String} with the {@code User} Username argument's name.
	 */
	public static final String USERNAME = "username";

	/**
	 * {@code String} with the {@code User} Password argument's name.
	 */
	private static final String NEWPASSWORD = "newPassword";

	/**
	 * {@code String} with the {@code User} Password argument's name.
	 */
	private static final String OLDPASSWORD = "oldPassword";

	/**
	 * An array of {@code String}s with the names of all mandatory arguments.
	 */
	private static final String[] DEMANDING_PARAMETERS = { USERNAME,
			OLDPASSWORD, NEWPASSWORD };

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
		 * @see BasePostCommand#repository
		 */
		private final UserRepository uRepository;

		/**
		 * The constructor for {@code Factory}.
		 * 
		 * @param uRepository
		 *            The {@code UserRepository}.
		 */
		public Factory(UserRepository uRepository){
			this.uRepository = uRepository;
		}

		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Callable<Result> newInstance(Map<String, String> parameters)	{
			return new PatchUser(uRepository, parameters);

		}
	}

	/**
	 * The constructor for {@code PostUsers}.
	 * 
	 * @param repository
	 *            The {@code UserRepository}.
	 * @param parameters
	 *            The {@code Command} arguments.
	 */
	public PatchUser(UserRepository repository, Map<String, String> parameters){
		super(repository, parameters);
		this.repository = repository;
	}

	/**
	 * @return The modified {@code User}.
	 * @see BaseCommandUserAuthentication#internalCall()
	 */
	@Override
	protected AppElement[] internalCall() throws Exception{

		this.newPassword = getParameterAsString(NEWPASSWORD);
		String oldPassword = parameters.get(OLDPASSWORD);
		this.username = getParameterAsString(USERNAME);
		
		try{
			new SetUserPropertiesFromRepo(repository, username, oldPassword, newPassword).call();
		} catch(IncorrectPasswordException e) {
			return new AppElement[] { new Message(
					"Old password is not correct for user: " + username) };
		} catch(NoSuchUsernameException e) {
			return new AppElement[] { new Message("User not found!") };
		} catch(PasswordLengthOutOfBoundsException e) {
			return new AppElement[] { new Message("New password must at least have 4 characters.") };
		}
		
		return new AppElement[] { new Message("Password successfully changed.") };
	}

	/**
	 * @see app.commands.BaseCommand#getMandatoryParameters()
	 */
	@Override
	protected String[] getMandatoryParameters(){
		return DEMANDING_PARAMETERS;
	}
}
