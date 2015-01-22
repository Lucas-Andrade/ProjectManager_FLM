package app.domain_commands.exceptions;

@SuppressWarnings("serial")
public class NoSuchWorkerException extends CommandExecutionException{

	public NoSuchWorkerException() {
	}

	public NoSuchWorkerException(String message) {
		super(message);
	}

	public NoSuchWorkerException(String message, Throwable cause) {
		super(message, cause);
	}
}
