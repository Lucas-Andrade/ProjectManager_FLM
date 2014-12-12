package app.elements;

import utils.Project;

public interface ProjectInterface extends DatabaseElement {
	
	public Iterable<Project> getContainerProject();
	
	public long getPID();
}
