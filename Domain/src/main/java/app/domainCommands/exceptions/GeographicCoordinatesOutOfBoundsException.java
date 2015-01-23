package app.domainCommands.exceptions;

/**
 * This exception is thrown if the {@code latitude} or {@code longitude} are outside of their
 * correct boundaries.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
@SuppressWarnings("serial")
public class GeographicCoordinatesOutOfBoundsException extends CommandExecutionException{

	public GeographicCoordinatesOutOfBoundsException() {
	}

	public GeographicCoordinatesOutOfBoundsException(String message) {
		super(message);
	}

	public GeographicCoordinatesOutOfBoundsException(String message, Throwable cause) {
		super(message, cause);
	}
}
