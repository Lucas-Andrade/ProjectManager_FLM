package app.domainCommands;

import java.util.concurrent.Callable;

import app.AppElement;
import app.domainCommands.exceptions.CommandExecutionException;

/**
 * {@code interface} of all the {@code Command}s that allow to easily make
 * alterations to the repositories, and to the objects stored in them. 
 * 
 * This {@code interface} implements the {@code Callable} interface, and the 
 * {@code call} method of all it's instances will return an array of
 * {@code AppElement}s
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public interface Command extends Callable<AppElement[]> {

	/**
	 * Calls the {@code Command}. This makes the {@code Command} perform the
	 * alterations in the repositories, and returns an array of {@code AppElement}s.
	 */
	@Override
	public AppElement[] call() throws CommandExecutionException;
}
