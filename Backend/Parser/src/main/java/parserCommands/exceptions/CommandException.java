package parserCommands.exceptions;

import app.domainCommands.Command;

/**
 * Base class for {@link Command} exceptions.
 */
@SuppressWarnings("serial")
public class CommandException extends Exception{

	public CommandException(){
	}

	public CommandException(String message){
		super(message);
	}

	public CommandException(String message, Throwable cause){
		super(message, cause);
	}
}
