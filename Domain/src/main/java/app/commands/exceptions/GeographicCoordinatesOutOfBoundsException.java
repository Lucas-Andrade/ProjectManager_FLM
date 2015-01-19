package app.commands.exceptions;

@SuppressWarnings("serial")
public class GeographicCoordinatesOutOfBoundsException extends Exception{

	public GeographicCoordinatesOutOfBoundsException() {
	}

	public GeographicCoordinatesOutOfBoundsException(String message) {
		super(message);
	}

	public GeographicCoordinatesOutOfBoundsException(String message, Throwable cause) {
		super(message, cause);
	}
}
