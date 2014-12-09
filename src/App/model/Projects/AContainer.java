package App.model.Projects;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;

/**
 * Abstract class {@code AContainer} that will allow homogeneous collections of {@code Project} and
 * {@code AWorker} to be created.
 *
 * Implements the Interface {@link ICost}.
 *
 * @param <elements>
 *            - any instances of a type compatible with {@code Element}.
 */
public abstract class AContainer<elements extends Element> implements ICost {

	
	private final Collection<elements> elementsList;
	
	/**
	 * ACointaner constructor the will instanciate {@code elementsList} as a {@link TreeSet}
	 * collection.
	 * 
	 * This type of collection will allow all its elements to be allways ordered by their natural
	 * order defined by the override of the method {@code compareTo()} from the {@link Comparable}
	 * Interface implemented by {@code Element}.
	 */
	public AContainer() {

		this.elementsList = new TreeSet<elements>();
	}

	/**
	 * Abstract method that will be implemented by the subclasses of {@code ACointaner}.
	 * 
	 * It will allow an {@code Element} belonging {@code elementsList} to be found given its name.
	 * If an {@code Element} with the same name isn't found the method will return false.
	 * 
	 * 
	 * Will thows {@link IllegalArgumentException} if the given parameter is null.
	 * 
	 * @param name
	 *            - The name ot the {@code Element} to be found.
	 * 
	 * @return returns the {@code Element} with correponding name or null if no {@code Element} has
	 *         the given name.
	 */
	public abstract Element getElementByName(String name);

	/**
	 * Method that will allow a given {@code Element} to be added to the the container. If a an
	 * {@code Element} with the same name already exists it will not be added and the method will
	 * return false.
	 * 
	 * If the given parameter is null returns false.
	 * 
	 * @param element
	 *            - {@code Element} to be added to the container.
	 * 
	 * @return true if the {@code Element} is successfully added and false otherwhise.
	 */
	public boolean addElement(elements element) {
		
		for (elements currentElement : elementsList)
			if (currentElement.getName().equals(element.getName()))
				return false;

		return elementsList.add(element);
	}

	/**
	 * Method that will allow a given {@code Element} to be removed from the the container. If the
	 * {@code Element} is not in the container the element wont be removed and the method will
	 * return false.
	 * 
	 * If the given parameter is null returns false.
	 * 
	 * @param element
	 *            - {@code Element} to be added to the container.
	 * 
	 * @return true if the {@code Element} is successfully added and false otherwhise.
	 */
	public boolean remove(Element element) {

		// Não remove subprojectos.
		return this.elementsList.remove(element);
	}

	/**
	 * @return and unmodifiable view of {@code elementsList}.
	 */
	public Collection<elements> getElementsList() {

		return Collections.unmodifiableCollection(elementsList);
	}

	/**
	 * Override of the method {@code getCosts()} from the {@code ICost} Interface.
	 */
	@Override
	public double getCost() {

		double cost = 0;

		for (elements element : elementsList)
			cost += element.getCost();

		return cost;
	}

	/**
	 * Override of the method {@code toString()} from {@code Object}.
	 */
	@Override
	public String toString() {

		String containerElementsString = "";

		for (elements element : elementsList)
			containerElementsString += element.toString() + "\r\n";

		return containerElementsString;
	}
}