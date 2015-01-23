package app.domainCommands.exceptions;

/**
 * This exception is thrown if a {@code Project} was added to an other, but happens
 * to already be a subproject of it.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
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
