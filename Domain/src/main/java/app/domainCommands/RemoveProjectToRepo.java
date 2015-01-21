package app.domainCommands;

import java.util.ArrayList;
import java.util.Collection;

import utils.Project;
import app.AppElement;
import app.domainCommands.exceptions.NoSuchProjectException;
import app.repository.ProjectsRepository;

public class RemoveProjectToRepo implements Command{

	ProjectsRepository pRepo;
	String pidString;
	
	public RemoveProjectToRepo(ProjectsRepository pRepo, String pid){
		if (pRepo == null || pid == null){
			throw new IllegalArgumentException();
		}
		this.pRepo = pRepo;
		this.pidString = pid;
	}
	
	
	@Override
	public AppElement[] call() throws NoSuchProjectException {

		long pid = Long.parseLong(pidString);
		
		Project parent = pRepo.getProjectById(pid);
		if (parent == null){
			throw new NoSuchProjectException();
		}
		Collection<Project> projectsToRemove = getAllProjectsToRemove(parent);

		for (Project project : projectsToRemove){
			pRepo.removeProject(project);
		}
		
		return projectsToRemove.toArray(new AppElement[projectsToRemove.size()]);
	}

	/**
	 * Constructs a {@code Collection<Project>} with all the subprojects of the
	 * parent {@code Project}, including all the subprojects of the subprojects
	 * and so on. The parent itself will be included in the {@code Collection}.
	 * 
	 * @param parent
	 * @return A {@code Collection} with all the subprojects of a parent
	 *         {@code Project}, and all of their subprojects and so on.
	 */
	private Collection<Project> getAllProjectsToRemove(Project parent){
		Collection<Project> toRemove = new ArrayList<Project>();
		toRemove.add(parent);

		Collection<Project> subprojects = parent.getContainerProject();
		for (Project project : subprojects){
			toRemove.addAll(getAllProjectsToRemove(project));
		}
		parent.removeAllSubprojects();
		return toRemove;
	}
}
