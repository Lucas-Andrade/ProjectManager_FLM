package app.resultsAndOutputMethods;

import java.io.FileNotFoundException;
import java.io.IOException;

import app.elements.DatabaseElement;
import app.resultsAndOutputMethods.outputDestination.ToConsole;
import app.resultsAndOutputMethods.outputDestination.ToFile;
import app.resultsAndOutputMethods.outputDestination.Writable;
import app.resultsAndOutputMethods.outputFormat.TextParser;
import app.resultsAndOutputMethods.outputFormat.ToPlainText;

/**
 * Class that stores the results of a Command execution and sends them to a
 * destination with the desired format.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 28/12/2014
 */
public class Result
{

	/**
	 * The results from a Command execution.
	 */
	private final DatabaseElement[] results;

	/**
	 * The destination to where the results are to be sent.
	 */
	private Writable destination;

	/**
	 * The format that the results should take before being sent.
	 */
	private TextParser format;

	/**
	 * The constructor for a {@code Result}.
	 * 
	 * @param results
	 *            The results from a Command execution.
	 * @param destination
	 *            A String with the name of the destination to where the results
	 *            are to be sent.
	 * @param format
	 *            A String with the name of the format that the results should
	 *            take before being sent.
	 * @throws ClassNotFoundException
	 *             Exception thrown when the name of the format doesn't
	 *             correspond to a valid format.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws FileNotFoundException
	 *             Exception thrown when the name of the destination doesn't
	 *             correspond to a valid destination.
	 */
	public Result(DatabaseElement[] results, String destination, String format)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, FileNotFoundException
	{
		// String formatClassCompleteName = new
		// TextParser().getClass().getPackage().getName() + ".To" +
		// format.substring(0, 1).toUpperCase() + format.substring(1,
		// format.length()).toLowerCase();
		// String formatClassCompleteName = TextParser.class.getPackage()
		// .getName()
		// + ".To"
		// + format.substring(0, 1).toUpperCase()
		// + format.substring(1, format.length()).toLowerCase();
		if (format == null || format == "")
		{
			format = ToPlainText.class.getName();
			format = format.substring(format.lastIndexOf('.') + 3);
		}
			
		String formatClassCompleteName = TextParser.class.getPackage()
				.getName() + ".To" + format;
		this.format = (TextParser) Class.forName(formatClassCompleteName)
				.newInstance();

		this.destination = (destination == "console" || destination == ""
				|| destination == null) ? new ToConsole()

				: new ToFile(destination);

		this.results = results;
	}

	/**
	 * Formats the results according to {@code format} and sends them to
	 * {@code destination}.
	 * 
	 * @throws IOException
	 */
	public void showResults() throws IOException
	{
		for (DatabaseElement result : results)
			destination.write(format.parse(result.getJson()));
	}

	
}
