package app.commands.exceptions;

import java.text.MessageFormat;


public class MandatoryParameterNotPresentException extends CommandException {

	public MandatoryParameterNotPresentException(String parameterName) {
		super(MessageFormat.format("Demanding parameter with name {0} not present.", parameterName));
	}



	public MandatoryParameterNotPresentException(String message, Throwable cause) {
		super(message, cause);
	}

}
