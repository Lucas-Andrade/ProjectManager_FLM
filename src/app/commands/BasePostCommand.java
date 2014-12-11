/**
 * 
 */
package app.commands;

import java.io.IOException;
import java.util.Map;

import app.commands.exceptions.CommandException;
import app.commands.exceptions.DemandingParameterNotPresentException;
import app.repository.UserRepository;
import app.resultsOutputMethods.ResultOutputMethod;

/**
 *
 */
public abstract class BasePostCommand extends BaseCommand
{
	
	public static final String USERNAME = "username";
	
	public static final String PASSWORD = "password";
	
	private final UserRepository repository;
	
	public BasePostCommand(UserRepository repository, Map<String, String> parameters)
	{
		super(parameters);
		this.repository = repository;
	}

	/* (non-Javadoc)
	 * @see app.commands.BaseCommand#internalExecute(app.resultsOutputMethods.ResultOutputMethod)
	 */
	@Override
	protected void internalExecute(ResultOutputMethod out)
			throws CommandException, IOException
	{
		validateDemandingParameters(USERNAME, PASSWORD);
		String username = parameters.get(USERNAME);
		String password = parameters.get(PASSWORD);
		if (authenticateUser(username, password))
			internalPostExecute(out);
		else
			out.giveResults("username e/ou password erradas");
	}
	
	protected boolean authenticateUser(String username, String password)
	{
		return repository.isPasswordCorrectForUser(username, password);
	}
	
	abstract protected void internalPostExecute(ResultOutputMethod out) throws app.commands.exceptions.CommandException, IOException;

}
