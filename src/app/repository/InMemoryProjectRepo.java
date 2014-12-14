
package app.repository;

import java.util.Collection;
import java.util.TreeSet;

import app.elements.DatabaseElement;
import app.elements.ProjectComparator;
import utils.Project;

/**
 * Abstract class whose purpose will be to store all projects in the memory and control
 * the projects IDs (all PIDs have to be different).
 */
public class InMemoryProjectRepo extends InMemoryRepo<Project> implements ProjectRepository{
	
	private static final Collection<Project> projects = new TreeSet<>(new ProjectComparator());

	private static long NEXT_PID_TO_BE_USED = 1;

	public boolean addProject(Project project) {
		if (projects.add(project))
		{
			NEXT_PID_TO_BE_USED++;
			return true;
		}
		return false;
	}

	public boolean removeProject(Project project) {
		return projects.remove(project);
	}
	
	public void removeAll() {
		NEXT_PID_TO_BE_USED = 1;
		projects.clear();
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
		return NEXT_PID_TO_BE_USED;
	}

	@Override
	public DatabaseElement[] getAll() {
		return (DatabaseElement[]) projects.toArray();
	}

	@Override
	public int size() {
		return projects.size();
	}
	
	
}
