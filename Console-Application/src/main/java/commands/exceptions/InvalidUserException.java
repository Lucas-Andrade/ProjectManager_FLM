/**
 * 
 */
package commands.exceptions;

import java.text.MessageFormat;

/**
 * Extends {@link CommandException}. 
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 22/12/2014
 */
@SuppressWarnings("serial")
public class InvalidUserException extends CommandException
{

	public InvalidUserException(String username)
	{
		super(MessageFormat.format(
				"Inserted username {0} and/or password are invalid.", username));
	}

	public InvalidUserException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
