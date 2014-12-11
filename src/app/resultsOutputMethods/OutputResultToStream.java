/**
 * 
 */
package app.resultsOutputMethods;

import java.io.OutputStream;

/**
 * Class that gives to an {@code OutpuStream} the results.
 */
public abstract class OutputResultToStream implements ResultOutputMethod
{

	/**
	 * 
	 */
	OutputStream out;

	/**
	 * @param out
	 */
	public OutputResultToStream(OutputStream out)
	{
		super();
		this.out = out;
	}

}
