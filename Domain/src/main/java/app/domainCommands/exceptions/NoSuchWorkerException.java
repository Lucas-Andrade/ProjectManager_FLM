package app.domainCommands.exceptions;

/**
 * This exception is thrown if a repository does not contain any {@code AWorker} with
 * the expected {@code CID}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
@SuppressWarnings("serial")
public class NoSuchWorkerException extends CommandExecutionException{

	public NoSuchWorkerException() {
	}

	public NoSuchWorkerException(String message) {
		super(message);
	}

	public NoSuchWorkerException(String message, Throwable cause) {
		super(message, cause);
	}
}
