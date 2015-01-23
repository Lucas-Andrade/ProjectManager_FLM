package app.domainCommands.exceptions;

/**
 * This exception is thrown if a {@code password} does not correspond to the one belonging
 * to the {@code User} it was tested against. 
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
@SuppressWarnings("serial")
public class IncorrectPasswordException extends CommandExecutionException {

	public IncorrectPasswordException() {
	}

	public IncorrectPasswordException(String message) {
		super(message);
	}

	public IncorrectPasswordException(String message, Throwable cause) {
		super(message, cause);
	}
}
