package commandProxy;

import java.util.Collection;

import utils.Project;
import app.AppElement;
import app.domainCommands.exceptions.NoSuchProjectException;
import app.domainCommands.exceptions.NoSuchSubprojectsException;
import app.repository.ProjectsRepository;

/**
 * This {@code Command} allows to get all the subprojects of a particular
 * {@code Project}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 * 
 */
public class GetSubprojectsFromRepo implements Command {

	
	/**
	 * The {@code PID} of the project, from which the subprojects are to be got.
	 */
	String pidString;

	/**
	 * Constructor of the {@code Command}.
	 * 
	 * @param pRepo
	 * The {@code ProjectsRepository} where the {@code Project} is stored.
	 * @param pidString
	 * The {@code PID} of the project, from which the subprojects are to be got. 
	 */
	public GetSubprojectsFromRepo(String pidString) {
		if (pidString == null) {
			throw new IllegalArgumentException();
		}
		this.pidString = pidString;
	}

	/**
	 * @return an array of {@code AppElement}s containing all the {@code Project}'s
	 * subprojects.
	 * @see Command#call()
	 */
	@Override
	public AppElement[] call() throws NoSuchProjectException,
			NoSuchSubprojectsException {

		long pid = Long.parseLong(pidString);
		Project project = pRepo.getProjectById(pid);
		if (project == null) {
			throw new NoSuchProjectException("There is no worker with that ID.");
		}

		int subprojectsNumber = project.getSubprojectsNumber();
		if (subprojectsNumber == 0) {
			throw new NoSuchSubprojectsException(
					"That project has no subprojects.");
		}
		Collection<Project> subprojects = project.getContainerProject();
		AppElement[] subprojectAux = new AppElement[subprojectsNumber];
		int i = 0;

		for (Project subproject : subprojects) {
			subprojectAux[i++] = subproject;
		}
		return subprojectAux;
	}

}
