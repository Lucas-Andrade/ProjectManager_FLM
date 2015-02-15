package publishers;

/**
 * Expression thrown if a {@code String} in JSON format could not be parsed
 * into a {@code JTable}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 13/02/2015
 *
 */
@SuppressWarnings("serial")
public class TableBuilderException extends Exception{
	public TableBuilderException() {
	}

	public TableBuilderException(String message) {
		super(message);
	}

	public TableBuilderException(String message, Throwable cause) {
		super(message, cause);
	}
}
