package app.domainCommands.exceptions;

@SuppressWarnings("serial")
public class AddedExistingSubproject extends CommandExecutionException{

	public AddedExistingSubproject() {
	}

	public AddedExistingSubproject(String message) {
		super(message);
	}

	public AddedExistingSubproject(String message, Throwable cause) {
		super(message, cause);
	}
}
