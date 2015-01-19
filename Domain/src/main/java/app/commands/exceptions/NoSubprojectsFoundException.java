package app.commands.exceptions;

@SuppressWarnings("serial")
public class NoSubprojectsFoundException extends Exception{
	
	public NoSubprojectsFoundException() {
	}

	public NoSubprojectsFoundException(String message) {
		super(message);
	}

	public NoSubprojectsFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
