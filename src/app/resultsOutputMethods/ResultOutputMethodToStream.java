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

	/**
	 * @see app.resultsOutputMethod.ResultsOutputMethod#giveResults(java.io.OutputStream)
	 * 
	 * Implementation Notes:
	 * This implementation of {@code giveResults(Object...)} won't work as expected
	 * if:
	 *  - more than one parameters are passed to this method (separated by ",") and
	 *    if one (or more than one) of the parameters passed is an array;
	 *  - an array with primitive elements is passed as a parameter.
	 */
	@Override
	public void giveResults(Object... result) throws IOException
	{
		this.writeAndFlushOutputStream(this.internalGiveResults(result));
	}

	/**
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
