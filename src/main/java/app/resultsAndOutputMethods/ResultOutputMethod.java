package app.resultsAndOutputMethods;

import java.io.IOException;

import app.elements.DatabaseElement;

/**
 * Interface that defines the contract for all output methods of results.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public interface ResultOutputMethod
{
	/**
	 * Receives the results and gives them back in the way defined by this
	 * method.
	 * 
	 * @param results
	 *            The results to be output by the method.
	 * @throws IOException
	 */
	public void giveResults(DatabaseElement... results) throws IOException;
}
