package app.forWindow.RepositoryHolders;

import app.repository.ProjectsRepository;
import app.repository.UserRepository;
import app.repository.WorkerRepository;

/**
 * This class has three fields, one for each type of repository: {@code UserRepository}, {@code ProjectsRepository},
 * and {@code WorkerRepository}. The repositories are initialised here here for later use by the application. 
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 11/01/2015
 *
 */
public abstract class RepositoryHolder {
	
	private UserRepository uRepo;
	private ProjectsRepository pRepo;
	private WorkerRepository wRepo;
	
	/**
	 * Constructs the RepositoryHolder using the three repositories passed as parameter. These repositories 
	 * will be the initial content of the RepositoryHolder and can later be modified.
	 * @param uRepo - {@code UserRepository}
	 * @param pRepo - {@code ProjectsRepository}
	 * @param wRepo - {@code WorkerRepository}
	 */
	public RepositoryHolder(UserRepository uRepo, ProjectsRepository pRepo, WorkerRepository wRepo)
	{
		this.uRepo = uRepo;
		this.pRepo = pRepo;
		this.wRepo = wRepo;
	}
	
	/**
	 * @return the {@code UserRepository} held in {@code this} {@code RepositoryHolder}
	 */
	public UserRepository getUsersRepo()
	{
		return uRepo;
	}
	
	/**
	 * @return the {@code ProjectsRepository} held in {@code this} {@code RepositoryHolder}
	 */
	public ProjectsRepository getProjectsRepo()
	{
		return pRepo;
	}
	
	/**
	 * @return the {@code WorkerRepository} held in {@code this} {@code RepositoryHolder}
	 */
	public WorkerRepository getWorkersRepo()
	{
		return wRepo;
	}
}
