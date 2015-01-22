package app.domain_commands.exceptions;

@SuppressWarnings("serial")
public class NoWorkersFoundException extends CommandExecutionException{

	public NoWorkersFoundException() {
	}

	public NoWorkersFoundException(String message) {
		super(message);
	}

	public NoWorkersFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
