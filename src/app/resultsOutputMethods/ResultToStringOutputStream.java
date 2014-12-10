package app.resultsOutputMethods;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Class that gives to an {@code OutpuStream} the results has {@code String}s.
 */
public class ResultToStringOutputStream extends OutputResultToStream
{
	
	/**
	 * @see super
	 */
	public ResultToStringOutputStream(OutputStream out)
	{
		super(out);
	}

	/* (non-Javadoc)
	 * @see app.resultsOutputMethod.ResultsOutputMethod#giveResults(java.io.OutputStream)
	 */
	@Override
	public void giveResults(Object... result) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		for(Object rs : result)
		{
			sb.append(rs.toString()).append("\n");
		}
		this.out.write(sb.toString().getBytes());
		this.out.close();
	}

}
