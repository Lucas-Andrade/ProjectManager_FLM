package app.commands;

import java.io.IOException;

import app.resultsOutputMethods.ResultOutputMethodToStream;

/**
 * Contract to be supported by all {@code Command}s. Instances cannot be
 * executed multiple times.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public interface Command
{
	/**
	 * Executes the {@code Command} and produces the Results.
	 * 
	 * @param out
	 *            The {@link ResultOutputMethodToStream} that receives the
	 *            Results, treats them and gives them to a Stream.
	 * @throws app.commands.exceptions.CommandException
	 * @throws IOException
	 */
	void execute(ResultOutputMethodToStream out)
			throws app.commands.exceptions.CommandException, IOException;
}
