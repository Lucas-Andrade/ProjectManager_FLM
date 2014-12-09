package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;

import App.repository.UsersRepository;

/**
 * Class that implements an in-memory product repository (i.e. an ephemeral TravelAgency 
 * catalog implementation) 
 */
public class InMemoryProjectsRepository implements UsersRepository {

	/**
	 * Holds the product catalog.
	 */
	private final Collection<Project> catalog = new ArrayList<>(); 
	
	/**
	 * {@see ProductRepository#getProducts()}
	 */
	@Override
	public Iterable<Project> getProducts() 
	{
		return Collections.unmodifiableCollection(catalog);
	}
	//Para evitar que uma coleção, após ser iterada possa ser modificada, podemos fornecer só uma vista 
		//não modificável da coleção - Collections.unmodifiableCollection(products)
		//Assim já não violamos o encapsulamento
		
	
	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public Iterable<Project> getProjects(Predicate<Project> criteria)
	{
		//Interface Predicate<T> - Represents a predicate (boolean-valued function) of one argument.
		ArrayList<Project> results = new ArrayList<>();
		for(Project project : catalog)
			if(criteria.test(project))
				results.add(project);
		
		return results;
	}
	
	/**
	 * 
	 */
	@Override
	public Project getProjectById(long id) 
	{
		
		return null;
	}
	
	/**
	 * Adds the given product to the catalog
	 * 
	 * @param project The product to add to the catalog
	 * @throws IllegalArgumentException if the received product is {@code null} 
	 */
	@Override
	public void insert(Project project)
	{
		if(project == null)
			throw new IllegalArgumentException();
		
		catalog.add(project);
	}
	
	
}