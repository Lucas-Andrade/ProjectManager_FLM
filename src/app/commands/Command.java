
package app.commands;

import app.resultsOutputMethods.ResultOutputMethod;


/**
 * Contract to be supported by all commands. Instances cannot be executed multiple times.
 */
public interface Command
{
	void execute(ResultOutputMethod out) throws app.commands.exceptions.CommandException;
}


