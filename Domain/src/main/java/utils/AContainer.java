package utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

import org.json.JSONObject;

/**
 * Abstract class {@code AContainer} that will allow homogeneous collections of
 * {@code Project} and {@code AWorker} to be created.
 *
 * Implements the Interface {@link ICost}.
 *
 * @param <elements>
 *            - any instances of a type compatible with {@code Element}.
 */
public abstract class AContainer<elements extends UtilsElement> implements ICost, Iterable<elements>
{

	private final Collection<elements> elementsList;

	/**
	 * ACointaner constructor the will instantiate {@code elementsList} as a
	 * {@link TreeSet} collection.
	 * 
	 * This type of collection will allow all its elements to be always ordered
	 * by their natural order defined by the override of the method
	 * {@code compareTo()} from the {@link Comparable} Interface implemented by
	 * {@code Element}.
	 */
	public AContainer()
	{
		this.elementsList = new TreeSet<elements>();
	}

	/**
	 * @return An {@code Iterator<elements>} that goes over all elements in 
	 * this {@code AContainer}.
	 * 
	 * @see java.util.Iterator
	 */
	public Iterator<elements> iterator() {
		return elementsList.iterator();
	}
	
	/**
	 * Abstract method that will be implemented by the subclasses of
	 * {@code ACointaner}.
	 * 
	 * It will allow an {@code Element} belonging {@code elementsList} to be
	 * found given its name. If an {@code Element} with the same name isn't
	 * found the method will return false.
	 * 
	 * 
	 * Will throws {@link IllegalArgumentException} if the given parameter is
	 * null.
	 * 
	 * @param name
	 *            - The name of the {@code Element} to be found.
	 * 
	 * @return returns the {@code Element} with corresponding name or null if no
	 *         {@code Element} has the given name.
	 */
	public abstract UtilsElement getElementByName(String name);

	/**
	 * Method that will allow a given {@code Element} to be added to the the
	 * container. If a an {@code Element} with the same name already exists it
	 * will not be added and the method will return false.
	 * 
	 * If the given parameter is null returns false.
	 * 
	 * @param element
	 *            - {@code Element} to be added to the container.
	 * 
	 * @return true if the {@code Element} is successfully added and false
	 *         Otherwise.
	 */
	public boolean addElement(elements element)
	{
		for (elements currentElement : elementsList)
			if (currentElement.getName().equals(element.getName()))
				return false;

		return elementsList.add(element);
	}

	/**
	 * Method that will allow a given {@code Element} to be removed from the the
	 * container. If the {@code Element} is not in the container the element
	 * wont be removed and the method will return false.
	 * 
	 * If the given parameter is null returns false.
	 * 
	 * @param element
	 *            - {@code Element} to be added to the container.
	 * 
	 * @return true if the {@code Element} is successfully added and false
	 *         Otherwise.
	 */
	public boolean remove(UtilsElement element)
	{
		// Não remove subprojectos dos subprojectos dum projecto.
		// No caso das equipas não há stresses.
		// @see Project#removeProject(String)
		return this.elementsList.remove(element);
	}

	/**
	 * @return and unmodifiable view of {@code elementsList}.
	 */
	public Collection<elements> getElementsList()
	{
		return Collections.unmodifiableCollection(elementsList);
	}

	/**
	 * Override of the method {@code getCosts()} from the {@code ICost}
	 * Interface.
	 */
	@Override
	public double getCost()
	{

		double cost = 0;

		for (elements element : elementsList)
			cost += element.getCost();

		return cost;
	}

	/**
	 * Override of the method {@code toString()} from {@code Object}.
	 */
	@Override
	public String toString()
	{
		return toString(0);	
	}
	
	public String toString(int i) 
	{
		String spaces = "";
		for(int n = 0; n < i; n++)
			spaces += " ";
		
		StringBuilder builder = new StringBuilder();

		for (elements element : elementsList)
			builder.append(spaces).append(element.toString()).append("\n");

		return builder.toString();
	}
	
	public JSONObject[] getJson() 
	{
		Collection<elements> elementsList = getElementsList();
		JSONObject[] jsonArray = new JSONObject[elementsList.size()];
		int index = 0;
		
		for (elements element : elementsList)
			jsonArray[index++] = element.getJson();
		return jsonArray;
	}
	
	/**
	 * @return the number of elements the container contains
	 */
	public int size()
	{
		return elementsList.size();
	}
	
	public void removeAll()
	{
		elementsList.clear();
	}
	
	/**
	 * Override of the method {@code equals()} from {@code Object} to be
	 * consistent with the {@code compareTo()} method.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object container)
	{
		if (this == container)
			return true;

		if (container == null)
			return false;

		if (getClass() != container.getClass())
			return false;
		
		if(size() != ((AContainer<UtilsElement>)container).size())
			return false;
		
		Iterator<UtilsElement> iterator = ((AContainer<UtilsElement>)container).getElementsList().iterator();
		for (UtilsElement element : elementsList)
			if (! element.equals(iterator.next())) //TreeSet is ordered, so we can compare elements in pairs
				return false;
		
		return true;
	}
}