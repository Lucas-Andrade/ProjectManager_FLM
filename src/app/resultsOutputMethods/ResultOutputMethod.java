/**
 * 
 */
package app.resultsOutputMethods;

import java.io.IOException;

/**
 *
 */
public interface ResultOutputMethod
{
	public void giveResults(Object... result) throws IOException;
}
