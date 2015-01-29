package app.domainCommands.exceptions;

/**
 * This exception is thrown if a {@code password} has less than the minimum
 * number of characters.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
@SuppressWarnings("serial")
public class PasswordLengthOutOfBoundsException extends CommandExecutionException{

	public PasswordLengthOutOfBoundsException() {
	}

	public PasswordLengthOutOfBoundsException(String message) {
		super(message);
	}

	public PasswordLengthOutOfBoundsException(String message, Throwable cause) {
		super(message, cause);
	}
}
