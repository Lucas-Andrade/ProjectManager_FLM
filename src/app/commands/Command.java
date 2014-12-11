
package app.commands;

import java.io.IOException;

import app.resultsOutputMethods.ResultOutputMethodToStream;


/**
 * Contract to be supported by all commands. Instances cannot be executed multiple times.
 */
public interface Command
{
	void execute(ResultOutputMethodToStream out) throws app.commands.exceptions.CommandException, IOException;
}


