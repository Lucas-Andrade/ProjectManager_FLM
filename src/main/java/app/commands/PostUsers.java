package app.commands;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;

import app.commands.exceptions.CommandException;
import app.elements.DatabaseElement;
import app.elements.User;
import app.elements.UserInterface;
import app.repository.UserRepository;
import app.resultsOutputMethods.ResultOutputMethod;

/**
 * Class whose instances are {@link Command}s that create new {@link User}s.
 * Caller {@code String}: POST /users {parameter list}
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class PostUsers extends BaseCommandUserAuthentication
{

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
	private static final String[] DEMANDING_PARAMETERS = { USERNAME, PASSWORD,
			EMAIL, FULLNAME };

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
	public static class Factory implements CommandFactory
	{

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
		 * @param uRepository
		 *            The {@code UserRepository}.
		 */
		public Factory(UserRepository uRepository)
		{
			this.uRepository = uRepository;
		}

		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Callable<Result> newInstance(Map<String, String> parameters)
		{
			return new PostUsers(uRepository, parameters);
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
	public PostUsers(UserRepository repository, Map<String, String> parameters)
	{
		super(repository, parameters);
		this.repository = repository;
	}

	/**
	 * @see app.commands.BaseCommand#getMandatoryParameters()
	 */
	@Override
	protected String[] getMandatoryParameters()
	{
		return DEMANDING_PARAMETERS;
	}

	/**
	 * Creates a new {@code User} and adds it to the {@code UserRepository} (if
	 * he doesn't exist already and if the Email argument is valid). Outputs
	 * successful message if successful.
	 * 
	 * @see PostUsers#validEmail()
	 * @see BaseCommandUserAuthentication#internalExecuteAfterUserAuthentication(ResultOutputMethod)
	 */
	@Override
	protected DatabaseElement internalCall()
			throws CommandException, IOException
	{
		this.username = parameters.get(USERNAME);
		this.password = parameters.get(PASSWORD);
		
		if(password.length() < User.minCharInPass)
		{
			out.giveResults("User's password must have at least 4 characters.");
			return;
		}
		
		this.email = parameters.get(EMAIL);
		this.fullname = parameters.get(FULLNAME);
		UserInterface[] existingUsers = (UserInterface[]) repository.getAll();
		for (UserInterface existingUser : existingUsers)
		{
			if (existingUser.getLoginName().equals(this.username))
			{
				out.giveResults("The Specified Username already exists in repository.");
				return;
			}
		}
		if (this.validEmail())
		{
			repository.addUser(new User(this.username, this.password,
					this.email, this.fullname));
			out.giveResults("Success.");
			return;
		} else
			out.giveResults("The Email is not valid.");
	}

	/**
	 * Validates the Email.
	 * 
	 * @return Returns True if valid, False if not.
	 */
	private boolean validEmail()
	{
		if (!(email.contains("@")))
			return false;
		if (email.substring(email.indexOf("@") + 1, email.length()).contains(
				"@"))
			return false;
		if (email.lastIndexOf(".") < email.lastIndexOf("@"))
			return false;
		return true;
	}

}
