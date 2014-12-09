package App.commands;

import java.util.Map;

/**
 * Contract to be suported by all {@link App.commands.Command} factories
 */
public interface CommandFactory     // Padrão de desenho  AbstractFactory
{
	public Command newInstance(Map<String,String> parameters);
}
