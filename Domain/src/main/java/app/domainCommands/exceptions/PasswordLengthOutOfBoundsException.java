package app.domainCommands.exceptions;

@SuppressWarnings("serial")
public class PasswordLengthOutOfBoundsException extends Exception{

	public PasswordLengthOutOfBoundsException() {
	}

	public PasswordLengthOutOfBoundsException(String message) {
		super(message);
	}

	public PasswordLengthOutOfBoundsException(String message, Throwable cause) {
		super(message, cause);
	}
}
