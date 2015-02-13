package utils;

import java.util.Collection;

/**
 * Class that will represent a container of projects. Extends
 * AContainer<Project>. All {@code ProjectsContainer} instances are thread-safe.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class ProjectsContainer extends AContainer<Project> {

	/**
	 * The lock to be used inside {@code this} object.
	 */
	private Object lockProjectsContainer = new Object();

	/**
	 * Override of the method {@code getELementByName()} from {@code AContainer}
	 * .
	 */
	public UtilsElement getElementByName(String name) {
		synchronized (lockProjectsContainer) {
			if (name == null) {
				throw new IllegalArgumentException();
			}
			for (Project element : this.getElementsList()) {

				UtilsElement project = element.getSubProjectByName(name);

				if (project == null) {
					continue;
				}
				return project;
			}

			return null;
		}
	}

	/**
	 * @return a string with information about all the projects in the container
	 */
	public String toString() {
		synchronized (lockProjectsContainer) {
			return toString(0);
		}
	}

	/**
	 * returns a string with details about all the projects in the container the
	 * parameter allow to set the number of spaces that will appear in front of
	 * every line of the information. this allows for indentation of the
	 * subprojects of a {@code Project}
	 * 
	 * @param nr
	 * @return information about all the projects in the container
	 */
	public String toString(int nr) {
		synchronized (lockProjectsContainer) {
			Collection<Project> elementsList = getElementsList();

			StringBuilder builder = new StringBuilder();

			for (Project element : elementsList) {
				builder.append(element.toString(nr));
			}
			return builder.toString();
		}
	}

}