package utils;

import app.AppElement;

/**
 * Class that defines the contract for the {@link AWorker}s.
 * Extends {@link AppElement}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public interface IWorker extends UtilsElement{
	/**
	 * @return the worker identification number
	 */
	public long getCID();

	/**
	 * @see Object#toString()
	 */
	public String toString();
}
