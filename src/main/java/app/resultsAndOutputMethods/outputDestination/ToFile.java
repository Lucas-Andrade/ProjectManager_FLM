package app.resultsAndOutputMethods.outputDestination;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class ToFile implements Writable
{

	private String pathToDestinationFile;

	public ToFile(String pathToDestinationFile) throws FileNotFoundException
	{
		this.pathToDestinationFile = pathToDestinationFile;
	}

	@Override
	public void write(String info) throws IOException
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(pathToDestinationFile));
		String[] lines = info.split("\n");
		for (String line : lines)
		{
			writer.write(line);
			writer.newLine();
		}
		writer.close();
	}
	
}