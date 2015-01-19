package app.commands;

import java.util.Collection;

import utils.AWorker;
import utils.Leader;
import utils.Project;
import app.AppElement;
import app.commands.exceptions.NoManagerFoundException;
import app.commands.exceptions.NoWorkersFoundException;
import app.commands.exceptions.NoSuchProjectException;
import app.commands.exceptions.UnrecognisedWorkerTypeException;
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
	public AppElement[] call() throws NoSuchProjectException, UnrecognisedWorkerTypeException, 
			NoManagerFoundException, NoWorkersFoundException {
		
		long projectId = Long.parseLong(pidString);
		Project project = pRepo.getProjectById(projectId);
		
		if (project == null){
			throw new NoSuchProjectException();
		}
		
		if (typeWorker.equalsIgnoreCase("Manager")){
			return getManager(project);
		} else if (typeWorker.equalsIgnoreCase("Consultant")){
			return getWorkers(project);
		} else {
			throw new UnrecognisedWorkerTypeException();
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
			throw new NoWorkersFoundException();
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
	 * @throws NoManagerFoundException 
	 */
	private AppElement[] getManager(Project project) throws NoManagerFoundException {
		Leader manager = project.getManager();
		if(manager == null){
			throw new NoManagerFoundException();
		}
		AppElement[] managerAux = {manager};
		return managerAux;
	}
}
