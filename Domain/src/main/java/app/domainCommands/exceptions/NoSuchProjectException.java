package app.domainCommands.exceptions;

@SuppressWarnings("serial")
public class NoSuchProjectException extends Exception{
	
	public NoSuchProjectException() {
	}

	public NoSuchProjectException(String message) {
		super(message);
	}

	public NoSuchProjectException(String message, Throwable cause) {
		super(message, cause);
	}

}
