package app.commands.exceptions;

@SuppressWarnings("serial")
public class NoSuchSubprojectsException extends Exception{
	
	public NoSuchSubprojectsException() {
	}

	public NoSuchSubprojectsException(String message) {
		super(message);
	}

	public NoSuchSubprojectsException(String message, Throwable cause) {
		super(message, cause);
	}
}
