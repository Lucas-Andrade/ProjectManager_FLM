package app.domain_commands.exceptions;

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
