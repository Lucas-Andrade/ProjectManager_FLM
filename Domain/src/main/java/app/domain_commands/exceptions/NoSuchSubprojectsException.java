package app.domain_commands.exceptions;

@SuppressWarnings("serial")
public class NoSuchSubprojectsException extends CommandExecutionException{
	
	public NoSuchSubprojectsException() {
	}

	public NoSuchSubprojectsException(String message) {
		super(message);
	}

	public NoSuchSubprojectsException(String message, Throwable cause) {
		super(message, cause);
	}
}
