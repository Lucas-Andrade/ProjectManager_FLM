package app.domainCommands.exceptions;

/**
 * This exception is thrown if the {@code cost} of a {@code AWorker} or {@code Local}
 * is a negative number.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
@SuppressWarnings("serial")
public class CostOutOfBoundsException extends CommandExecutionException{

	public CostOutOfBoundsException() {
	}

	public CostOutOfBoundsException(String message) {
		super(message);
	}

	public CostOutOfBoundsException(String message, Throwable cause) {
		super(message, cause);
	}
}
