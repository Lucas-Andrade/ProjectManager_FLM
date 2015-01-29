package app.domainCommands.exceptions;

/**
 * This exception is thrown if a {@code Project} does not have any subprojects,
 * but was expected to.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
@SuppressWarnings("serial")
public class NoSuchSubprojectsException extends CommandExecutionException{
	
	public NoSuchSubprojectsException() {
	}

	public NoSuchSubprojectsException(String message) {
		super(message);
	}

	public NoSuchSubprojectsException(String message, Throwable cause) {
		super(message, cause);
	}
}
