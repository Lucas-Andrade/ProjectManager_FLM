package commandProxy;

import java.util.ArrayList;
import java.util.Collection;

import utils.Project;
import app.AppElement;
import app.domainCommands.exceptions.NoSuchProjectException;
import app.repository.ProjectsRepository;

/**
 * This {@code Command} allows to remove a {@code Project} from the repository,
 * and also removes all of it's subprojects from the repository. As the
 * subprojects are deleted, if they have subprojects of their own, they are also
 * deleted from the repository, and so on.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015 *
 */
public class RemoveProjectToRepo implements Command {

	/**
	 * The {@code ProjectRepository} where the {@code Project} to be deleted is
	 * stored.
	 */
	ProjectsRepository pRepo;

	/**
	 * The {@code PID} of the {@code Project} that is to be removed from the
	 * repository.
	 */
	String pidString;

	/**
	 * Constructor of the {@code Command}.
	 * 
	 * @param pid
	 *            The {@code PID} of the {@code Project} that is to be removed
	 *            from the repository.
	 */
	public RemoveProjectToRepo(String pid) {
		if (pid == null) {
			throw new IllegalArgumentException();
		}
		this.pidString = pid;
	}

	/**
	 * @return An array of {@code AppElement}s containing all the {@code Project}s
	 * that were removed from the project.
	 * @see Command#call()
	 */
	@Override
	public AppElement[] call() throws NoSuchProjectException {

		long pid = Long.parseLong(pidString);

		Project parent = pRepo.getProjectById(pid);
		if (parent == null) {
			throw new NoSuchProjectException(
					"There is no project with that ID.");
		}
		Collection<Project> projectsToRemove = getAllProjectsToRemove(parent);

		for (Project project : projectsToRemove) {
			pRepo.removeProject(project);
		}

		return projectsToRemove
				.toArray(new AppElement[projectsToRemove.size()]);
	}

	/**
	 * Constructs a {@code Collection<Project>} with all the subprojects of the
	 * parent {@code Project}, including all the subprojects of the subprojects
	 * and so on. The parent itself will be included in the {@code Collection}.
	 * 
	 * @param parent
	 * @return A {@code Collection} with all the subprojects of a parent
	 *         {@code Project}, and all of their subprojects and so on.
	 */
	private Collection<Project> getAllProjectsToRemove(Project parent) {
		Collection<Project> toRemove = new ArrayList<Project>();
		toRemove.add(parent);

		Collection<Project> subprojects = parent.getContainerProject();
		for (Project project : subprojects) {
			toRemove.addAll(getAllProjectsToRemove(project));
		}
		parent.removeAllSubprojects();
		return toRemove;
	}
}
