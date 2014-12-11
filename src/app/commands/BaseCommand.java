package app.commands;

import java.io.IOException;
import java.util.Map;

import app.commands.exceptions.DemandingParameterNotPresentException;
import app.resultsOutputMethods.ResultOutputMethod;
import app.resultsOutputMethods.ResultOutputMethodToStream;

public abstract class BaseCommand implements Command {

	/**
	 * The command arguments;
	 */
	protected final Map<String, String> parameters;
	
	
	public BaseCommand(Map<String, String> parameters) {
		super();
		this.parameters = parameters;
	}


	public final void execute(ResultOutputMethodToStream out) throws app.commands.exceptions.CommandException, IOException
	{	
		validateDemandingParameters(getDemandingParametres());
		internalExecute(out);
	}


	protected abstract String[] getDemandingParametres();


	abstract protected void internalExecute(ResultOutputMethod out) throws app.commands.exceptions.CommandException, IOException;


	protected void validateDemandingParameters(String ...parameterNames) throws DemandingParameterNotPresentException {
				for (String name : parameterNames) {
					if(!parameters.containsKey(name)) {
						throw new DemandingParameterNotPresentException(name);
					}
				}
			}


	protected double getParameterAsDouble(String name) {
		return Double.parseDouble(parameters.get(name));
	}

	protected String getParameterAsString(String name) {
		return parameters.get(name);
	}
	
	protected int getParameterAsInt(String name) {
		return Integer.parseInt(parameters.get(name));
	}

	protected long getParameterAsLong(String name) {
		return Long.parseLong(parameters.get(name));
	}
}
