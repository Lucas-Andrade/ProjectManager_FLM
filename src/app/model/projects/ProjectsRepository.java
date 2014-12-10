package app.model.projects;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;

//ESTA CLASSE NÃO TEM UTILIZAÇÃO, TALVEZ NO FUTURO...
/**
 * Abstract class whose purpose will be to store all projects in existence and control
 * the projects IDs (all PIDs have to be different).
 */
public abstract class ProjectsRepository
{
	private static final Collection<Project> projects = new TreeSet<>();
	private static long nextPIDToBeUsed = 1;

	public static boolean addProject(Project project) {
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
		String projectsStr = "";
		for (Project project : projects)
			projectsStr += project.toString();
		return projectsStr;
	}
}