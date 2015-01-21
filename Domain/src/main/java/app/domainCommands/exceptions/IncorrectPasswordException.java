package app.domainCommands.exceptions;

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
