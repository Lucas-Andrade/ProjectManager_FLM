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
	 * Synchronized {@code Map} that stores the {@code Project}s of this
	 * repository.
	 */
	private static final Map<Long, Project> PROJECTS = Collections
			.synchronizedMap(new HashMap<>());

	/**
	 * The last PID attributed to a {@link Project} plus one.
	 */
	private volatile static long NEXT_PID_TO_BE_USED;

	/**
	 * The lock to be used in instructions where
	 * {@code this#NEXT_PID_TO_BE_USED} cannot be modified by concurrent
	 * threads. This lock CANNOT be used after a synchronized block with the
	 * lock {@code this#pidResetLock}.
	 */
	private final Object pidLock;

	/**
	 * The lock to be used when {@code this#NEXT_PID_TO_BE_USED} is reset, by
	 * all instructions that associate a Project to a PID in the repository,
	 * thus preventing the possibility of a Project being added to this
	 * repository with a PID higher than the {@code this#NEXT_PID_TO_BE_USED}.
	 */
	private final Object pidResetLock;

	/**
	 * Constructs a new empty {@code InMemoryProjectRepo}, in which the next
	 * {@code PID} to be used is set to 1.
	 */
	public InMemoryProjectRepo() {
		NEXT_PID_TO_BE_USED = 1;
		pidLock = new Object();
		pidResetLock = new Object();
	}

	/**
	 * @see ProjectsRepository#addProject(ProjectCreationDescriptor)
	 */
	@Override
	public Long addProject(ProjectCreationDescriptor creationDescriptor) {
		Long newProjectPID;
		Project newProject;

		synchronized (pidLock) {
			newProjectPID = NEXT_PID_TO_BE_USED++;
			newProject = creationDescriptor.build(newProjectPID);
			if (newProject == null) {
				NEXT_PID_TO_BE_USED--;
				return null;
			}
		}
		synchronized (pidResetLock) {
			if (NEXT_PID_TO_BE_USED > newProjectPID
					&& PROJECTS.putIfAbsent(newProjectPID, newProject) == null)
				return newProjectPID;
		}

		return null;
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
	public void removeAll() {
		synchronized (pidResetLock) {
			NEXT_PID_TO_BE_USED = 1;
			PROJECTS.clear();
		}
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		Iterable<Project> projects = PROJECTS.values();
		StringBuilder builder = new StringBuilder();
		for (Project project : projects) {
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
		Iterable<Project> projects = PROJECTS.values();
		int i = 0;
		for (Project ele : projects) {
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
		Iterable<Project> projects = PROJECTS.values();
		for (AppElement ele : projects) {
			json.accumulate("All projects", ele.getJson());
		}
		return json;
	}

}
