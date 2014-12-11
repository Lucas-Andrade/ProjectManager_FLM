package App.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;

import App.elements.DatabaseElements;

public abstract class InMemoryRepo<T extends DatabaseElements> implements Repository<DatabaseElements>{

	/**
	 * Holds the database.
	 */
	private final Collection<T> database = new ArrayList<>();

	/**
	 * {@see ProductRepository#getProducts()}
	 */

	public Iterable<DatabaseElements> getDatabaseElements()
	{
		return Collections.unmodifiableCollection(database);
	}

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public Iterable<T> getDatabaseElements(Predicate<T> criteria)
	{
		ArrayList<T> results = new ArrayList<>();
		for (T databaseElement : database)
			if (criteria.test(databaseElement))
				results.add(databaseElement);

		return results;
	}

	/**
	 * Adds the given DatabaseElement to the Database
	 * 
	 * @param DatabaseElement
	 *            The DatabaseElement to add to the catalog
	 * @throws IllegalArgumentException
	 *             if the received DatabaseElement is {@code null}
	 */

	public void insert(T databaseElement)
	{
		if (databaseElement == null)
			throw new IllegalArgumentException();

		database.add(databaseElement);
	}

}
