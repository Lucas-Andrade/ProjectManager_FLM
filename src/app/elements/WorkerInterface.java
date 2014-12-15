package app.elements;

/**
 * Class that defines the contract for the {@link AWorker}s.
 * Extends {@link DatabaseElement}.
 */
public interface WorkerInterface extends DatabaseElement
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
