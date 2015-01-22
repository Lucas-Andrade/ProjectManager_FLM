package app.domainCommands;

import java.util.Collection;

import utils.Project;
import app.AppElement;
import app.domainCommands.exceptions.NoSuchProjectException;
import app.domainCommands.exceptions.NoSuchSubprojectsException;
import app.repository.ProjectsRepository;

public class GetSubprojectsFromRepo implements Command{

	ProjectsRepository pRepo;
	String pidString;
	
	public GetSubprojectsFromRepo(ProjectsRepository pRepo, String pidString){
		if (pRepo == null || pidString == null){
			throw new IllegalArgumentException();
		}
		this.pRepo = pRepo;
		this.pidString = pidString;
	}
	
	@Override
	public AppElement[] call() throws NoSuchProjectException, NoSuchSubprojectsException {
		
		long pid = Long.parseLong(pidString);
		Project project = pRepo.getProjectById(pid);
		if(project == null){
			throw new NoSuchProjectException("There is no worker with that ID.");
		}
		
		int subprojectsNumber = project.getSubprojectsNumber();
		if(subprojectsNumber == 0){
			throw new NoSuchSubprojectsException("That project has no subprojects.");
		}
		Collection<Project> subprojects = project.getContainerProject();
		AppElement[] subprojectAux = new AppElement[subprojectsNumber];
		int i = 0;
		
		for (Project subproject : subprojects){
			subprojectAux[i++] = subproject;
		}
		return subprojectAux;
	}

}
