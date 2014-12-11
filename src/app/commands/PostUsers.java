package App.commands;

import java.util.Map;

import App.repository.UsersRepository;

/**
 * POST /users - cria um novo utilizador, dados os seguintes parâmetros
 * username - nome único do utilizador;
 * password - palavra-chave do utilizador;
 * email - email único do utilizador;
 * fullname - nome completo (opcional)
 *
 */
public class PostUsers extends BaseCommand implements Command
{
	/**
	 * Class that implements the {@link GetProducts} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory {

		private final UsersRepository repository;
		private String username;
		private String password;
		private String email;
		private String fullname;
		
		public Factory(UsersRepository repository)
		{
			this.repository = repository;
		}
		
		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			if(parameters.get(username)==null||parameters.get(username)==null)
				throw new IllegalArgumentException();
			return new PostUsers();
		}
		
	}
	@Override
	public void execute() {
		
		
	}

}
