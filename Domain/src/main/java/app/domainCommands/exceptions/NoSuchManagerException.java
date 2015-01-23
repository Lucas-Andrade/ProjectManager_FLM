package app.domainCommands.exceptions;

/**
 * This exception is thrown if there is no {@code Leader} in the repository
 * with the expected {@code CID}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
@SuppressWarnings("serial")
public class NoSuchManagerException extends CommandExecutionException{

	public NoSuchManagerException() {
	}

	public NoSuchManagerException(String message) {
		super(message);
	}

	public NoSuchManagerException(String message, Throwable cause) {
		super(message, cause);
	}
}
