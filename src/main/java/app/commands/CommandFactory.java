package main.java.app.commands;

import java.util.Map;

/**
 * Contract to be supported by all {@link Command} Factories. Each
 * {@code Command} Factory should generate instances of the corresponding
 * {@code Command}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public interface CommandFactory
{
	/**
	 * Generates a new instance of {@code Command}.
	 * 
	 * @param parameters
	 *            The {@code Command} arguments.
	 * @return
	 */
	public Command newInstance(Map<String, String> parameters);
}
