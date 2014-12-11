package app.elements;

import utils.Project;

public interface ProjectInterface extends DatabaseElements {
	
	public Iterable<Project> getContainerProject();
}
