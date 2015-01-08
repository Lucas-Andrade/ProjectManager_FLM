package app.forConsole.resultsAndOutputMethods.outputDestination;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class that sends the results to file.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 28/12/2014
 */
public class ToFile implements Writable
{

	/**
	 * String with the path and name of the destination file.
	 */
	private String pathToDestinationFile;

	/**
	 * Constructor for this class.
	 * 
	 * @param pathToDestinationFile
	 *            String with the path and name of the destination file.
	 */
	public ToFile(String pathToDestinationFile)
	{
		this.pathToDestinationFile = pathToDestinationFile;
	}

	/**
	 * @see Writable#write(String)
	 */
	@Override
	public void write(String info) throws IOException
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(
				pathToDestinationFile));
		String[] lines = info.split("\n");
		for (String line : lines)
		{
			writer.write(line);
			writer.newLine();
		}
		writer.close();
	}

}