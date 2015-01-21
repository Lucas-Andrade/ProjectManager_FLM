package app.domainCommands.exceptions;

@SuppressWarnings("serial")
public class NoSuchProjectException extends CommandExecutionException{
	
	public NoSuchProjectException() {
	}

	public NoSuchProjectException(String message) {
		super(message);
	}

	public NoSuchProjectException(String message, Throwable cause) {
		super(message, cause);
	}

}
