package app.commands;

import java.io.IOException;
import java.util.Map;

import app.commands.exceptions.CommandException;
import app.elements.User;
import app.elements.UserInterface;
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

	private String username;
	private String password;
	private String email;
	private String fullname;
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String EMAIL = "email";
	private static final String FULLNAME = "fullname";
	private static final String[] DEMANDING_PARAMETERS = {USERNAME, PASSWORD, EMAIL, FULLNAME};

	private final UserRepository repository;
	

	/**
	 * Class that implements the {@link GetProducts} factory, according to the 
	 * AbstratFactory design pattern. 
	 */
	public static class Factory implements CommandFactory 
	{
		private final UserRepository uRepository;
		private String username;
		
		public Factory(UserRepository uRepository)
		{
			this.uRepository = uRepository;
		}
		
		@Override
		public Command newInstance(Map<String, String> parameters) 
		{
			if(parameters.get(username)==null||parameters.get(username)==null)
				throw new IllegalArgumentException();
			return new PostUsers(uRepository,  parameters);
		}
		
	}

	
	public PostUsers(UserRepository repository, Map<String, String> parameters) {
		super(repository, parameters);
		this.repository = repository;
	}

	@Override
	protected String[] getMandatoryParametres() {
		return DEMANDING_PARAMETERS;
	}

	@Override
	protected void internalPostExecute(ResultOutputMethod out)
			throws CommandException, IOException
	{
		this.username = parameters.get(USERNAME);
		this.password = parameters.get(PASSWORD);
		this.email = parameters.get(EMAIL);
		this.fullname = parameters.get(FULLNAME);
		UserInterface[] existingUsers = repository.getAllUsers();
		for(UserInterface existingUser : existingUsers)
		{
			if(existingUser.getLoginName().equals(this.username))
				out.giveResults("The Specified Username already exists in repository.");
		}
		if(this.validEmail())
		{
			repository.addUser(new User(this.username, this.password, this.email, this.fullname));
			return;
		}
		else
			out.giveResults("The Email is not valid.");
	}

	private boolean validEmail()
	{
		if(!(email.contains("@")))
			return false;
		if(email.substring(email.indexOf("@")+1, email.length()).contains("@"))
			return false;
		if(email.lastIndexOf(".")<email.lastIndexOf("@"))
			return false;
		return true;
	}

}
