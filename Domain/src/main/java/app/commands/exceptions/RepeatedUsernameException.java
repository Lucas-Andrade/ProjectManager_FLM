package app.commands.exceptions;

@SuppressWarnings("serial")
public class RepeatedUsernameException extends Exception {

	public RepeatedUsernameException() {
	}

	public RepeatedUsernameException(String message) {
		super(message);
	}

	public RepeatedUsernameException(String message, Throwable cause) {
		super(message, cause);
	}
}
