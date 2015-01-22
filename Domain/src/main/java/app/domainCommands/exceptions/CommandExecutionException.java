package app.domainCommands.exceptions;

@SuppressWarnings("serial")
public class CommandExecutionException extends Exception{

	public CommandExecutionException() {
	}

	public CommandExecutionException(String message) {
		super(message);
	}

	public CommandExecutionException(String message, Throwable cause) {
		super(message, cause);
	}
}
