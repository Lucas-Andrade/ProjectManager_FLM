package App.commands;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import App.elements.User;
import App.elements.UserInterface;
import App.repository.UsersRepository;

/**
 * @author amiguinhos do Maia
 * GET /users - retorna a lista de utilizadores
 */
public class GetUsers implements Command
{

	private final UsersRepository repository;
	
	/**
	 * Class that implements the {@link GetUser} factory, according to the
	 * AbstratFactory design pattern.
	 */
	public static class Factory implements CommandFactory
	{
		private final UsersRepository repository;

		public Factory(UsersRepository repository)
		{
			this.repository = repository;
		}

		@Override
		public Command newInstance(Map<String, String> parameters)
		{
			return new GetUsers(repository);
		}
	}
	
	public GetUsers(UsersRepository repository)
	{
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

}
