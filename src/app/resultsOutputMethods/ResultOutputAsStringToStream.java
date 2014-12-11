package app.resultsOutputMethods;

import java.io.OutputStream;

/**
 * Class that gives to an {@link OutputStream} the results has {@code String}s.
 */
public class ResultOutputAsStringToStream extends ResultOutputMethodToStream
{

	/**
	 * @see super()
	 */
	public ResultOutputAsStringToStream(OutputStream out)
	{
		super(out);
	}

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
