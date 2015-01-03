package app.commands;

import java.util.Map;
import app.commands.exceptions.InvalidParameterValueException;
import app.resultsAndOutputMethods.Result;
import app.resultsAndOutputMethods.outputFormat.ToHtml;
import app.resultsAndOutputMethods.outputFormat.ToJson;
import app.resultsAndOutputMethods.outputFormat.ToPlainText;

/**
 * Abstract Command to be supported by all Commands that support different
 * output ways for results. The end result of this Command is a {@link Result}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 28/12/2014
 */
public abstract class BaseCommandResultsOutputMethod extends BaseCommand
{

	/**
	 * {@code String} with the results output format argument's name. This
	 * argument is used to define how the results will be presented (in plain
	 * text, html text, application json).
	 */
	public static final String ACCEPT = "accept";

	/**
	 * {@code String} with the results output destination argument's name. This
	 * argument is used to define where the results will be presented (console
	 * or other).
	 */
	public static final String OUTPUT = "output";

	/**
	 * The constructor for {@code BaseCommandResultsOutputMethod}.
	 * 
	 * @param parameters
	 *            The Command arguments.
	 */
	public BaseCommandResultsOutputMethod(Map<String, String> parameters)
	{
		super(parameters);
	}

	/**
	 * This method creates an object Result with the {@code DatabaseElement}s
	 * returned by {@code this#internalCall()} and the output format and
	 * destination, after validating the mandatory parameters (if not stops the
	 * execution).
	 */
	@Override
	public Result call() throws Exception
	{
		validateDemandingParameters(getMandatoryParameters());
		return new Result(internalCall(), getResultsOutputFormat(),
				getResultsOutputDestination());
	}

	private String getResultsOutputFormat() throws Exception
	{
		String accept = getParameterAsString(ACCEPT).toLowerCase();

		if (accept == null || accept == ""
				|| accept.equalsIgnoreCase("text/plain"))
		{
			return new ToPlainText()
					.getClass()
					.getName()
					.substring(ToPlainText.class.getName().lastIndexOf('.') + 3); // This
			// String
			// is
			// case
			// sensitive
			// and
			// must
			// match
			// the
			// name
			// of
			// the
			// Object
			// that
			// is
			// going
			// to be
			// used.
			// {@see Result}
		} else if (accept.equalsIgnoreCase("text/html"))
		{
			return new ToHtml().getClass().getName()
					.substring(ToHtml.class.getName().lastIndexOf('.') + 3); // This
			// String is
			// case
			// sensitive
			// and must
			// match the
			// name of
			// the
			// Object
			// that is
			// going to
			// be used.
			// {@see Result}
		} else if (accept.equalsIgnoreCase("application/json"))
		{
			return new ToJson().getClass().getName()
					.substring(ToJson.class.getName().lastIndexOf('.') + 3); // This
			// String is
			// case
			// sensitive
			// and must
			// match the
			// name of
			// the
			// Object
			// that is
			// going to
			// be used.
			// {@see Result}
		} else
			throw new InvalidParameterValueException(
					"Unrecognised accept format.");
	}

	/**
	 * This method checks if the user entered the destination in which he want
	 * to display the results output ("output"). If the user has not entered the
	 * format in which he want to display the output, it will be presented in
	 * text;
	 * 
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	protected String getResultsOutputDestination() throws Exception
	{
		return getParameterAsString(OUTPUT).toLowerCase();
	}

}
