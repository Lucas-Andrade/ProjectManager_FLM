
package app.repository;

import utils.Project;
import app.elements.DatabaseElements;

public interface ProjectRepository extends Repository<DatabaseElements>{
	
	public Project getProjectById(long projectId);

	public long getNextPID();

	public boolean addProject(Project project);
	
	
}
