package parserCommands.exceptions;

import java.text.MessageFormat;

/**
 * Extends {@link CommandException}.
 */
@SuppressWarnings("serial")
public class InvalidParameterValueException extends CommandException{

	public InvalidParameterValueException(String name, String value){
		super(MessageFormat.format(
				"Demanding parameter with name {0} has invalid value {1}.",
				name, value));
	}
	
	public InvalidParameterValueException(String message){
		super(message);
	}
	
	public InvalidParameterValueException(String message, Throwable cause){
		super(message, cause);
	}
}
