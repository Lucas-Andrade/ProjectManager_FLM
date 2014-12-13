package app.elements;

import utils.AWorker;
import utils.Leader;
import utils.Project;

public interface ProjectInterface extends DatabaseElement {
	
	public Iterable<Project> getContainerProject();
	
	public long getPID();
	
	public void setManager(Leader manager);
	
	public boolean addProject(Project project);
	
	public boolean addWorker(AWorker worker);
	
	
}
