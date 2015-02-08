package parserUtils;
import java.util.Map;
import java.util.concurrent.Callable;

import outputMethods.Result;

/**
 * Contract to be supported by all {@link Command} Factories. Each
 * {@code Command} Factory should generate instances of the corresponding
 * {@code Command}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public interface CommandFactory{
	
	/**
	 * Generates a new instance of {@code Command}.
	 * 
	 * @param parameters
	 *            The {@code Command} arguments.
	 * @return
	 */
	public Callable<Result> newInstance(Map<String, String> parameters);
}
