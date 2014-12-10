package app.resultsOutputMethods;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 */
public class ResultToString implements ResultOutputMethod
{

	/**
	 * 
	 */
	OutputStream out;
	
	/**
	 * @param out
	 */
	public ResultToString(OutputStream out)
	{
		this.out = out;
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
