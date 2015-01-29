package app.domainCommands.exceptions;

/**
 * This exception is thrown if the desired {@code loginName} is already in use.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
@SuppressWarnings("serial")
public class RepeatedUsernameException extends CommandExecutionException {

	public RepeatedUsernameException() {
	}

	public RepeatedUsernameException(String message) {
		super(message);
	}

	public RepeatedUsernameException(String message, Throwable cause) {
		super(message, cause);
	}
}
