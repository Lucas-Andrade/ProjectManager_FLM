package app.commands;

import java.util.Map;
import java.util.concurrent.Callable;

import app.commands.exceptions.InvalidParameterValueException;
import app.elements.DatabaseElement;
import app.resultsOutputMethods.ResultOutputMethodToStream;

public abstract class BaseCommandResultsOutputMethod extends BaseCommand
{

	
	/**
	 * {@code String} with the Output Format argument's name. This argument is used to define how the 
	 * results will be presented (text, html or json).
	 */
	public static String ACCEPT = "accept";

	/**
	 * {@code String} with the results output destination. This argument is used to define where the 
	 * results will be presented (console or an output file).
	 */
	public static final String OUTPUT = "output";

	/**
	 * An array of {@code String}s with the names of all mandatory arguments.
	 */
	public static final String[] OUTPUT_PARAMETERS = new String[] {ACCEPT, OUTPUT};

	
	
	public BaseCommandResultsOutputMethod(Map<String, String> parameters)
	{
		super(parameters);
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * This method creates an object Result with the database Element and the output format and destination
	 * @see Callable#call()
	 * 
	 */
	@Override
	public Result call() throws Exception
	{
		String format = getResultsOutputFormat();
		String destination = getParameterAsString(OUTPUT);
		DatabaseElement dbElement =internalCall();
		return new result(dbElement, format, destination);
	}
	
	@Override
	abstract protected DatabaseElement internalCall() throws Exception;
	

	/**
	 * This method checks if the user entered the format in which he want to display the results output 
	 * ("accept").
	 * If the user has not entered the format in which he want to display the output, it will be presented 
	 * in text; 
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	protected String getResultsOutputFormat()
			throws Exception
	{
		String accept = getParameterAsString(ACCEPT);
		
		if (accept == null||accept.equalsIgnoreCase("text/plain"))
		{
			ACCEPT="text";
		}
		else if(accept.equalsIgnoreCase("text/html"))
		{
			ACCEPT="html";	
		}
		else if(accept.equalsIgnoreCase("aplication/json"))
		{
			ACCEPT="json";	
		}
		else
			throw new InvalidParameterValueException("Unrecognised accept format.");
		return ACCEPT;
	}

	

}
