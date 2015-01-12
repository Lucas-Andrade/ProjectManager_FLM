package outputMethods.destination;

import java.io.IOException;

/**
 * Class that sends the results to the desired output.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 28/12/2014
 */
public interface Writable
{

	/**
	 * Writes the results in the desired output.
	 * 
	 * @param info
	 *            The results.
	 * @throws IOException
	 */
	public void write(String info) throws IOException;

}