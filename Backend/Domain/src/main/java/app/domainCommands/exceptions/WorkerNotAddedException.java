package app.domainCommands.exceptions;

/**
 * This exception is thrown if a {@code AWorker} could not be added to the {@code Team}
 * of a {@code Project}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
@SuppressWarnings("serial")
public class WorkerNotAddedException extends CommandExecutionException {

	public WorkerNotAddedException() {
	}

	public WorkerNotAddedException(String message) {
		super(message);
	}

	public WorkerNotAddedException(String message, Throwable cause) {
		super(message, cause);
	}
}
