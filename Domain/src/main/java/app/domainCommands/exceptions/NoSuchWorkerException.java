package app.domainCommands.exceptions;

@SuppressWarnings("serial")
public class NoSuchWorkerException extends Exception{

	public NoSuchWorkerException() {
	}

	public NoSuchWorkerException(String message) {
		super(message);
	}

	public NoSuchWorkerException(String message, Throwable cause) {
		super(message, cause);
	}
}
