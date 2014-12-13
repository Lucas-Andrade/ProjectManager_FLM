package app.commands;

import java.io.IOException;
import java.util.Map;

import app.commands.exceptions.MandatoryParameterNotPresentException;
import app.resultsOutputMethods.ResultOutputMethod;
import app.resultsOutputMethods.ResultOutputMethodToStream;

/**
 * Abstract {@link Command} to be supported by all {@code Command}s. Establishes
 * the model to be followed by the {@code Command}s.
 */
public abstract class BaseCommand implements Command
{

	/**
	 * The {@code Command} arguments. These are required by some {@code Command}
	 * s, supplying required information (passed by the {@link User}) for the
	 * correct execution of the {@code Command}. Some of these arguments are
	 * mandatory for the execution of the {@code Command}, while others are
	 * optional. The arguments are stored in a {@link Map} of {@code String}s,
	 * the Keys of the {@code Map} correspond to the names of the arguments and
	 * the Values to the parameters.
	 */
	protected final Map<String, String> parameters;

	/**
	 * The Constructor of {@code BaseCommand}s.
	 * 
	 * @param parameters
	 *            The {@code Command} arguments.
	 */
	public BaseCommand(Map<String, String> parameters)
	{
		super();
		this.parameters = parameters;
	}

	/**
	 * Checks if all mandatory arguments are in the
	 * {@link BaseCommand#parameters}, if yes proceeds with the execution of the
	 * {@code Command}.
	 * 
	 * @see BaseCommand#validateDemandingParameters(String...)
	 * @see BaseCommand#internalExecute(ResultOutputMethodToStream)
	 */
	@Override
	public final void execute(ResultOutputMethodToStream out)
			throws app.commands.exceptions.CommandException, IOException
	{
		validateDemandingParameters(getMandatoryParameters());
		internalExecute(out);
	}

	/**
	 * @see Command#execute(ResultOutputMethodToStream)
	 * 
	 * @param out
	 * @throws app.commands.exceptions.CommandException
	 * @throws IOException
	 */
	abstract protected void internalExecute(ResultOutputMethod out)
			throws app.commands.exceptions.CommandException, IOException;

	/**
	 * Checks if all mandatory arguments are in the
	 * {@link BaseCommand#parameters} (by searching their names), if not throws
	 * {@code MandatoryParameterNotPresentException}.
	 * 
	 * @param parameterNames
	 *            The mandatory arguments names.
	 * @throws MandatoryParameterNotPresentException
	 *             The exception to be thrown if not all mandatory arguments are
	 *             present in the {@link BaseCommand#parameters}.
	 */
	protected void validateDemandingParameters(String... parameterNames)
			throws MandatoryParameterNotPresentException
	{
		for (String name : parameterNames)
		{
			if (!parameters.containsKey(name))
			{
				throw new MandatoryParameterNotPresentException(name);
			}
		}
	}

	/**
	 * @return An array of {@code String}s with the names of all mandatory
	 *         arguments.
	 */
	protected abstract String[] getMandatoryParameters();

	/**
	 * Gives the argument as a {@code double}.
	 * 
	 * @param name
	 *            The name of the argument to be returned.
	 * @return The command argument.
	 */
	protected double getParameterAsDouble(String name)
	{
		return Double.parseDouble(parameters.get(name));
	}

	/**
	 * Gives the argument as a {@code String}.
	 * 
	 * @param name
	 *            The name of the argument to be returned.
	 * @return The command argument.
	 */
	protected String getParameterAsString(String name)
	{
		return parameters.get(name);
	}

	/**
	 * Gives the argument as an {@code int}.
	 * 
	 * @param name
	 *            The name of the argument to be returned.
	 * @return The command argument.
	 */
	protected int getParameterAsInt(String name)
	{
		return Integer.parseInt(parameters.get(name));
	}

	/**
	 * Gives the argument as a {@code long}.
	 * 
	 * @param name
	 *            The name of the argument to be returned.
	 * @return The command argument.
	 */
	protected long getParameterAsLong(String name)
	{
		return Long.parseLong(parameters.get(name));
	}

}
