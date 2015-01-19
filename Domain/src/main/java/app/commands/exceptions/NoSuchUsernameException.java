package app.commands.exceptions;

@SuppressWarnings("serial")
public class NoSuchUsernameException extends Exception{
	
	public NoSuchUsernameException() {
	}

	public NoSuchUsernameException(String message) {
		super(message);
	}

	public NoSuchUsernameException(String message, Throwable cause) {
		super(message, cause);
	}

}
