package parserUtils;
import parser.CommandParser;

/**
 * {@link CommandParser} exceptions.
 */
@SuppressWarnings("serial")
public class CommandParserException extends Exception {

	public CommandParserException() {
	}

	public CommandParserException(String message) {
		super(message);
	}

	public CommandParserException(String message, Throwable cause) {
		super(message, cause);
	}
}
