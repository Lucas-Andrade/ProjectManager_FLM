package app.commands.exceptions;

@SuppressWarnings("serial")
public class UnrecognisedWorkerTypeException extends Exception {

	public UnrecognisedWorkerTypeException() {
	}

	public UnrecognisedWorkerTypeException(String message) {
		super(message);
	}

	public UnrecognisedWorkerTypeException(String message, Throwable cause) {
		super(message, cause);
	}
}
