package app.domainCommands;

import utils.Project;
import app.AppElement;
import app.domainCommands.exceptions.NoSuchProjectException;
import app.repository.ProjectsRepository;

public class GetProjectFromRepo implements Command{
	
	ProjectsRepository pRepo;
	String pidString;
	
	public GetProjectFromRepo(ProjectsRepository pRepo, String pidString){
		if (pRepo == null || pidString == null){
			throw new IllegalArgumentException();
		}
		this.pRepo = pRepo;
		this.pidString = pidString;
	}
	
	@Override
	public AppElement[] call() throws NoSuchProjectException {
		
		long pid = Long.parseLong(pidString);
		
		Project project = pRepo.getProjectById(pid);
		
		if(project == null){
			throw new NoSuchProjectException();
		}
		AppElement[] projectAux = {project};
		return projectAux;
	}

}
