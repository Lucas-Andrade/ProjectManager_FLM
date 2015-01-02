package app.resultsAndOutputMethods.outputDestination;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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
		OutputStream outStream = new FileOutputStream(pathToDestinationFile);
		outStream.write(info.getBytes());
		outStream.close();
	}

}