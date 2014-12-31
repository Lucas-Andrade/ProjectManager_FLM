package app.resultsAndOutputMethods;

import java.io.FileNotFoundException;
import java.io.IOException;

import app.elements.DatabaseElement;
import app.resultsAndOutputMethods.outputDestination.ToConsole;
import app.resultsAndOutputMethods.outputDestination.ToFile;
import app.resultsAndOutputMethods.outputDestination.Writable;
import app.resultsAndOutputMethods.outputFormat.TextParser;

public class Result
{

	private final DatabaseElement[] results;
	private Writable destination;
	private TextParser format;

	public Result(DatabaseElement[] results, String destination, String format)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, FileNotFoundException
	{
		// String formatClassCompleteName = new
		// TextParser().getClass().getPackage().getName() + ".To" +
		// format.substring(0, 1).toUpperCase() + format.substring(1,
		// format.length()).toLowerCase();
//		String formatClassCompleteName = TextParser.class.getPackage()
//				.getName()
//				+ ".To"
//				+ format.substring(0, 1).toUpperCase()
//				+ format.substring(1, format.length()).toLowerCase();
		String formatClassCompleteName = TextParser.class.getPackage()
				.getName() + ".To" + format;
		this.format = (TextParser) Class.forName(formatClassCompleteName)
				.newInstance();

		this.destination = (destination == "console") ? new ToConsole()
				: new ToFile(destination);

		this.results = results;
	}

	public void showResults() throws IOException
	{
		for (DatabaseElement result : results)
			destination.write(format.parse(result.getJson()));
	}

}