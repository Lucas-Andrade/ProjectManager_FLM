package App.model.Projects;
import java.util.function.Predicate;

/**
 * Contract to be supported by product repositories (i.e. TravelAgency catalog
 * implementations) 
 */
public interface UsersRepository {

	/**
	 * Gets all the products in the repository
	 * 
	 * @return The repository products
	 */
	public Iterable<Project> getProducts();

	/**
	 * Gets all the products that pass to the given filtering criteria
	 * 
	 * @param criteria The filtering criteria to be used
	 * @return The repository products that are within the specified filtering
	 * criteria
	 */
	public Iterable<Project> getProjects(Predicate<Project> criteria);
	
	/**
	 * Adds the given product to the catalog
	 * 
	 * @param product The product to add to the catalog
	 * @throws IllegalArgumentException if the received product is {@code null} 
	 */
	public void insert(Project product);
	
	/**
	 * Gets the product with the given id, or {@code null} if none exists
	 *  
	 * @param id the product identifier
	 * @return the instance with the given identifier
	 */
	public Project getProjectById(long id);
}
