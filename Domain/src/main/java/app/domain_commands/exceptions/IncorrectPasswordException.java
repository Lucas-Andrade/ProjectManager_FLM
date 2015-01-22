package app.domain_commands.exceptions;

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
