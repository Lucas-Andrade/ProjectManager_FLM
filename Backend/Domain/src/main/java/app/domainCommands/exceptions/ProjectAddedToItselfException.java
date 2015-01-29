package app.domainCommands.exceptions;

/**
 * This exception is thrown if a {@code Project} was added to itself.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
@SuppressWarnings("serial")
public class ProjectAddedToItselfException extends CommandExecutionException{

	public ProjectAddedToItselfException() {
	}

	public ProjectAddedToItselfException(String message) {
		super(message);
	}

	public ProjectAddedToItselfException(String message, Throwable cause) {
		super(message, cause);
	}
}
