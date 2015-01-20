package parserCommands;

import java.util.Map;

import outputMethods.Result;

/**
 * Abstract Command to be supported by all Commands that support different
 * output ways for results. The end result of this Command is a {@link Result}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 28/12/2014
 */
public abstract class BaseCommandResultsOutputMethod extends BaseCommand{

	/**
	 * {@code String} with the results output format argument's name. This
	 * argument is used to define how the results will be presented (in plain
	 * text, html text, application json).
	 */
	public static final String ACCEPT = "accept";

	/**
	 * {@code String} with the results output destination argument's name. This
	 * argument is used to define where the results will be presented.
	 */
	public static final String OUTPUT = "output";

	/**
	 * The constructor for {@code BaseCommandResultsOutputMethod}.
	 * 
	 * @param parameters
	 *            The Command arguments.
	 */
	public BaseCommandResultsOutputMethod(Map<String, String> parameters){
		super(parameters);
	}

	/**
	 * This method creates an object Result with the {@code DatabaseElement}s
	 * returned by {@code this#internalCall()} and the output format and
	 * destination, after validating the mandatory parameters (if not stops the
	 * execution).
	 */
	@Override
	public Result call() throws Exception{
		validateDemandingParameters(getMandatoryParameters());
		return new Result(internalCall(), getResultsOutputDestination(),
				getResultsOutputFormat());
	}

	/**
	 * This method returns the entered format by the User, if it exists.
	 * 
	 * @return The format for the results.
	 */
	private String getResultsOutputFormat(){
		return getParameterAsString(ACCEPT);
	}

	/**
	 * This method returns the entered destination by the User, if it exists.
	 * 
	 * @return The destination for the results.
	 */
	protected String getResultsOutputDestination(){
		return getParameterAsString(OUTPUT);
	}

}