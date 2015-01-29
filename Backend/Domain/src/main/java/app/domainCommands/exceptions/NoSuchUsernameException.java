package app.domainCommands.exceptions;

/**
 * This exception is thrown if a repository does not contain any {@code User} with
 * the expected {@code loginName}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
@SuppressWarnings("serial")
public class NoSuchUsernameException extends CommandExecutionException{
	
	public NoSuchUsernameException() {
	}

	public NoSuchUsernameException(String message) {
		super(message);
	}

	public NoSuchUsernameException(String message, Throwable cause) {
		super(message, cause);
	}

}
