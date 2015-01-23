package app.domainCommands.exceptions;

/**
 * This exception is thrown if there is no {@code Project} in the repository
 * with the expected {@code PID}. 
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
@SuppressWarnings("serial")
public class NoSuchProjectException extends CommandExecutionException{
	
	public NoSuchProjectException() {
	}

	public NoSuchProjectException(String message) {
		super(message);
	}

	public NoSuchProjectException(String message, Throwable cause) {
		super(message, cause);
	}

}
