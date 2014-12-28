package app.resultsAndOutputMethods;

import java.io.IOException;
import java.io.OutputStream;

import app.elements.DatabaseElement;

/**
 * Class that defines the contract for all output methods of results that give
 * the results to an {@link OutputStream}.
 * 
 * Note: none of the methods from this class should {@link OutputStream#close()}
 * the {@code OutputStream} (that is not their responsibility, that's the
 * responsibility of who instantiates the {@code OutputStream}).
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
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
	 * @see app.resultsOutputMethod.ResultsOutputMethod#giveResults(DatabaseElement[])
	 * 
	 *      Sends the results to
	 *      {@link ResultOutputMethodToStream#internalGiveResults(Object[])} ,
	 *      then gives them to
	 *      {@link ResultOutputMethodToStream#writeAndFlushOutputStream(byte[])}
	 *      .
	 * 
	 *      Implementation Notes: This implementation of
	 *      {@code giveResults(Object...)} won't work as expected if: <li>more
	 *      than one parameters are passed to this method (separated by ",") and
	 *      if one (or more than one) of the parameters passed is an array (if
	 *      is passed only a single array as a parameter and there are no more
	 *      parameters, there's no problem); <li>an array with primitive
	 *      elements is passed as a parameter.
	 */
	@Override
	public void giveResults(DatabaseElement... results) throws IOException
	{
		this.writeAndFlushOutputStream(this.internalGiveResults(results));
	}

	/**
	 * Receives the results and gives them back (as an array of {@code byte}s)
	 * in the way defined by this method.
	 * 
	 * @param results
	 *            The results to be output by the method.
	 * @return The results in an array of {@code byte}s.
	 */
	protected abstract byte[] internalGiveResults(Object[] results);

	/**
	 * Receives the results as an array of {@code byte}s and sends them to the
	 * {@code OutputStream}.
	 * 
	 * @param result
	 *            The results to be output by the method.
	 * @throws IOException
	 */
	private void writeAndFlushOutputStream(byte[] result) throws IOException
	{
		this.out.write(result);
		out.flush();
	}

}
