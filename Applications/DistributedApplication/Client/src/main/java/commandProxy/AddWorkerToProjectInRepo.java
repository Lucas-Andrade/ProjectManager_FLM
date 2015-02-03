package commandProxy;

import utils.AWorker;
import utils.Leader;
import utils.Project;
import app.AppElement;
import app.domainCommands.exceptions.IllegalWorkerTypeException;
import app.domainCommands.exceptions.NoSuchManagerException;
import app.domainCommands.exceptions.NoSuchProjectException;
import app.domainCommands.exceptions.NoSuchWorkerException;
import app.domainCommands.exceptions.WorkerNotAddedException;
import app.repository.ProjectsRepository;
import app.repository.WorkerRepository;

/**
 * This {@code Command} allows to add {@code AWorker}s to a {@code Project}. It allows
 * both to add {@code AWorker}s to the {@code Team}, and to set a new {@code Leader} as 
 * the {@code Project}'s {@code Leader}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class AddWorkerToProjectInRepo implements Command{
	
	/**
	 * The {@code PID} of the {@code Project}.
	 */
	String pidString;
	
	/**
	 * The {@code CID} if the {@code AWorker}.
	 */
	String cidString;
	
	/**
	 * The type of the worker to be added. {@code "consultant"} will try to add the {@code AWorker} to 
	 * the {@code Project}'s {@code Team}. {@code "manager"} will try to set the {@code AWorker} as the 
	 * {@code Project}'s {@code Leader}.
	 */
	String workerType;
	
	/**
	 * The {@code PID} of the {@code Project}.
	 */
	Long pid;
	
	/**
	 * The {@code CID} if the {@code AWorker}.
	 */
	Long cid;
	
	/**
	 * Constructor of this {@code Command}.
	 * 
	 * @param pRepo
	 * The {@code ProjectsRepository} where the {@code Project} which will be added new {@code AWorker}s.
	 * is stored. 
	 * @param wRepo
	 * The {@code WorkerRepository} where the {@code AWorker} to be added is stored.
	 * @param pidString
	 * The {@code PID} of the {@code Project}.
	 * @param cidString
	 * The {@code CID} if the {@code AWorker}.
	 * @param workerType
	 * The type of the worker to be added. {@code "consultant"} will try to add the {@code AWorker} to 
	 * the {@code Project}'s {@code Team}. {@code "manager"} will try to set the {@code AWorker} as the 
	 * {@code Project}'s {@code Leader}.
	 */
	public AddWorkerToProjectInRepo(String pidString, String cidString, String workerType){
		if (pidString == null || cidString == null || workerType == null){
			throw new IllegalArgumentException();
		}
		this.pidString = pidString;
		this.cidString = cidString;
		this.workerType = workerType;
	}
	
	/**
	 * @return an array of {@code AppElement}s containing the modified {@code Project}.
	 * @see Command#call()
	 */
	@Override
	public AppElement[] call() throws NoSuchProjectException, 
			IllegalWorkerTypeException, NoSuchManagerException, NoSuchWorkerException, WorkerNotAddedException {
		
		pid = Long.parseLong(pidString);
		cid = Long.parseLong(cidString);
		
		Project project = pRepo.getProjectById(pid);
		if (project == null){
			throw new NoSuchProjectException("There is no project with that ID.");
		}

		if ("manager".equalsIgnoreCase(workerType)){
			addManager();
		} else if ("consultant".equalsIgnoreCase(workerType)) {
			addConsultant();
		} else{
			throw new IllegalWorkerTypeException("Unercognised worker type.");
		}		
		
		return new AppElement[]{project};
	}

	/** 
	 * Tries to get a {@code AWorker} with the {@code CID} passed as parameter to the constructor from the repository,
	 * and then, tries to add that consultant to the {@code Project}.
	 * 
	 * @throws NoSuchWorkerException 
	 * @throws WorkerNotAddedException 
	 */
	private void addConsultant() throws NoSuchWorkerException, WorkerNotAddedException{
		AWorker consultant = wRepo.getConsultantByID(cid);
		
		if (consultant == null){
			throw new NoSuchWorkerException("There is no worker with that ID.");
		}
		
		if(! pRepo.getProjectById(pid).addWorker(consultant)){
			throw new WorkerNotAddedException("Worker could not be added. The worker may already be in the project's team."); 
		}
	}

	/**
	 * Tries to get a {@code Leader} with the {@code CID} passed as parameter to the constructor from the repository,
	 * and then, sets the {@code Leader} as the {@code Project}'s {@code Manager}.
	 * 
	 * @throws NoSuchManagerException 
	 */
	private void addManager() throws NoSuchManagerException{

		Leader manager = wRepo.getManagerByID(cid);
		
		if (manager == null){
			throw new NoSuchManagerException("There is no manager with that ID.");
		}
		pRepo.getProjectById(pid).setManager(manager);
	}

}
