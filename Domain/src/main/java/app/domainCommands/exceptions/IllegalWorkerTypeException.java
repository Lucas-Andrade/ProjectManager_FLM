package app.domainCommands.exceptions;

@SuppressWarnings("serial")
public class IllegalWorkerTypeException extends Exception {

	public IllegalWorkerTypeException() {
	}

	public IllegalWorkerTypeException(String message) {
		super(message);
	}

	public IllegalWorkerTypeException(String message, Throwable cause) {
		super(message, cause);
	}
}
