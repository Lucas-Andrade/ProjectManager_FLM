package App.model.Projects;

/**
 * Class that will represent a container of projects. Extends AContainer<Project>.
 */
public class ProjectsContainer extends AContainer<Project> {

	/**
	 * Override of the method {@code getELementByName()} from {@code AContainer}.
	 */
	public Element getElementByName(String name) {

		if (name == null)
			throw new IllegalArgumentException();

		for (Project element : this.getElementsList()) {
			
			Element project = element.getSubProjectByName(name);
			
			if(project == null)
				continue;
				
			return project;
		}

		return null;
	}
}