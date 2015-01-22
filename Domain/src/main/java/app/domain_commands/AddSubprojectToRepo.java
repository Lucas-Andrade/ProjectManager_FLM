package app.domain_commands;

import utils.Project;
import app.AppElement;
import app.domain_commands.exceptions.AddedExistingSubproject;
import app.domain_commands.exceptions.NoSuchProjectException;
import app.domain_commands.exceptions.ProjectAddedToItselfException;
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
			throw new ProjectAddedToItselfException("Project was added to itself.");
		}
		Project project = pRepo.getProjectById(pid);
		Project subProject = pRepo.getProjectById(subPid);
		
		if (project == null || subProject == null){
			throw new NoSuchProjectException("There is no project with that ID.");
		}
		if(!project.addProject(subProject)){
			throw new AddedExistingSubproject("The project that was added is already subproject of that project.");
		}
		
		return new AppElement[]{project};
	}
}
