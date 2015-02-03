package app.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import utils.Project;
import app.AppElement;
import app.elements.mutable.ProjectCreationDescriptor;

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
	 * {@code Map} that stores the {@code Project}s of this repository.
	 */
	private static final Map<Long, Project> PROJECTS = Collections
			.synchronizedMap(new HashMap<>());

	/**
	 * The last PID attributed to a {@link Project} plus one.
	 */
	private volatile static long NEXT_PID_TO_BE_USED;

	/**
	 * Constructs a new empty {@code InMemoryProjectRepo}, in which the next
	 * {@code PID} to be used is set to 1.
	 */
	public InMemoryProjectRepo() {
		NEXT_PID_TO_BE_USED = 1;
	}

	/**
	 * @see ProjectsRepository#addProject(ProjectCreationDescriptor)
	 */
	public synchronized Long addProject(ProjectCreationDescriptor creationDescriptor) {
		Long newProjectPID = NEXT_PID_TO_BE_USED;
		Project newProject = creationDescriptor.build(newProjectPID);
		if (newProject == null) {
			return null;
		}
		PROJECTS.putIfAbsent(newProjectPID, newProject);
		NEXT_PID_TO_BE_USED++;
		return newProjectPID;
	}

	/**
	 * @see ProjectsRepository#removeProject(Project)
	 */
	@Override
	public boolean removeProject(Project project) {
		return PROJECTS.remove(project.getPID(), project);
	}

	/**
	 * @see Repository#removeAll()
	 */
	public synchronized void removeAll() {
		NEXT_PID_TO_BE_USED = 1;
		PROJECTS.clear();
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Project project : PROJECTS.values()) {
			builder.append(project.toString()).append("\n");
		}
		return builder.toString();
	}

	/**
	 * @see ProjectsRepository#getProjectById(long)
	 */
	@Override
	public Project getProjectById(long projectId) {
		Project project = PROJECTS.get(projectId);
		return project;
	}

	/**
	 * @see Repository#getAll()
	 */
	@Override
	public Project[] getAll() {
		Project[] all = new Project[this.size()];
		int i = 0;
		for (Project ele : PROJECTS.values()) {
			all[i++] = ele;
		}
		return all;
	}

	/**
	 * @see Repository#size()
	 */
	@Override
	public int size() {
		return PROJECTS.size();
	}

	@Override
	public JSONObject getJson() {
		JSONObject json = new JSONObject();
		for (AppElement ele : PROJECTS.values()) {
			json.accumulate("All projects", ele.getJson());
		}
		return json;
	}

}
