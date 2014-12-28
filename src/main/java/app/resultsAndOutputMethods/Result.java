package app.resultsAndOutputMethods;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import app.elements.DatabaseElement;

public class Result
{

	public final DatabaseElement[] results;
	public final ResultOutputMethodToStream outputMethod;

	/**
	 * The variable that defines the default Output Stream used to send the
	 * results.
	 */
	private static final PrintStream DEFAULT_SYSTEM_OUT = System.out;

	/**
	 * The variable that defines the default Output Method used to send the
	 * results.
	 * 
	 * @see ResultOutputMethodToStream
	 */
	private static final ResultOutputMethodToStream DEFAULT_OUTMETHOD = new ResultOutputAsPlainTextToStream(
			DEFAULT_SYSTEM_OUT);

	public Result(DatabaseElement[] dbElement, String format, String destination)
			throws FileNotFoundException
	{
		this.results = dbElement;

		OutputStream out = (destination == "console") ? DEFAULT_SYSTEM_OUT
				: new FileOutputStream(destination);
		if (format == "text/plain")
			this.outputMethod = new ResultOutputAsPlainTextToStream(out);
		else if (format == "text/html")
			this.outputMethod = new ResultOutputAsHtmlTextToStream(out);
		else if (format == "application/json")
			this.outputMethod = new ResultOutputAsJsonAplicationToStream(out);
		else
			this.outputMethod = DEFAULT_OUTMETHOD;
	}

	public Result(DatabaseElement[] dbElement)
	{
		this.results = dbElement;
		outputMethod = DEFAULT_OUTMETHOD;
	}

}