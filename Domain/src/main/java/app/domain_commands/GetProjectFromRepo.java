package app.domain_commands;

import utils.Project;
import app.AppElement;
import app.domain_commands.exceptions.NoSuchProjectException;
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
			throw new NoSuchProjectException("There is no project with that ID.");
		}
		AppElement[] projectAux = {project};
		return projectAux;
	}

}
