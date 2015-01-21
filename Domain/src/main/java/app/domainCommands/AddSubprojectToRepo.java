package app.domainCommands;

import utils.Project;
import app.AppElement;
import app.domainCommands.exceptions.AddedExistingSubproject;
import app.domainCommands.exceptions.NoSuchProjectException;
import app.domainCommands.exceptions.ProjectAddedToItselfException;
import app.repository.ProjectsRepository;

public class AddSubprojectToRepo implements Command {

	ProjectsRepository pRepo;
	String pidString;
	String subPidString;
	
	public AddSubprojectToRepo(ProjectsRepository pRepo, String pidString, String subPidString){
		
		if (pRepo == null || pidString == null || subPidString == null){
			throw new IllegalArgumentException();
		}
		this.pRepo = pRepo;
		this.pidString = pidString;
		this.subPidString = subPidString;
	}
	
	@Override
	public AppElement[] call() throws ProjectAddedToItselfException, AddedExistingSubproject, 
			NoSuchProjectException {
		long pid = Long.parseLong(pidString);
		long subPid = Long.parseLong(subPidString);

		if (pid == subPid){
			throw new ProjectAddedToItselfException();
		}
		Project project = pRepo.getProjectById(pid);
		Project subProject = pRepo.getProjectById(subPid);
		
		if (project == null || subProject == null){
			throw new NoSuchProjectException();
		}
		if(!project.addProject(subProject)){
			throw new AddedExistingSubproject();
		}
		
		return new AppElement[]{project};
	}
}
