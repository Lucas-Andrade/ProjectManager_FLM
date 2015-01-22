package command_parser;

/**
 * Class whose instances represent {@code CommandParser} errors that result from
 * finding argument repetitions in a command.
 */
@SuppressWarnings("serial")
public class DuplicateArgumentsException extends CommandParserException {
	public DuplicateArgumentsException() {
	}

	public DuplicateArgumentsException(String message) {
		super(message);
	}

	public DuplicateArgumentsException(String message, Throwable cause) {
		super(message, cause);
	}

}
