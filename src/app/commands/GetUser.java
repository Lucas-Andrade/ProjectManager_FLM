package app.commands;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import app.elements.UserInterface;
import app.repository.UsersRepository;

/**
 * @author amiguinhos do Maia
 *GET /users/{username} - retorna informação sobre o utilizador com nome de utilizador username.
 */
public class GetUser implements Command
{
	
	private String username;
	private final UsersRepository repository;
	
	/**
	 * The {@link CommandParser.Node.content} to be used (between "{" and "}", see
	 * {@link CommandParser.Node.isPlaceHolderNode}) in the
	 * {@code CommandParser.Node} that has the {@code GetUser.Factory} in
	 * the field {@link CommandParser.Node.factory}.
	 */
	public static final String pathholderParameter = "username";
	
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
		
		/**
		 * @see CommandFactory.newInstance
		 * Suplies the {@code GetUser} constructor with a {@code UsersRepository}
		 * and with the parameter {@link pathholderParameter} stored inside the
		 * Map {@link parameters}, to generate a new instance of {@code GetUser}.
		 */
		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			return new GetUser(repository, parameters.get(pathholderParameter));
		}
	}
	

	private GetUser(UsersRepository repository, String username)
	{
		this.username=username;
		this.repository=repository;
	}
	
	@Override
	public void execute(OutputStream out) throws IOException
	{
		UserInterface user = repository.getUserByUsername(username);
		out.write(user.toString().getBytes());
		out.close();
	}

}
