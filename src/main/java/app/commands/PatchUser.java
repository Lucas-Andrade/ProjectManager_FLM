package app.commands;

import java.util.Map;
import java.util.concurrent.Callable;

import app.elements.AppElement;
import app.elements.Message;
import app.elements.User;
import app.elements.UserInterface;
import app.forConsole.resultsAndOutputMethods.Result;
import app.repository.UserRepository;

/**
 * Class whose instances are commands that modifies {@link User}s
 * {@code password}. Caller {@code String}: PATCH /users/{username} {parameter
 * list}
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 05/01/2015
 */
public class PatchUser extends BaseCommandUserAuthentication
{

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
	public static class Factory implements CommandFactory
	{

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
	public PatchUser(UserRepository repository, Map<String, String> parameters)
	{
		super(repository, parameters);
		this.repository = repository;
	}

	/**
	 * @return The modified {@code User}.
	 * @see BaseCommandUserAuthentication#internalCall()
	 */
	@Override
	protected AppElement[] internalCall() throws Exception
	{

		this.newPassword = getParameterAsString(NEWPASSWORD);
		String oldPassword = parameters.get(OLDPASSWORD);
		this.username = getParameterAsString(USERNAME);

		if (!super.authenticateUser(this.username, oldPassword))
			return new AppElement[] { new Message(
					"Old password is not correct for user: " + username) };
		;

		UserInterface user = repository.getUserByUsername(username);
		AppElement[] messageAux = new AppElement[1];

		if (user == null)
		{
			Message message = new Message("User not found!");
			messageAux[0] = message;
			return messageAux;
		}

		if (user.setNewPassword(newPassword))
		{
			Message message = new Message("Password successfully changed");
			messageAux[0] = message;
		} else
		{
			Message message = new Message(
					"New password must at least have 4 characters.");
			messageAux[0] = message;
		}
		return messageAux;
	}

	/**
	 * @see app.commands.BaseCommand#getMandatoryParameters()
	 */
	@Override
	protected String[] getMandatoryParameters()
	{
		return DEMANDING_PARAMETERS;
	}

}
