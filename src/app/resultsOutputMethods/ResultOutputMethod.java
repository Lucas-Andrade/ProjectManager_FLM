package app.resultsOutputMethods;

import java.io.IOException;

/**
 * Interface that defines the contract for all output methods of results.
 */
public interface ResultOutputMethod
{
	/**
	 * Receives the results and gives them back in the way defined by this
	 * method.
	 * 
	 * @param result
	 *            The results to be output by the method.
	 * @throws IOException
	 */
	public void giveResults(Object... result) throws IOException;
}
