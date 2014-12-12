package utils;

import app.elements.ProjectInterface;

/**
 * Class {@code Project} whose instances will represent a project.
 * 
 * Extends {@link Element}.
 */
public class Project extends Element implements ProjectInterface
{

	private final String name;
	private Leader manager;
	private final Local local;
	private final Team team;
	private final ProjectsContainer projectsContainer;
	private final long pid;

	/**
	 * Project constructor that will receive as parameter the name of the
	 * project, the local it will take place, the manager in charge of the
	 * project and the team that will work on it.
	 * 
	 * Throws {@link IllegalArgumentException} if any of the given parameters
	 * are null.
	 * 
	 * @param name
	 *            - the name of the project.
	 * @param local
	 *            - the local where the project will take place.
	 * @param manager
	 *            - the {@code Leader} in charge of the project.
	 * @param team
	 *            - the {@code Team} that will be responsible for executing the
	 *            project.
	 */
	public Project(String name, Local local, Leader manager, Team team, long pid)
	{
		if (name == null || local == null || team == null) // o manager pode ser
															// null.
			throw new IllegalArgumentException();

		this.name = name;
		this.local = local;
		this.team = team;
		this.manager = manager;
		this.projectsContainer = new ProjectsContainer();
		this.pid = pid;
	}

	/**
	 * Project constructor that will receive as parameter the name of the
	 * project, the local it will take place, the manager in charge of the
	 * project and the team that will work on it.
	 * 
	 * Throws {@link IllegalArgumentException} if any of the given parameters
	 * are null.
	 * 
	 * @param name
	 *            - the name of the project.
	 * @param local
	 *            - the local where the project will take place.
	 * @param team
	 *            - the {@code Team} that will be responsible for executing the
	 *            project.
	 */
	public Project(Local local, long pid)
	{
		this(String.valueOf(pid), local, null, new Team(), pid);
	}

	/**
	 * Method that will allow a worker to be added to the {@code team}. If a
	 * worker with the same name is already on the {@code team} or is the
	 * {@code manager} the worker will not be added and the method will return
	 * false.
	 * 
	 * Throws {@link IllegalArgumentException} if the given parameter is null.
	 * 
	 * @param worker
	 *            - The {@code AWorker} to be add to the {@code team}.
	 * 
	 * @return true if the worker is successfully added and false otherwise.
	 */
	public boolean addWorker(AWorker worker)
	{

		if (worker == null)
			throw new IllegalArgumentException();

		if (worker.getName().equals(manager.getName()))
			return false;

		return team.addElement(worker);
	}

	/**
	 * Method that will allow a subproject to be added to the
	 * {@code projectsContainer}. If a sub_project with the same name already
	 * exists it will not be added and the method will return false.
	 * 
	 * Throws {@link IllegalArgumentException} if the given parameter is null.
	 * 
	 * @param project
	 *            - the subproject to the added to the {@code projectsContainer}
	 *            .
	 * 
	 * @return true if the subproject is successfully added and false otherwise.
	 */
	public boolean addProject(Project project)
	{

		if (project == null)
			throw new IllegalArgumentException();

		if (NameTester.addName(project.getName()))
			return projectsContainer.addElement(project);

		return false;
	}

	/**
	 * Method that will allow a worker to be removed from the {@code team} given
	 * its name. If a worker with the same name doesn't exist on the
	 * {@code team} no worker will be removed and the method will return false.
	 * 
	 * Uses the method {@code getWorkerByName()} from this same class as an
	 * auxiliary method.
	 * 
	 * Throws {@link IllegalArgumentException} if the given parameter is null.
	 * 
	 * @param name
	 *            - The name of the {@code AWorker} to be removed from the
	 *            {@code team}.
	 * 
	 * @return true if the worker is successfully removed and false otherwise.
	 */
	public boolean removeAWorker(String name)
	{

		if (name == null)
			throw new IllegalArgumentException();

		return team.remove(getWorkerByName(name));
	}

	/**
	 * Method that will allow a subproject to be removed from the
	 * {@code projectsContainer} given its name. If a subproject with the same
	 * name doesn't exist on the {@code projectsContainer} no project will be
	 * removed and the method will return false.
	 * 
	 * Uses the method {@code getSubProjectByName()} from this same class as an
	 * auxiliary method.
	 * 
	 * Throws {@link IllegalArgumentException} if the given parameter is null.
	 * 
	 * @param name
	 *            - The name of the {@code Project} to be removed from the
	 *            {@code projectsContainer} .
	 * 
	 * @return true if the subproject is successfully removed and false
	 *         otherwise.
	 */
	public boolean removeProject(String name)
	{

		if (name == null)
			throw new IllegalArgumentException();

		if (projectsContainer.remove(getSubProjectByName(name)))
			return NameTester.removeName(name);

		return false;
	}

	/**
	 * Method that will allow a worker, belonging to the {@code team}, to be
	 * found, given its name, and returns it. If a worker with the same name
	 * doesn't exist on the {@code team} it will return null.
	 * 
	 * It will use the implementation of the method {@code getElementByName()}
	 * existing in the {@link Team} class.
	 * 
	 * Throws {@link IllegalArgumentException} if the given parameter is null.
	 * 
	 * @param name
	 *            - The name of the {@code AWorker} to be found in the
	 *            {@code team}.
	 * 
	 * @return returns the worker with corresponding name or null if no worker
	 *         in the {@code team} verifies this condition.
	 */
	public AWorker getWorkerByName(String name)
	{

		if (name == null)
			throw new IllegalArgumentException();

		return (AWorker) team.getElementByName(name);
	}

