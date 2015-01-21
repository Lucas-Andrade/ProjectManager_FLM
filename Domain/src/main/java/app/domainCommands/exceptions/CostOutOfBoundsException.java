package app.domainCommands.exceptions;

@SuppressWarnings("serial")
public class CostOutOfBoundsException extends Exception{

	public CostOutOfBoundsException() {
	}

	public CostOutOfBoundsException(String message) {
		super(message);
	}

	public CostOutOfBoundsException(String message, Throwable cause) {
		super(message, cause);
	}
}
