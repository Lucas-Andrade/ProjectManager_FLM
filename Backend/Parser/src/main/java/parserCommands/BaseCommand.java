package parserCommands;

import java.util.Map;
import java.util.concurrent.Callable;

import outputMethods.Result;
import parserCommands.exceptions.MandatoryParameterNotPresentException;
import app.AppElement;
import app.elements.User;

/**
 * Abstract Command to be supported by all Commands. Establishes the model to be
 * followed by the Commands and implements the {@link Callable<Result>}.
 * interface.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @param <Result>
 *            The object containing the results of a Command execution ({@see
 *            this#call()}).
 * @since 08/12/2014
 */
public abstract class BaseCommand implements Callable<Result>{

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
	public BaseCommand(Map<String, String> parameters){
		super();
		this.parameters = parameters;
	}

	/**
	 * This method creates an object Result with the {@code DatabaseElement}s
	 * returned by {@code this#internalCall()}, after validating the mandatory
	 * parameters (if not stops the execution).
	 * @throws Exception 
	 */
	@Override
	public Result call() throws Exception {
		validateDemandingParameters(getMandatoryParameters());
		return new Result(internalCall(), null, null);
	}

	/**
	 * This method performs the specific instructions for each command that extends this class.
	 * @see Callable#call()
	 * 
	 * @return An array of {@code DatabaseElement} 
	 * @throws Exception 
	 */
	abstract protected AppElement[] internalCall() throws Exception;

	/**
	 * Checks if all mandatory arguments are in the
	 * {@link BaseCommand#parameters} (by searching their names), if not throws
	 * {@code MandatoryParameterNotPresentException}.
	 * 
	 * @param parameterNames    The mandatory arguments names.
	 * @throws MandatoryParameterNotPresentException
	 *             The exception to be thrown if not all mandatory arguments are
	 *             present in the {@link BaseCommand#parameters}.
	 */
	protected void validateDemandingParameters(String... parameterNames)
			throws MandatoryParameterNotPresentException{
		for (String name : parameterNames){
			if (!parameters.containsKey(name)){
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
	 * @param name   The name of the argument to be returned.
	 * @return The command argument.
	 */
	protected double getParameterAsDouble(String name){
		return Double.parseDouble(parameters.get(name));
	}

	/**
	 * Gives the argument as a {@code String}.
	 * 
	 * @param name   The name of the argument to be returned.
	 * @return The command argument.
	 */
	protected String getParameterAsString(String name){
		return parameters.get(name);
	}

	/**
	 * Gives the argument as an {@code int}.
	 * 
	 * @param name    The name of the argument to be returned.
	 * @return The command argument.
	 */
	protected int getParameterAsInt(String name){
		return Integer.parseInt(parameters.get(name));
	}

	/**
	 * Gives the argument as a {@code long}.
	 * 
	 * @param name    The name of the argument to be returned.
	 * @return The command argument.
	 */
	protected long getParameterAsLong(String name){
		return Long.parseLong(parameters.get(name));
	}
}
