package app.domainCommands.exceptions;

@SuppressWarnings("serial")
public class IllegalEmailException extends CommandExecutionException{
	
	public IllegalEmailException() {
	}

	public IllegalEmailException(String message) {
		super(message);
	}

	public IllegalEmailException(String message, Throwable cause) {
		super(message, cause);
	}

}
