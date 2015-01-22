package app.domain_commands;

import java.util.Collection;

import utils.AWorker;
import utils.Leader;
import utils.Project;
import app.AppElement;
import app.domain_commands.exceptions.IllegalWorkerTypeException;
import app.domain_commands.exceptions.NoManagerInProjectException;
import app.domain_commands.exceptions.NoSuchProjectException;
import app.domain_commands.exceptions.NoWorkersFoundException;
import app.repository.ProjectsRepository;

public class GetProjectWorkersFromRepo implements Command{

	ProjectsRepository pRepo;
	String pidString;
	String typeWorker;
	
	public GetProjectWorkersFromRepo(ProjectsRepository pRepo, String pidString, String typeWorker){
		
		if (pRepo == null || pidString == null || typeWorker == null){
			throw new IllegalArgumentException();
		}
		this.pRepo = pRepo;
		this.pidString = pidString;
		this.typeWorker = typeWorker;
	}
	
	@Override
	public AppElement[] call() throws NoSuchProjectException, IllegalWorkerTypeException, 
			NoManagerInProjectException, NoWorkersFoundException {
		
		long projectId = Long.parseLong(pidString);
		Project project = pRepo.getProjectById(projectId);
		
		if (project == null){
			throw new NoSuchProjectException("There is no project with that ID.");
		}
		
		if (typeWorker.equalsIgnoreCase("Manager")){
			return getManager(project);
		} else if (typeWorker.equalsIgnoreCase("Consultant")){
			return getWorkers(project);
		} else {
			throw new IllegalWorkerTypeException("Unrecognised type of worker.");
		}
	}

	/**
	 * Returns the {@code Manager} of the {@code Project}, if one has been assigned.
	 * @param project
	 * @return An array of {@code DatabaseElement} with one element carrying 
	 * the {@code Manager} of the {@code Project}
	 * @throws NoWorkersFoundException 
	 */
	private AppElement[] getWorkers(Project project) throws NoWorkersFoundException {
		Collection<AWorker> workers = project.getTeam();
		
		if (workers.isEmpty()){
			throw new NoWorkersFoundException("That project doesn't have any assigned workers.");
		}
		  
		AppElement[] workersArray = new AppElement[workers.size()];
		int i = 0;
		for (AWorker worker : workers){
			workersArray[i++] = worker;
		}
		return workersArray;
	}

	/**
	 * Returns and array with the {@code Consultant}s working on the {@code Project}, 
	 * if any have been assigned.
	 * @param project
	 * @return An array of {@code DatabaseElement} with the workers of the {@code Project}
	 * @throws NoManagerInProjectException 
	 */
	private AppElement[] getManager(Project project) throws NoManagerInProjectException {
		Leader manager = project.getManager();
		if(manager == null){
			throw new NoManagerInProjectException("That project has no assigned manager.");
		}
		AppElement[] managerAux = {manager};
		return managerAux;
	}
}
