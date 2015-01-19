package app.commands.exceptions;

@SuppressWarnings("serial")
public class NoManagerFoundException extends Exception {

	public NoManagerFoundException() {
	}

	public NoManagerFoundException(String message) {
		super(message);
	}

	public NoManagerFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
