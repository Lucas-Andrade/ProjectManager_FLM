package app.resultsAndOutputMethods.outputDestination;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ToFile implements Writable{

	private OutputStream outStream;
	
	public ToFile(String pathToDestinationFile) throws FileNotFoundException
	{
		this.outStream = new FileOutputStream(pathToDestinationFile);
	}
	
	//TODO concordam?
	// Each ToFile object can only be use the write method once,
	// because the stream is closed in the end.
	@Override
	public void write(String info) throws IOException {
		this.outStream.write(info.getBytes());
		this.outStream.flush();
		this.outStream.close();
	}

}
