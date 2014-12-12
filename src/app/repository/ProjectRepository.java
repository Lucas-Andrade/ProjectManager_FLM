package app.repository;

import utils.Project;
import app.elements.DatabaseElement;

/**
 * The interface to be implemented by all {@link Project}s {@link Repository}.
 */
public interface ProjectRepository extends Repository<DatabaseElement>
{

	/**
	 * Get's the {@code Project} with the supplied PID from the repository.
	 * 
	 * @param projectID
	 *            The PID of the Project to search.
	 * @return The Project with the supplied PID, or null if none.
	 */
	public Project getProjectById(long projectId);

	/**
	 * @return The next available PID ({@link Project} ID, there can't be more
	 *         than one {@code Project} with the same PID).
	 */
	public long getNextPID();

	/**
	 * Adds a {@code Project} to the repository.
	 * 
	 * @param project
	 *            The Project to add.
	 * @return True if successful, False if not.
	 */
	public boolean addProject(Project project);

}