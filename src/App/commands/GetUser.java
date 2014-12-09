package App.commands;

import java.util.Map;

import App.elements.UserInterface;
import App.repository.UsersRepository;

/**
 * @author amiguinhos do Maia
 *GET /users/{username} - retorna informação sobre o utilizador com nome de utilizador username.
 */
public class GetUser implements Command
{
	
	private String username;
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
			return new GetUser(repository, parameters.get("{username}"));
		}
	}
	
	private GetUser(UsersRepository repository, String username)
	{
		this.username=username;
		this.repository=repository;
	}
	
	@Override
	public void execute()
	{
		UserInterface user = repository.getUserByUsername(username);
		System.out.println(user.toString());
	}

}
