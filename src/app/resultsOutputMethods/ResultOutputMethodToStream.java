/**
 * 
 */
package app.resultsOutputMethods;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Class that has an {@link OutputStream} to give the results. None of the
 * methods from this class should {@link OutputStream#close()} the
 * {@code OutputStream} (that is not their responsibility, that's the
 * responsibility of who instantiates the {@code OutputStream}).
 */
public abstract class ResultOutputMethodToStream implements ResultOutputMethod
{

	/**
	 * The {@link OutputStream} for the results.
	 */
	OutputStream out;

	/**
	 * The constructor for a {@code ResultOutputMethodToStream}.
	 * 
	 * @param out
	 *            The {@link OutputStream} for the results.
	 */
	public ResultOutputMethodToStream(OutputStream out)
	{
		super();
		this.out = out;
	}

	/**
	 * @see app.resultsOutputMethod.ResultsOutputMethod#giveResults(java.io.OutputStream)
	 * 
	 * 
	 * 
	 *      Implementation Notes: This implementation of
	 *      {@code giveResults(Object...)} won't work as expected if: <li>more
	 *      than one parameters are passed to this method (separated by ",") and
	 *      if one (or more than one) of the parameters passed is an array (if
	 *      is passed only a single array as a parameter and there are no more
	 *      parameters, there's no problem); <li>an array with primitive
	 *      elements is passed as a parameter; <li>the object doesn't have an
	 *      Override of {@link Object#toString()}.
	 */
	@Override
	public void giveResults(Object... result) throws IOException
	{
		this.writeAndFlushOutputStream(this.internalGiveResults(result));
	}

	/**
	 * Receives the results and gives them back in the way defined by the output
	 * method.
	 * 
	 * @param results
	 * @return
	 */
	protected abstract byte[] internalGiveResults(Object[] results);

	/**
	 * 
	 * @param result
	 * @throws IOException
	 */
	private void writeAndFlushOutputStream(byte[] result) throws IOException
	{
		this.out.write(result);
		out.flush();
	}

}
