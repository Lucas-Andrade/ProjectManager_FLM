package app.commands;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import app.commands.exceptions.CommandException;
import app.repository.UserRepository;
import app.resultsOutputMethods.ResultOutputMethod;

/**
 * POST /users - cria um novo utilizador, dados os seguintes parâmetros
 * username - nome único do utilizador;
 * password - palavra-chave do utilizador;
 * email - email único do utilizador;
 * fullname - nome completo (opcional)
 *
 */
public class PostUsers extends BasePostCommand
{
	public PostUsers(UserRepository repository, Map<String, String> parameters) {
		super(repository, parameters);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Class that implements the {@link GetProducts} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory {

		private final UserRepository repository;
		private String username;
		private String password;
		private String email;
		private String fullname;
		private static final String USERNAME = "username";
		private static final String PASSWORD = "password";
		private static final String EMAIL = "email";
		private static final String FULLNAME = "fullname";
		
		public Factory(UserRepository repository)
		{
			this.repository = repository;
		}
		
		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			if(parameters.get(username)==null||parameters.get(username)==null)
				throw new IllegalArgumentException();
			return new PostUsers(repository, parameters);
		}
		
	}

	@Override
	protected String[] getDemandingParametres() {
		return {USERNAME, PASSWORD, EMAIL, FULLNAME};
	}

	@Override
	protected void internalPostExecute(ResultOutputMethod out)
			throws CommandException, IOException
	{
		// TODO Auto-generated method stub
		
	}


}
