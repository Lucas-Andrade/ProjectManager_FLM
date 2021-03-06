package parserUtils;
/**
 * Class whose instances represent {@code CommandParser} errors that result from
 * trying to obtain an unknown command.
 */
@SuppressWarnings("serial")
public class InvalidRegisterException extends CommandParserException{
	public InvalidRegisterException(){
	}

	public InvalidRegisterException(String message){
		super(message);
	}

	public InvalidRegisterException(String message, Throwable cause){
		super(message, cause);
	}

}
