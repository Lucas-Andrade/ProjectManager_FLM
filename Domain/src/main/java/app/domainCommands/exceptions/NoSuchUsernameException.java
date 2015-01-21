package app.domainCommands.exceptions;

@SuppressWarnings("serial")
public class NoSuchUsernameException extends CommandExecutionException{
	
	public NoSuchUsernameException() {
	}

	public NoSuchUsernameException(String message) {
		super(message);
	}

	public NoSuchUsernameException(String message, Throwable cause) {
		super(message, cause);
	}

}
