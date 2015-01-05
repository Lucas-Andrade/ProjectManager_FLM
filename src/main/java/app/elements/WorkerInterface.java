package app.elements;

/**
 * Class that defines the contract for the {@link AWorker}s.
 * Extends {@link Element}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public interface WorkerInterface extends Element
{

	/**
	 * @return the worker identification number
	 */
	public long getCID();

	/**
	 * @see Object#toString()
	 */
	public String toString();

}
