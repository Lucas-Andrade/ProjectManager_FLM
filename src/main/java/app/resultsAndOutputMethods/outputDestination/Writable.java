package app.resultsAndOutputMethods.outputDestination;

import java.io.IOException;

public interface Writable
{

	public void write(String info) throws IOException;

}