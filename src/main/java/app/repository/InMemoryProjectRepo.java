package app.repository;

import java.util.Collection;
import java.util.TreeSet;

import app.elements.DatabaseElement;
import app.elements.ProjectComparator;
import utils.Project;

/**
 * Abstract class whose purpose will be to store all projects in the memory and
 * control the projects IDs (all PIDs have to be different).
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class InMemoryProjectRepo extends InMemoryRepo<Project> implements
		ProjectRepository
{

	/**
	 * {@code Collection} that stores the {@code Project}s of this repository.
	 */
	private static final Collection<Project> projects = new TreeSet<>(
			new ProjectComparator());

	/**
	 * The last PID attributed to a {@link Project} plus one.
	 */
	private static long NEXT_PID_TO_BE_USED = 1;

	/**
	 * @see ProjectRepository#addProject(Project)
	 */
	public boolean addProject(Project project)
	{
		if (projects.add(project))
		{
			NEXT_PID_TO_BE_USED++;
			return true;
		}
		return false;
	}

	/**
	 * @see ProjectRepository#removeProject(Project)
	 */
	@Override
	public boolean removeProject(Project project)
	{
		return projects.remove(project);
	}

	/**
	 * @see Repository#removeAll()
	 */
	public void removeAll()
	{
		NEXT_PID_TO_BE_USED = 1;
		projects.clear();
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		for (Project project : projects)
			builder.append(project.toString()).append("\n");
		return builder.toString();
	}

	/**
	 * @see ProjectRepository#getProjectById(long)
	 */
	@Override
	public Project getProjectById(long projectId)
	{
		for (Project project : projects)
			if (project.getPID() == projectId)
				return project;
		return null;
	}

	/**
	 * @see ProjectRepository#getNextPID()
	 */
	@Override
	public long getNextPID()
	{
		return NEXT_PID_TO_BE_USED;
	}

	/**
	 * @see Repository#getAll()
	 */
	@Override
	public DatabaseElement[] getAll()
	{
		DatabaseElement[] all = new DatabaseElement[this.size()];
		int i = -1;
		for (DatabaseElement ele : projects)
			all[++i] = ele;
		return all;
	}

	/**
	 * @see Repository#size()
	 */
	@Override
	public int size()
	{
		return projects.size();
	}

}