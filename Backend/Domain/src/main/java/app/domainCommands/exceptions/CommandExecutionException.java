package app.domainCommands.exceptions;

/**
 * Super class of all the exceptions originated in the {@code Command}s.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
@SuppressWarnings("serial")
public class CommandExecutionException extends Exception{

	public CommandExecutionException() {
	}

	public CommandExecutionException(String message) {
		super(message);
	}

	public CommandExecutionException(String message, Throwable cause) {
		super(message, cause);
	}
}
