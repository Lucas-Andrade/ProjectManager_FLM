package App.commands;

import java.util.Map;

import App.model.Projects.UsersRepository;

/**
 * @author amiguinhos do Maia
 * GET /users - retorna a lista de utilizadores
 */
public class GetUsers implements Command {

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
			// TODO
			return null;
		}
	
}
@Override
public void execute()
{
	// TODO
}

}
