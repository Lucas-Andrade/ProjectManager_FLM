package app.commands;

import java.util.Map;

/**
 * Contract to be supported by all {@link Command} Factories. Each
 * {@code Command} Factory should generate instances of the corresponding
 * {@code Command}.
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
