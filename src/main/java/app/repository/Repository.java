package app.repository;

import app.elements.Element;

/**
 * The interface to be implemented by all Repositories.
 * 
 * @param <T>
 *            The elements that can be stored in the Repository, extends
 *            {@code DatabaseElements}.
 *            
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public interface Repository<T extends Element>
{

	/**
	 * Removes all elements from the {@code Repository}.
	 */
	public void removeAll();

	/**
	 * @return An array with all {@link DatabaseElements} that are in the
	 *         Repository.
	 */
	public Element[] getAll();

	/**
	 * @return The size of the {@code Repository}.
	 */
	public int size();

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString();

}
