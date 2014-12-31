package app.resultsAndOutputMethods.outputDestination;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ToFile implements Writable
{

	private OutputStream outStream;

	public ToFile(String pathToDestinationFile) throws FileNotFoundException
	{
		this.outStream = new FileOutputStream(pathToDestinationFile);
	}

	@Override
	public void write(String info) throws IOException
	{
		this.outStream.write(info.getBytes());
	}

	public void flushAndClose() throws IOException
	{
		this.outStream.flush();
		this.outStream.close();
	}

}