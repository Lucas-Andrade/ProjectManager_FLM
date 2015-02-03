package app.repository;

import utils.Project;
import app.AppElement;
import app.elements.mutable.ProjectCreationDescriptor;

/**
 * The interface to be implemented by all {@link Project}s {@link Repository}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public interface ProjectsRepository extends Repository<AppElement> {

	/**
	 * Get's the {@code Project} with the supplied PID from the repository.
	 * 
	 * @param projectID
	 *            The PID of the Project to search.
	 * @return The Project with the supplied PID, or null if none.
	 */
	public Project getProjectById(long projectId);

	/**
	 * Creates and adds a {@code Project} to the repository.
	 * 
	 * @param project
	 *            The {@code ProjectCreationDescriptor} with the information
	 *            necessary to create the {@code Project} to add.
	 * @return The CID if successful, null if not.
	 */
	public Long addProject(ProjectCreationDescriptor creationDescriptor);

	/**
	 * Removes a project from the repository.
	 * 
	 * @param project
	 *            The project to be removed.
	 * @return True if successful, False if not.
	 */
	boolean removeProject(Project project);

}