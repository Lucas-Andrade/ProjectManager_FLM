package utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

import org.json.JSONObject;

/**
 * Abstract class {@code AContainer} that will allow homogeneous collections of
 * {@code Project} and {@code AWorker} to be created. All {@code AContainer}
 * instances are thread-safe. Implements the Interface {@link ICost}.
 *
 * @param <Elements>
 *            - any instances of a type compatible with {@code Element}.
 */
public abstract class AContainer<Elements extends UtilsElement> implements
		ICost, Iterable<Elements> {

	/**
	 * A volatile collection.
	 */
	private volatile Collection<Elements> elementsList;

	/**
	 * The lock to be used inside {@code this} object.
	 */
	private Object lockAContainer = new Object();

	/**
	 * ACointaner constructor the will instantiate {@code elementsList} as a
	 * synchronized {@link TreeSet} collection.
	 * 
	 * This type of collection will allow all its elements to be always ordered
	 * by their natural order defined by the override of the method
	 * {@code compareTo()} from the {@link Comparable} Interface implemented by
	 * {@code Element}.
	 */
	public AContainer() {
			this.elementsList = new TreeSet<Elements>();
	}

	/**
	 * @return An {@code Iterator<elements>} that goes over all elements in this
	 *         {@code AContainer}.
	 * 
	 * @see java.util.Iterator
	 */
	public Iterator<Elements> iterator() {
		synchronized (lockAContainer) {
			return elementsList.iterator();
		}
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
	public boolean addElement(Elements element) {
		synchronized (lockAContainer) {
			return elementsList.add(element);
		}
	}

	/**
	 * Method that will remove the {@code Element} equal to element from this
	 * container. If this container doesn't have an {@code Element} equal to
	 * element, then no {@code Element} will be removed and the method will
	 * return false.
	 * 
	 * If the given parameter is null returns false.
	 * 
	 * @param element
	 *            - {@code Element} to be added to the container.
	 * 
	 * @return true if the {@code Element} is successfully added and false
	 *         Otherwise.
	 */
	public boolean remove(UtilsElement element) {
		synchronized (lockAContainer) {
			return this.elementsList.remove(element);
		}
	}

	/**
	 * @return and unmodifiable view of {@code elementsList}.
	 */
	public Collection<Elements> getElementsList() {
		synchronized (lockAContainer) {
			return Collections.unmodifiableCollection(elementsList);
		}
	}

	/**
	 * Override of the method {@code getCosts()} from the {@code ICost}
	 * Interface.
	 */
	@Override
	public double getCost() {
		synchronized (lockAContainer) {
			double cost = 0;

			for (Elements element : elementsList) {
				cost += element.getCost();
			}
			return cost;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		synchronized (lockAContainer) {
			return toString(0);
		}
	}

	public String toString(int i) {
		synchronized (lockAContainer) {
			String spaces = "";
			for (int n = 0; n < i; n++) {
				spaces += " ";
			}
			StringBuilder builder = new StringBuilder();

			for (Elements element : elementsList) {
				builder.append(spaces).append(element.toString()).append("\n");
			}

			return builder.toString();
		}
	}

	public JSONObject[] getJson() {
		synchronized (lockAContainer) {
			Collection<Elements> elementsCol = getElementsList();
			JSONObject[] jsonArray = new JSONObject[elementsCol.size()];
			int index = 0;

			for (Elements element : elementsCol) {
				jsonArray[index++] = element.getJson();
			}
			return jsonArray;
		}
	}

	/**
	 * @return the number of elements the container contains
	 */
	public int size() {
		synchronized (lockAContainer) {
			return elementsList.size();
		}
	}

	public void removeAll() {
		synchronized (lockAContainer) {
			elementsList.clear();
		}
	}

	@Override
	public int hashCode() {
		synchronized (lockAContainer) {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((elementsList == null) ? 0 : elementsList.hashCode());
			return result;
		}
	}

	/**
	 * Override of the method {@code equals()} from {@code Object} to be
	 * consistent with the {@code compareTo()} method.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object container) {
		synchronized (lockAContainer) {
			if (this == container) {
				return true;
			}
			if (container == null) {
				return false;
			}
			if (getClass() != container.getClass()) {
				return false;
			}
			return hasSameElements((AContainer<UtilsElement>) container);
		}
	}

	/**
	 * Verifies if the {@code AContainer} passed as parameter has the same
	 * elements as {@code this}.
	 * 
	 * @param worker
	 * @return true if the {@code AContainer} passed as parameter has the same
	 *         elements as {@code this}
	 * @return false if the {@code AContainer} passed as parameter has not the
	 *         same elements as {@code this}
	 */
	public boolean hasSameElements(AContainer<UtilsElement> container) {
		synchronized (lockAContainer) {
			if (size() != container.size()) {
				return false;
			}

			Iterator<UtilsElement> iterator = container.getElementsList()
					.iterator();
			for (UtilsElement element : elementsList) {
				if (!element.equals(iterator.next())) { // TreeSet is ordered,
														// so we
														// can compare elements
														// in
														// pairs
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * @return true if the {@code AContainer} is empty
	 */
	public boolean isEmpty() {
		synchronized (lockAContainer) {
			return elementsList.isEmpty();
		}
	}

}
