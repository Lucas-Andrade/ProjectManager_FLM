
package app.repository;

import java.util.Collection;
import java.util.Collections;
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

	private static long nextPIDToBeUsed = 1;

	public boolean addProject(Project project) {
		if (projects.add(project))
		{
			nextPIDToBeUsed++;
			return true;
		}
		return false;
	}

	public boolean removeProject(Project project) {
		return projects.remove(project);
	}
	
	public void removeAll() {
		nextPIDToBeUsed = 1;
		projects.clear();
	}

	public Collection<Project> getProjects() {
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

	@Override
	public DatabaseElement[] getAll() {
		Collection<Project> col = getProjects();
		DatabaseElement[] dataArr = new DatabaseElement[col.size()];
		int index = 0;
		
		for(DatabaseElement elem : col)
			dataArr[index++] = elem;
		
		return dataArr;
	}

	@Override
	public int size() {
		return projects.size();
	}
	
	
}
