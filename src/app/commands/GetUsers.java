package app.commands;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import app.commands.exceptions.CommandException;
import app.elements.UserInterface;
import app.repository.UserRepository;

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
	public void execute(OutputStream out) throws IOException 
	{
		UserInterface[] users = repository.getAllUsers();
		
		StringBuilder builder = new StringBuilder();
		for(UserInterface user : users)
		{
			builder.append(user.toString()).append("\n");
		}
		
		out.write(builder.toString().getBytes());
		out.close();
	}

	@Override
	protected String[] getDemandingParametres() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void internalExecute() throws CommandException {
		// TODO Auto-generated method stub
		
	}

}
