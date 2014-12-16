package app.repository;

import app.elements.DatabaseElement;

/**
 * Abstract class whose purpose will be to store all {link DatabaseElements} in
 * the memory.
 * 
 * @param <T>
 *            The elements that can be stored in the Repository, extends
 *            {@code DatabaseElements}.
 *            
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public abstract class InMemoryRepo<T extends DatabaseElement> implements
		Repository<DatabaseElement>
{

	// Devia aqui estar um Map e não uma Collection. Todos os outros InMemoryRepos
	// deviam usar correctamente esta classe (neste momento quase nem a usam...).
	//TODO
	//
	// /**
	// * The {@link Collection} that holds the repository elements.
	// */
	// private final Collection<T> database = new ArrayList<>();
	//
	// public Iterable<T> getDatabaseElements(Predicate<T> criteria)
	// {
	// ArrayList<T> results = new ArrayList<>();
	// for (T databaseElement : database)
	// if (criteria.test(databaseElement))
	// results.add(databaseElement);
	//
	// return results;
	// }
	//
	// /**
	// * Adds the given DatabaseElement to the Database
	// *
	// * @param DatabaseElement
	// * The DatabaseElement to add to the catalog
	// * @throws IllegalArgumentException
	// * if the received DatabaseElement is {@code null}
	// */
	// public void insert(T databaseElement)
	// {
	// if (databaseElement == null)
	// throw new IllegalArgumentException();
	//
	// database.add(databaseElement);
	// }

}
