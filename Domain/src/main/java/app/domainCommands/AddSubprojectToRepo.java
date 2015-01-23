package app.domainCommands;

import utils.Project;
import app.AppElement;
import app.domainCommands.exceptions.AddedExistingSubproject;
import app.domainCommands.exceptions.NoSuchProjectException;
import app.domainCommands.exceptions.ProjectAddedToItselfException;
import app.repository.ProjectsRepository;

/**
 * This {@code Command} allows to add a subproject to a {@code Project}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class AddSubprojectToRepo implements Command {

	/**
	 * The {@code ProjectsRepository} where both projects are stored.
	 */
	ProjectsRepository pRepo;

	/**
	 * The {@code PID} of the {@code Project} to which will be added a
	 * subproject.
	 */
	String pidString;

	/**
	 * The {@code PID} of the {@code Project} that will be added as a
	 * subproject.
	 */
	String subPidString;

	/**
	 * Constructor of the {@code Command}.
	 * 
	 * @param pRepo
	 *            The {@code ProjectsRepository} where both projects are stored.
	 * @param pidString
	 *            The {@code PID} of the {@code Project} to which will be added
	 *            a subproject.
	 * @param subPidString
	 *            The {@code PID} of the {@code Project} that will be added as a
	 *            subproject.
	 */
	public AddSubprojectToRepo(ProjectsRepository pRepo, String pidString,
			String subPidString) {

		if (pRepo == null || pidString == null || subPidString == null) {
			throw new IllegalArgumentException();
		}
		this.pRepo = pRepo;
		this.pidString = pidString;
		this.subPidString = subPidString;
	}

	/**
	 * @return an array of {@code AppElement}s containing the {@code Project}
	 *         which the subproject was added to.
	 * @see Command#call()
	 */
	@Override
	public AppElement[] call() throws ProjectAddedToItselfException,
			AddedExistingSubproject, NoSuchProjectException {
		long pid = Long.parseLong(pidString);
		long subPid = Long.parseLong(subPidString);

		if (pid == subPid) {
			throw new ProjectAddedToItselfException(
					"Project was added to itself.");
		}
		Project project = pRepo.getProjectById(pid);
		Project subProject = pRepo.getProjectById(subPid);

		if (project == null || subProject == null) {
			throw new NoSuchProjectException(
					"There is no project with that ID.");
		}
		if (!project.addProject(subProject)) {
			throw new AddedExistingSubproject(
					"The project that was added is already subproject of that project.");
		}

		return new AppElement[] { project };
	}
}
