package commandProxy;

import utils.Project;
import app.AppElement;
import app.domainCommands.exceptions.NoSuchProjectException;
import app.repository.ProjectsRepository;

/**
 * This {@code Command} allows to get a {@code Project} from the repository.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class GetProjectFromRepo implements Command{
	
	/**
	 * The {@code PID} of the {@code Project} to be got.
	 */
	String pidString;
	
	/**
	 * Constructor of this {@code Command}.
	 * 
	 * @param pRepo
	 * The {@code ProjectsRepository} where the project is stored.
	 * @param pidString
	 * The {@code PID} of the {@code Project} to be got.
	 */
	public GetProjectFromRepo(String pidString){
		if (pidString == null){
			throw new IllegalArgumentException();
		}
		this.pidString = pidString;
	}
	
	/**
	 * @return an array of {@code AppElement}s containing the {@code Project}.
	 * @see Command#call()
	 */
	@Override
	public AppElement[] call() throws NoSuchProjectException {
		
		long pid = Long.parseLong(pidString);
		
		Project project = pRepo.getProjectById(pid);
		
		if(project == null){
			throw new NoSuchProjectException("There is no project with that ID.");
		}
		AppElement[] projectAux = {project};
		return projectAux;
	}

}
