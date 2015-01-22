package app.domain_commands.exceptions;

@SuppressWarnings("serial")
public class IllegalWorkerTypeException extends CommandExecutionException {

	public IllegalWorkerTypeException() {
	}

	public IllegalWorkerTypeException(String message) {
		super(message);
	}

	public IllegalWorkerTypeException(String message, Throwable cause) {
		super(message, cause);
	}
}
