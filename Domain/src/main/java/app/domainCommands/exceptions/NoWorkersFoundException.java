package app.domainCommands.exceptions;

/**
 * This exception is thrown if a {@code Project} does not have any workers contained
 * in it's {@code Team}, but was expected to.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
@SuppressWarnings("serial")
public class NoWorkersFoundException extends CommandExecutionException{

	public NoWorkersFoundException() {
	}

	public NoWorkersFoundException(String message) {
		super(message);
	}

	public NoWorkersFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
