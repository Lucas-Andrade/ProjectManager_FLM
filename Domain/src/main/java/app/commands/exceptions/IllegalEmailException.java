package app.commands.exceptions;

@SuppressWarnings("serial")
public class IllegalEmailException extends Exception{
	
	public IllegalEmailException() {
	}

	public IllegalEmailException(String message) {
		super(message);
	}

	public IllegalEmailException(String message, Throwable cause) {
		super(message, cause);
	}

}