package app.domainCommands.exceptions;

/**
 * This exception is thrown if a {@code Project} has no assigned manager, but
 * one was expected
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
@SuppressWarnings("serial")
public class NoManagerInProjectException extends CommandExecutionException {

	public NoManagerInProjectException() {
	}

	public NoManagerInProjectException(String message) {
		super(message);
	}

	public NoManagerInProjectException(String message, Throwable cause) {
		super(message, cause);
	}
}
