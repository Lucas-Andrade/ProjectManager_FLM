package app.resultsOutputMethods;

import java.io.OutputStream;

/**
 * Class that gives to an {@link OutputStream} the results has {@code String}s,
 * each result is presented in a different line and is defined as in
 * {@link Object#toString()}.
 * 
 * @see 
 *      app.resultsOutputMethod.ResultsOutputMethodToStream(java.io.OutputStream)
 */
public class ResultOutputAsStringToStream extends ResultOutputMethodToStream
{

	/**
	 * The constructor for a {@code ResultOutputAsStringToStream}.
	 * 
	 * @param out
	 *            The {@link OutputStream} for the results.
	 */
	public ResultOutputAsStringToStream(OutputStream out)
	{
		super(out);
	}

	/**
	 * Receives the results, inserts them in a {@code String} and gives them
	 * back as an array of {@code byte}s (to be used by the {@code OutputStream}
	 * ). The {@code String} with the results has each one of the results in a
	 * different line. This method makes use of the method
	 * {@link Object#toString()}.
	 * 
	 * @param results
	 *            The results to be output by the method.
	 * @return The results in an array of bytes.
	 * 
	 *         Implementation Notes: This implementation of
	 *         {@code internalGiveResults(Object[])} won't work as expected if
	 *         the object doesn't have an Override of {@link Object#toString()}.
	 */
	@Override
	protected byte[] internalGiveResults(Object[] results)
	{
		StringBuilder sb = new StringBuilder();
		for (Object rs : results)
		{
			sb.append(rs.toString()).append("\n");
		}
		return sb.toString().getBytes();
	}

}
