package app.commands;

import java.util.Map;

/**
 * Contract to be suported by all {@link App.commands.Command} factories
 */
public interface CommandFactory     // Padrão de desenho  AbstractFactory
{
	/**
	 * Generates a new instance of {@code Command}. 
	 * @param parameters
	 * @return
	 */
	public Command newInstance(Map<String,String> parameters);
}
