package app.commands.exceptions;

@SuppressWarnings("serial")
public class NoSuchManagerException extends Exception{

	public NoSuchManagerException() {
	}

	public NoSuchManagerException(String message) {
		super(message);
	}

	public NoSuchManagerException(String message, Throwable cause) {
		super(message, cause);
	}
}