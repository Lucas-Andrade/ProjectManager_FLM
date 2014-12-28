package app.resultsAndOutputMethods;

import java.io.PrintStream;

import app.elements.DatabaseElement;

public class Result {

	private final DatabaseElement[] results;
	private String format;
	private String destination;
	
	/**
	 * The variable that defines the default Output Stream used to send the results.
	 */
	private static final PrintStream DEFAULT_SYSTEM_OUT = System.out;

	/**
	 * The variable that defines the default Output Method used to send the results.
	 * 
	 * @see ResultOutputMethodToStream
	 */
	private static final ResultOutputMethodToStream DEFAULT_OUTPUT_METHOD = new ResultOutputAsPlainTextToStream(DEFAULT_SYSTEM_OUT);

	public Result (DatabaseElement[] dbElement, String format, String destination) {
		this.results=dbElement;
		this.format=format;
		this.destination=destination;
	}

	/**
	 * @return the format
	 */
	public String getFormat()
	{
		return format;
	}

	/**
	 * @param format the format to set
	 */
	public void setFormat(String format)
	{
		this.format = format;
	}

	/**
	 * @return the destination
	 */
	public String getDestination()
	{
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination)
	{
		this.destination = destination;
	}

	/**
	 * @return the dbElement
	 */
	public DatabaseElement[] getResults()
	{
		return results;
	}

}
