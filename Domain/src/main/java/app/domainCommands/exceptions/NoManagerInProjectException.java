package app.domainCommands.exceptions;

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
