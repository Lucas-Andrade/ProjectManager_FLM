package app.domainCommands.exceptions;

@SuppressWarnings("serial")
public class NoSuchManagerException extends CommandExecutionException{

	public NoSuchManagerException() {
	}

	public NoSuchManagerException(String message) {
		super(message);
	}

	public NoSuchManagerException(String message, Throwable cause) {
		super(message, cause);
	}
}
