/**
 * 
 */
package app.resultsOutputMethods;

import java.io.OutputStream;

/**
 * Class that gives to an {@link OutputStream} the results.
 */
public abstract class ResultOutputMethodToStream implements ResultOutputMethod
{

	/**
	 * 
	 */
	OutputStream out;

	/**
	 * @param out
	 */
	public ResultOutputMethodToStream(OutputStream out)
	{
		super();
		this.out = out;
	}

}
