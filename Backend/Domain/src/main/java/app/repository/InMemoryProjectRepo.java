package app.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import utils.Project;
import app.AppElement;

/**
 * Abstract class whose purpose will be to store all projects in the memory and
 * control the projects IDs (all PIDs have to be different).
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class InMemoryProjectRepo extends InMemoryRepo<Project> implements
		ProjectsRepository {

	/**
	 * {@code Collection} that stores the {@code Project}s of this repository.
	 */
	private static Map<Long, Project> projects = Collections.synchronizedMap(new HashMap<>());

	/**
	 * The last PID attributed to a {@link Project} plus one.
	 */
	private static long NEXT_PID_TO_BE_USED;

	/**
	 * Constructs a new empty {@code InMemoryProjectRepo}, in which the next {@code PID}
	 * to be used is set to 1.
	 */
	public InMemoryProjectRepo(){
		NEXT_PID_TO_BE_USED = 1;
	}
	
	/**
	 * @see ProjectsRepository#addProject(Project)
	 */
	public boolean addProject(ProjectCreationDescriptor<?> creationDescriptor) {
	
		if (projects.put(NEXT_PID_TO_BE_USED, creationDescriptor.build(NEXT_PID_TO_BE_USED)) != null) {
			NEXT_PID_TO_BE_USED++;
			return true;
		}
		return false;
	}

	/**
	 * @see ProjectsRepository#removeProject(Project)
	 */
	@Override
	public boolean removeProject(Project project) {
		return projects.remove(project.getPID(), project);
	}

	/**
	 * @see Repository#removeAll()
	 */
	public void removeAll() {
		NEXT_PID_TO_BE_USED = 1;
		projects.clear();
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Project project : projects.values()){
			builder.append(project.toString()).append("\n");
		}
		return builder.toString();
	}

	/**
	 * @see ProjectsRepository#getProjectById(long)
	 */
	@Override
	public Project getProjectById(long projectId) {
		Project project = projects.get(projectId);
      	return project;
	}

	/**
	 * @see ProjectsRepository#getNextPID()
	 */
	@Override
	public long getNextPID() {
		return NEXT_PID_TO_BE_USED;
	}

	/**
	 * @see Repository#getAll()
	 */
	@Override
	public Project[] getAll() {
		Project[] all = new Project[this.size()];
		int i = 0;
		for (Project ele : projects.values()){
			all[i++] = ele;
		}
		return all;
	}

	/**
	 * @see Repository#size()
	 */
	@Override
	public int size() {
		return projects.size();
	}

	@Override
	public JSONObject getJson() {
		JSONObject json = new JSONObject();
		for (AppElement ele : projects.values()){
			json.accumulate("All projects", ele.getJson());
		}
		return json;
	}

	@Override
	public boolean addProject(Project project) {
		// TODO Auto-generated method stub
		return false;
	}
}
