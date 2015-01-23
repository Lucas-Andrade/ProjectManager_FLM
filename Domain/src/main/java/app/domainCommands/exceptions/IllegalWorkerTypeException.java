package app.domainCommands.exceptions;

/**
 * This exception is thrown if the type of worker does not correspond to any of the
 * expected possibilities.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
@SuppressWarnings("serial")
public class IllegalWorkerTypeException extends CommandExecutionException {

	public IllegalWorkerTypeException() {
	}

	public IllegalWorkerTypeException(String message) {
		super(message);
	}

	public IllegalWorkerTypeException(String message, Throwable cause) {
		super(message, cause);
	}
}
