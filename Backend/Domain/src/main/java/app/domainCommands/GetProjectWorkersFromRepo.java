package app.domainCommands;

import java.util.Collection;

import utils.AWorker;
import utils.Leader;
import utils.Project;
import app.AppElement;
import app.domainCommands.exceptions.IllegalWorkerTypeException;
import app.domainCommands.exceptions.NoManagerInProjectException;
import app.domainCommands.exceptions.NoSuchProjectException;
import app.domainCommands.exceptions.NoWorkersFoundException;
import app.repository.ProjectsRepository;

/**
 * This {@code Command} allows to get the {@code Team} or the {@code Manager} of
 * a particular {@code Project}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class GetProjectWorkersFromRepo implements Command {

	/**
	 * The {@code ProjectsRepository} where the {@code Project} is stored.
	 */
	ProjectsRepository pRepo;

	/**
	 * The {@code PID} of the {@code Project} from which we want to get the
	 * {@code Team}.
	 */
	String pidString;

	/**
	 * The type of worker to be obtained. {@code "consultant"} will get all the
	 * {@code Consultant}s working of the {@code Project} (the {@code Project}'s
	 * {@code Team}). {@code "manager"} will get the {@code Project}'s
	 * {@code Leader}.
	 */
	String typeWorker;

	/**
	 * Constructor of the {@code Command}.
	 * 
	 * @param pRepo
	 *            The {@code ProjectsRepository} where the {@code Project} is
	 *            stored.
	 * @param pidString
	 *            The {@code PID} of the {@code Project} from which we want to
	 *            get the {@code Team}.
	 * @param typeWorker
	 *            The type of worker to be obtained. {@code "consultant"} will
	 *            get all the {@code Consultant}s working of the {@code Project}
	 *            (the {@code Project}'s {@code Team}). {@code "manager"} will
	 *            get the {@code Project}'s {@code Leader}.
	 */
	public GetProjectWorkersFromRepo(ProjectsRepository pRepo,
			String pidString, String typeWorker) {

		if (pRepo == null || pidString == null || typeWorker == null) {
			throw new IllegalArgumentException();
		}
		this.pRepo = pRepo;
		this.pidString = pidString;
		this.typeWorker = typeWorker;
	}

	/**
	 * @return an array of {@code AppElement}s containing all the
	 *         {@code Project}'s {@code Consultant}s, or, the {@code Project}'s
	 *         {@code Leader}, depending on what {@code typeWorker} was
	 *         introduced in the constructor.
	 * @see Command#call()
	 */
	@Override
	public AppElement[] call() throws NoSuchProjectException,
			IllegalWorkerTypeException, NoManagerInProjectException,
			NoWorkersFoundException {

		long projectId = Long.parseLong(pidString);
		Project project = pRepo.getProjectById(projectId);

		if (project == null) {
			throw new NoSuchProjectException(
					"There is no project with that ID.");
		}

		if ("Manager".equalsIgnoreCase(typeWorker)) {
			return getManager(project);
		} else if ("Consultant".equalsIgnoreCase(typeWorker)) {
			return getWorkers(project);
		} else {
			throw new IllegalWorkerTypeException("Unrecognised type of worker.");
		}
	}

	/**
	 * Returns an array of {@code AppElement}s containing the {@code Manager} of
	 * the {@code Project}, if one has been assigned.
	 * 
	 * @param project
	 * @return An array of {@code AppElement} with one element carrying the
	 *         {@code Manager} of the {@code Project}.
	 * @throws NoWorkersFoundException
	 */
	private AppElement[] getWorkers(Project project)
			throws NoWorkersFoundException {
		Collection<AWorker> workers = project.getTeam();

		if (workers.isEmpty()) {
			throw new NoWorkersFoundException(
					"That project doesn't have any assigned workers.");
		}

		AppElement[] workersArray = new AppElement[workers.size()];
		int i = 0;
		for (AWorker worker : workers) {
			workersArray[i++] = worker;
		}
		return workersArray;
	}

	/**
	 * Returns and array of {@code AppElement}s containing the
	 * {@code Consultant}s working on the {@code Project}, if any have been
	 * assigned. In other words, returns all the {@code AWorker}s contained in
	 * the {@code Project}'s {@code Team}.
	 * 
	 * @param project
	 * @return An array of {@code DatabaseElement} with the workers of the
	 *         {@code Project}
	 * @throws NoManagerInProjectException
	 */
	private AppElement[] getManager(Project project)
			throws NoManagerInProjectException {
		Leader manager = project.getManager();
		if (manager == null) {
			throw new NoManagerInProjectException(
					"That project has no assigned manager.");
		}
		AppElement[] managerAux = { manager };
		return managerAux;
	}
}
