package app.domain_commands.exceptions;

@SuppressWarnings("serial")
public class CostOutOfBoundsException extends CommandExecutionException{

	public CostOutOfBoundsException() {
	}

	public CostOutOfBoundsException(String message) {
		super(message);
	}

	public CostOutOfBoundsException(String message, Throwable cause) {
		super(message, cause);
	}
}
