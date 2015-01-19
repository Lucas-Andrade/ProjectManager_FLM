package app.commands;

import utils.Consultant;
import utils.Leader;
import utils.Project;
import app.AppElement;
import app.commands.exceptions.IllegalWorkerTypeException;
import app.commands.exceptions.NoSuchManagerException;
import app.commands.exceptions.NoSuchProjectException;
import app.commands.exceptions.NoSuchWorkerException;
import app.repository.ProjectsRepository;
import app.repository.WorkerRepository;

public class AddWorkerToProjectInRepo implements Command{

	ProjectsRepository pRepo;
	WorkerRepository wRepo; 
	String pidString;
	String cidString;
	String workerType;
	Long pid;
	Long cid;
	
	public AddWorkerToProjectInRepo(ProjectsRepository pRepo, WorkerRepository wRepo, 
			String pidString, String cidString, String workerType){
		if (pRepo == null || wRepo == null || pidString == null || cidString == null || workerType == null){
			throw new IllegalArgumentException();
		}
		this.pRepo = pRepo;
		this.wRepo = wRepo;
		this.pidString = pidString;
		this.cidString = cidString;
		this.workerType = workerType;
	}
	
	@Override
	public AppElement[] call() throws NoSuchProjectException, 
			IllegalWorkerTypeException, NoSuchManagerException, NoSuchWorkerException {
		
		pid = Long.parseLong(pidString);
		cid = Long.parseLong(cidString);
		
		Project project = pRepo.getProjectById(pid);
		if (project == null){
			throw new NoSuchProjectException();
		}

		if (workerType.equalsIgnoreCase("manager")){
			addManager();
		} else if (workerType.equalsIgnoreCase("consultant")) {
			addConsultant();
		} else{
			throw new IllegalWorkerTypeException();
		}		
		
		return new AppElement[]{project};
	}

	/**
	 * @see PostWorkerInProject#internalCall()
	 * 
	 * @param out
	 *            The {@link ResultOutputMethodToStream} that receives the
	 *            Results, treats them and gives them to a Stream.
	 *            
	 * @param projectId  {@code long} with the argument PID.
	 * @param workerId   {@code long} with the argument {@code AWorker}ID.
	 * @return True if successful, False if not.
	 * @throws NoSuchWorkerException 
	 */
	private void addConsultant() throws NoSuchWorkerException{
		Consultant consultant = wRepo.getConsultantByID(cid);

		if (consultant == null){
			throw new NoSuchWorkerException();
		}
		pRepo.getProjectById(pid).addWorker(consultant);
	}

	/**
	 * @see PostWorkerInProject#internalCall()
	 * 
	 * @param out
	 *            The {@link ResultOutputMethodToStream} that receives the
	 *            Results, treats them and gives them to a Stream.
	 *            
	 * @param projectId  {@code long} with the argument PID.
	 * @param workerId   {@code long} with the argument {@code AWorker}ID.
	 * @return True if successful, False if not.
	 * @throws NoSuchManagerException 
	 */
	private void addManager() throws NoSuchManagerException{

		Leader manager = wRepo.getManagerByID(cid);
		
		if (manager == null){
			throw new NoSuchManagerException();
		}
		pRepo.getProjectById(pid).setManager(manager);
	}

}
