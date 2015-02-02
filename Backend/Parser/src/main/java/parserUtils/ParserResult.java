package parserUtils;

import java.io.IOException;

import app.AppElement;

public interface ParserResult {

	/**
	 * @return the results of the {@code internalCall}
	 */
	public AppElement[] getResults();

	/**
	 * Formats the results according to {@code format} and sends them to
	 * {@code destination}.
	 * 
	 * @throws IOException
	 */
	public void showResults() throws IOException;
}
