package app.commands;

import java.io.IOException;
import java.util.Map;

import app.commands.exceptions.CommandException;
import app.elements.UserInterface;
import app.repository.UserRepository;
import app.resultsOutputMethods.ResultOutputMethod;

/**
 * @author amiguinhos do Maia
 *GET /users/{username} - retorna informação sobre o utilizador com nome de utilizador username.
 */
public class GetUser extends BaseCommand
{
	
	private String username;
	private final UserRepository repository;
	
	/**
	 * The {@link CommandParser.Node.content} to be used (between "{" and "}", see
	 * {@link CommandParser#Node#isPlaceHolderNode()}) in the
	 * {@link CommandParser#Node} that has the {@code GetUser#Factory} in
	 * the field {@link CommandParser#Node#factory}.
	 */
	public static final String USERNAME = "username";
	
	/**
	 * Class that implements the {@link GetUser} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory 
	{
		private final UserRepository repository;
		
		public Factory(UserRepository repository)
		{
			this.repository = repository;
		}
		
		/**
		 * @see CommandFactory.newInstance
		 * Suplies the {@code GetUser} constructor with a {@code UsersRepository}
		 * and with the parameter {@link pathholderParameter} stored inside the
		 * Map {@link parameters}, to generate a new instance of {@code GetUser}.
		 */
		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			return new GetUser(repository, parameters);
		}
	}
	

	private GetUser(UserRepository repository, Map<String, String> parameters)
	{
		super(parameters);
		this.username=parameters.get(USERNAME);
		this.repository=repository;
	}

	@Override
	protected String[] getMandatoryParameters() {
		return new String[] {USERNAME};
	}

	@Override
	protected void internalExecute(ResultOutputMethod out)
			throws CommandException, IOException
	{
		UserInterface user = repository.getUserByUsername(username);
		out.giveResults(user);
	}

}