	/**
	 * Method that will allow a project, belonging to the subprojects contained
	 * in {@code projectContainer} or their own subprojects, to be found, given
	 * its name, and returns it. If a project with the same name doesn't exist
	 * it will return null.
	 * 
	 * It will use the implementation of the method {@code getElementByName()}
	 * existing in the {@link ProjectsContainer} class.
	 * 
	 * Throws {@link IllegalArgumentException} if the given parameter is null.
	 * 
	 * @param name
	 *            - The name of the {@code Project} to be found in the
	 *            {@code projectsContainer}.
	 * 
	 * @return returns the project with corresponding name or null if no project
	 *         with the given name exists.
	 */
	public Project getSubProjectByName(String name)
	{

		if (name == null)
			throw new IllegalArgumentException();

		return (this.getName().equals(name) ? this
				: (Project) projectsContainer.getElementByName(name));
	}

	/**
	 * @return returns and unmodifiable view of {@code team} provided by the
	 *         method {@code getElementsList()}.
	 */
	public Iterable<AWorker> getTeam()
	{

		return this.team.getElementsList();
	}

	/**
	 * @return and unmodifiable view of {@code projectsContainer} provided by
	 *         the method {@code getElementsList()}.
	 */
	@Override
	public Iterable<Project> getContainerProject()
	{

		return this.projectsContainer.getElementsList();
	}

	/**
	 * @return {@link Local} of the {@code Project}.
	 */
	public Local getLocal()
	{

		return local;
	}

	/**
	 * Override of the method {@code getName()} from the {@code IName}
	 * Interface.
	 */
	@Override
	public String getName()
	{

		return name;
	}

	/**
	 * Override of the method {@code toString()} from {@code Object}.
	 */
	@Override
	public String toString()
	{

		return toString(0);
	}

	/**
	 * allows to add characters at the beginning of each line of the
	 * {@code toString()} this allows to indent subprojects to clearly show
	 * which projects belong to what main project
	 * 
	 * @param space
	 * @return information about the project
	 */
	protected String toString(int nr)
	{
		StringBuilder spaceBuilder = new StringBuilder();
		for (int i = 0; i < nr * 5; i++)
			spaceBuilder.append(" ");
		String space = spaceBuilder.toString();

		StringBuilder builder = new StringBuilder();

		builder.append(space)
				.append("Project ID: ")
				.append(pid)
				.append("\n")
				.append(space)
				.append("Cost: ")
				.append(getCost())
				.append(" Euros")
				.append("\n")
				.append(space)
				.append("Local: ")
				.append(local.toString())
				.append("\n")
				.append(space)
				.append("Team: ")
				.append(team.toString())
				.append("\n")
				.append(space)
				.append("Subprojects: ")
				.append(projectsContainer.size() == 0 ? "None." : "\n"
						+ projectsContainer.toString(nr + 1)).append("\n");

		return builder.toString();
	}

	/**
	 * Override of the method {@code getCosts()} from the {@code ICost}
	 * Interface.
	 */
	@Override
	public double getCost()
	{

		double managerCost;
		try
		{
			managerCost = manager.getCost();
		} catch (NullPointerException e)
		{
			managerCost = 0;
		}

		return local.getCost() + managerCost + team.getCost()
				+ projectsContainer.getCost();
	}

	/**
	 * Override of the method {@code compareTo()} from the {@link Comparable}
	 * Interface.
	 * 
	 * It will allow the subprojects to be ordered alphabetically in the
	 * {@code projectsContainer}.
	 * 
	 * Two projects are considered to be equal when their {@code name} and
	 * {@code local} are the same. It is consistent with the method
	 * {@code equals} from {@code Object}.
	 */
	@Override
	public int compareTo(Element element)
	{

		if (element == null)
			throw new IllegalArgumentException();

		if (!(element instanceof Project))
			throw new ClassCastException();

		if (this.getName().toLowerCase()
				.equals(element.getName().toLowerCase()))
			return this.getName().compareTo(element.getName());

		return this.getName().toLowerCase()
				.compareTo(element.getName().toLowerCase());

	}

	/**
	 * Override of the method {@code hashCode()} from {@code Object}.
	 */
	@Override
	public int hashCode()
	{

		final int prime = 31;
		int result = 1;

		result = prime * result + ((local == null) ? 0 : local.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());

		return result;
	}

	/**
	 * Override of the method {@code equals()} from {@code Object} to be
	 * consistent with the {@code compareTo()} method.
	 */
	@Override
	public boolean equals(Object project)
	{

		if (this == project)
			return true;

		if (project == null)
			return false;

		if (getClass() != project.getClass())
			return false;

		if (((Project) project).compareTo(this) != 0)
			return false;

		return true;
	}

	/**
	 * @return The Manager of the {@code Project}.
	 */
	public Leader getManager()
	{
		return manager;
	}

	/**
	 * @param manager
	 *            The manager to set in the {@code Project}.
	 */
	public void setManager(Leader manager)
	{
		this.manager = manager;
	}

	/**
	 * @return The Project Identification (PID).
	 */
	@Override
	public long getPID()
	{
		return pid;
	}

}