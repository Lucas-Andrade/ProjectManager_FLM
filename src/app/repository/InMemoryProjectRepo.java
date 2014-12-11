
package app.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import utils.Project;

/**
 * Abstract class whose purpose will be to store all projects in existence and control
 * the projects IDs (all PIDs have to be different).
 */
public class InMemoryProjectRepo extends InMemoryRepo<Project> implements ProjectRepository{
	
	private static final Collection<Project> projects = new HashSet<>();

	private static long nextPIDToBeUsed = 1;

	public boolean addProject(Project project) {
		if (projects.add(project))
		{
			nextPIDToBeUsed++;
			return true;
		}
		return false;
	}

	public static boolean removeName(Project project) {
		return projects.remove(project);
	}
	
	public static void removeAll() {
		projects.clear();
	}

	public static Collection<Project> getProjects() {
		return Collections.unmodifiableCollection(projects);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Project project : projects)
			builder.append(project.toString()).append("\n");
		return builder.toString();
	}

	@Override
	public Project getProjectById(long projectId) {
		for (Project project : projects)
			if (project.getPID() == projectId)
				return project;
		return null;
	}

	@Override
	public long getNextPID() {
		return nextPIDToBeUsed;
	}
	
	
}
