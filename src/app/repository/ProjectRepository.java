
package app.repository;

import utils.Project;
import app.elements.DatabaseElement;

public interface ProjectRepository extends Repository<DatabaseElement>{
	
	public Project getProjectById(long projectId);

	public long getNextPID();

	public boolean addProject(Project project);
	
	
}
