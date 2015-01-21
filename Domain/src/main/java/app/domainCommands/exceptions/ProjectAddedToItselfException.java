package app.domainCommands.exceptions;

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
