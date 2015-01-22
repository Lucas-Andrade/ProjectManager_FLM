package app.domain_commands.exceptions;

@SuppressWarnings("serial")
public class PasswordLengthOutOfBoundsException extends CommandExecutionException{

	public PasswordLengthOutOfBoundsException() {
	}

	public PasswordLengthOutOfBoundsException(String message) {
		super(message);
	}

	public PasswordLengthOutOfBoundsException(String message, Throwable cause) {
		super(message, cause);
	}
}
