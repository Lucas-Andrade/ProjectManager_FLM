package app.commands;

import java.io.IOException;
import java.util.Map;

import app.commands.exceptions.CommandException;
import app.repository.UserRepository;
import app.resultsOutputMethods.ResultOutputMethod;

/**
 * @author amiguinhos do Maia
 * GET /users - retorna a lista de utilizadores
 */
public class GetUsers extends BaseCommand implements Command
{

	private final UserRepository repository;
	
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

		@Override
		public Command newInstance(Map<String, String> parameters)
		{
			return new GetUsers(repository, parameters);
		}
	}
	
	public GetUsers(UserRepository repository, Map<String, String> parameters)
	{
		super(parameters);
		this.repository = repository;
	}
	
	@Override
	public void internalExecute(ResultOutputMethod out) throws CommandException, IOException
	{
		out.giveResults(repository.getAllUsers());
	}

	@Override
	protected String[] getMandatoryParametres() {
		return null;
	}

}
