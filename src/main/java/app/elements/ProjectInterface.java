package app.elements;

import utils.AWorker;
import utils.Leader;
import utils.Project;

/**
 * Class that defines the contract for the {@link Project}s. Extends
 * {@link DatabaseElement}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public interface ProjectInterface extends DatabaseElement
{

	/**
	 * Gives the Container with all subprojects of the project.
	 */
	public Iterable<Project> getContainerProject();

	/**
	 * Returns the identification PID of the project.
	 */
	public long getPID();

	public void setManager(Leader manager);

	/**
	 * Method that will allow a subproject to be added to the {@code Project}.
	 * 
	 * @param project
	 *            The subproject to the added to the {@code Project}.
	 * 
	 * @return true if the subproject is successfully added and false otherwise.
	 */
	public boolean addProject(Project project);

	/**
	 * Method that will allow a worker to be added to the {@code Project}.
	 * 
	 * @param worker
	 *            - The {@code AWorker} to be add to the {@code team}.
	 * 
	 * @return true if the worker is successfully added and false otherwise.
	 */
	public boolean addWorker(AWorker worker);

	/**
	 * @see Object#toString()
	 */
	public String toString();

}
