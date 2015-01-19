package app.commands.exceptions;

@SuppressWarnings("serial")
public class NoWorkersFoundException extends Exception{

	public NoWorkersFoundException() {
	}

	public NoWorkersFoundException(String message) {
		super(message);
	}

	public NoWorkersFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
