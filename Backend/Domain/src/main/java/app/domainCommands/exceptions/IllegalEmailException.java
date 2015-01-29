package app.domainCommands.exceptions;

/**
 * This exception is thrown if an {@code email} was deemed to be invalid.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
@SuppressWarnings("serial")
public class IllegalEmailException extends CommandExecutionException{
	
	public IllegalEmailException() {
	}

	public IllegalEmailException(String message) {
		super(message);
	}

	public IllegalEmailException(String message, Throwable cause) {
		super(message, cause);
	}

}
