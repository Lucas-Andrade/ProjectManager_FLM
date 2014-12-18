package app.commands;

import java.io.IOException;
import java.util.Map;

import app.commands.exceptions.CommandException;
import app.elements.UserInterface;
import app.repository.UserRepository;
import app.resultsOutputMethods.ResultOutputMethod;

public class PatchUser extends BaseCommandAuthentication{

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
	private static final String[] DEMANDING_PARAMETERS = { USERNAME, OLDPASSWORD, NEWPASSWORD };

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
		 * @param uRepository The {@code UserRepository}.
		 */
		public Factory(UserRepository uRepository)
		{
			this.uRepository = uRepository;
		}

		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Command newInstance(Map<String, String> parameters)
		{
			return new PatchUser(uRepository, parameters);
			
		}

	}

	/**
	 * The constructor for {@code PostUsers}.
	 * 
	 * @param repository   The {@code UserRepository}.
	 * @param parameters   The {@code Command} arguments.
	 */
	public PatchUser(UserRepository repository, Map<String, String> parameters)
	{
		super(repository, parameters);
		this.repository = repository;
	}

	/**
	 * @see BaseCommandAuthentication#internalPostExecute(ResultOutputMethod)
	 */
	@Override
	protected void internalPostExecute(ResultOutputMethod out)
			throws CommandException, IOException {
		
		newPassword = getParameterAsString(NEWPASSWORD);
		this.username = getParameterAsString(USERNAME);
		
		UserInterface user = repository.getUserByUsername(username);
		
		if(!user.getLoginPassword().equals(parameters.get(OLDPASSWORD)))
		{
			out.giveResults("The old password is incorrect.");
			return;
		}
		
		if(user.setNewPassword(newPassword))
			out.giveResults("Success.");
		else
			out.giveResults("New password must at least have 4 characters.");
	}

	/**
	 * @see app.commands.BaseCommand#getMandatoryParameters()
	 */
	@Override
	protected String[] getMandatoryParameters() {
		return DEMANDING_PARAMETERS;
	}
}
