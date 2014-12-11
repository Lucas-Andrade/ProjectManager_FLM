/**
 * 
 */
package app.commands;

import java.io.IOException;
import java.util.Map;

import app.commands.exceptions.CommandException;
import app.commands.exceptions.DemandingParameterNotPresentException;
import app.resultsOutputMethods.ResultOutputMethod;

/**
 * @author fm
 *
 */
public abstract class PostBase extends BaseCommand
{


	public PostBase(Map<String, String> parameters)
	{
		super(parameters);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see app.commands.BaseCommand#internalExecute(app.resultsOutputMethods.ResultOutputMethod)
	 */
	@Override
	protected void internalExecute(ResultOutputMethod out)
			throws CommandException, IOException
	{
		if (authenticateUser(username, password))
			internalPostExecute(out);
		else
			//TODO
	}
	
	protected boolean authenticateUser(String username, String password)
	{
		//TODO
	}
	
	abstract protected void internalPostExecute(ResultOutputMethod out) throws app.commands.exceptions.CommandException, IOException;

}
