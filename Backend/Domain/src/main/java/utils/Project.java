package utils;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Iterator;

import org.json.JSONObject;

/**
 * Class {@code Project} whose instances will represent a project.
 * 
 * Extends {@link UtilsElement}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class Project implements IProject{

	private final String name;
	private Leader manager;
	private final Local local;
	private final Team team;
	private final ProjectsContainer projectsContainer;
	private final long pid;

	/**
	 * Project constructor that will receive as parameter the name of the
	 * project, the local it will take place, the manager in charge of the
	 * project, the team that will work on it and the Project ID.
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
	 * @param pid
	 *            - the project's identification (PID).
	 */
	private Project(String name, Local local, Leader manager, Team team, long pid){
		if (name == null || local == null || team == null){
			throw new IllegalArgumentException("Name, Local and Team can't be null.");
		}
		this.name = name;
		this.local = local;
		this.team = team;
		this.manager = manager;
		this.pid = pid;
		projectsContainer = new ProjectsContainer();
	}

	/**
	 * Project constructor that will receive as parameter the local it will take
	 * place and the Project ID. The name of the project will be equal to the
	 * PID and the team will be a new empty one.
	 * 
	 * Throws {@link IllegalArgumentException} if any of the given parameters
	 * are null.
	 * 
	 * @param local
	 *            - the local where the project will take place.
	 * @param pid
	 *            - the project's identification (PID).
	 */
	public Project(Local local, long pid){
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
	public boolean addWorker(AWorker worker){
		if (worker == null){
			throw new IllegalArgumentException();
		}
		if (manager != null && worker.equals(manager)){
			return false;
		}
		return team.addElement(worker);
	}

	/**
	 * Method that will allow a project to be added as a Sub{@code Project} (it
	 * is added to {@code this} {@code Project#projectsContainer}). If the
	 * project's to be added as Sub{@code Project} name already exists in
	 * {@link NameTester}, the project won't be added as a Sub{@code Project}
	 * and the method will return false. If successful this method also adds the
	 * added Sub{@code Project}'s name to the {@link NameTester} ({@see
	 * NameTester#addName(String)}; {@see Project#removeProject(Project)}).
	 * 
	 * Throws {@link IllegalArgumentException} if the given parameter is null.
	 * 
	 * @param project
	 *            The subproject to the added to the {@code projectsContainer}.
	 * 
	 * @return true if the subproject is successfully added and false otherwise.
	 */
	public boolean addProject(Project project){
		if (project == null){
			throw new IllegalArgumentException();
		}
		if (NameTester.addName(project.getName())){
			return projectsContainer.addElement(project);
		}
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
	public boolean removeAWorker(String name){

		if (name == null){
			throw new IllegalArgumentException();
		}
		return team.remove(getWorkerByName(name));
	}

	/**
	 * Method that will allow a Sub{@code Project} to be removed from the
	 * {@code this} {@code ProjectsContainer} given its name. If a subproject
	 * with the same name doesn't exist on the {@code this}
	 * {@code Project#projectsContainer} no project will be removed and the
	 * method will return false. If successful this method also removes the
	 * removed Sub{@code Project}'s name from the {@link NameTester} ({@see
	 * NameTester#removeName(String)}; {@see Project#addProject(Project)}).
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
	public boolean removeProject(String name){
		return removeProject(getSubProjectByName(name));
	}
	
	/**
	 * Method that will allow a Sub{@code Project} to be removed from the
	 * {@code this} {@code ProjectsContainer}. If the {@code Project}
	 * doesn't exist on {@code this} {@code Project#projectsContainer} no project 
	 * will be removed and the method will return false. If successful this method 
	 * also removes the removed Sub{@code Project}'s name from the {@link NameTester} ({@see
	 * NameTester#removeName(String)}; {@see Project#addProject(Project)}).
	 * 
	 * Uses the method {@code getSubProjectByName()} from this same class as an
	 * auxiliary method.
	 * 
	 * Throws {@link IllegalArgumentException} if the given parameter is null.
	 * 
	 * @param project
	 *            - The {@code Project} to be removed from the
	 *            {@code projectsContainer} .
	 * 
	 * @return true if the subproject is successfully removed and false
	 *         otherwise.
	 */
	public boolean removeProject(Project project){
		if (name == null){
			throw new IllegalArgumentException();
		}
		if (projectsContainer.remove(project)){
			return NameTester.removeName(name);
		}
		return false;
	}
	
	public void removeAllSubprojects(){
		NameTester.removeAll();
		projectsContainer.removeAll();
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
	public AWorker getWorkerByName(String name){

		if (name == null){
			throw new IllegalArgumentException();
		}
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
	public Project getSubProjectByName(String name){

		if (name == null){
			throw new IllegalArgumentException();
		}
		return this.getName().equals(name) ? this
				: (Project) projectsContainer.getElementByName(name);
	}

	/**
	 * @return returns and unmodifiable view of {@code team} provided by the
	 *         method {@code getElementsList()}.
	 */
	public Collection<AWorker> getTeam(){
		return this.team.getElementsList();
	}

	/**
	 * @return and unmodifiable view of {@code projectsContainer} provided by
	 *         the method {@code getElementsList()}.
	 */
	@Override
	public Collection<Project> getContainerProject(){
		return this.projectsContainer.getElementsList();
	}

	/**
	 * @return {@link Local} of the {@code Project}.
	 */
	public Local getLocal(){
		return local;
	}

	/**
	 * Override of the method {@code getName()} from the {@code IName}
	 * Interface.
	 */
	@Override
	public String getName(){
		return name;
	}

	/**
	 * Override of the method {@code toString()} from {@code Object}.
	 */
	@Override
	public String toString(){
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
	protected String toString(int nr){
		
		StringBuilder spaceBuilder = new StringBuilder();
		for (int i = 0; i < nr * 5; i++){
			spaceBuilder.append(" ");
		}
		String space = spaceBuilder.toString();

		StringBuilder builder = new StringBuilder();
		DecimalFormat df = new DecimalFormat("#.##");
		builder.append(space).append("Project ID: ").append(pid).append("\n")
				.append(space).append("Cost: ").append(df.format(getCost())).append(" Euros").append("\n")
				.append(space).append("Local: ").append(local.toString()).append("\n");
		if(manager != null){
			builder.append(space).append("Manager: ").append(manager.toString()).append("\n");
		}
		builder.append(space).append("Team: ").append("\n")
				.append(space).append(team.toString(5))//.append("\n")
				.append(space).append("Subprojects: ")
				.append(projectsContainer.isEmpty() ? "None." : "\n"
						+ projectsContainer.toString(nr + 1)).append("\n");

		return builder.toString();
	}
	
	@Override
	public JSONObject getJson(){
		DecimalFormat df = new DecimalFormat("#.##");
		JSONObject json = new JSONObject();
		json.put("Subprojects", projectsContainer.isEmpty() ? "None." : projectsContainer.getJson());
		json.put("Project team", team.getJson());
		if(manager != null){
			json.put("Manager", manager.getJson());
		}
		json.put("Local", local.getJson());
		json.put("Cost (Euros)", df.format(getCost()).replaceAll(",", "."));
		json.put("Project ID", pid);
		return json;
	}

	/**
	 * Override of the method {@code getCosts()} from the {@code ICost}
	 * Interface.
	 */
	@Override
	public double getCost(){

		double managerCost;
		try{
			managerCost = manager.getCost();
		} catch (NullPointerException e){
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
	public int compareTo(UtilsElement element){

		if (element == null){
			throw new IllegalArgumentException();
		}
		if (!(element instanceof Project)){
			throw new ClassCastException();
		}
		if (this.getName().equalsIgnoreCase(element.getName())){
			return this.getName().compareTo(element.getName());
		}
		return this.getName().toLowerCase()
				.compareTo(element.getName().toLowerCase());
	}

	/**
	 * Override of the method {@code hashCode()} from {@code Object}.
	 */
	@Override
	public int hashCode(){
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
	public boolean equals(Object project){
		if (this == project){
			return true;
		}
		if (project == null){
			return false;
		}
		if (getClass() != project.getClass()){
			return false;
		}
		if (((Project) project).compareTo(this) != 0){
			return false;
		}
		return hasSameProperties((Project)project);
	}
	
	/**
	 * Verifies if the {@code Project} passed as parameter has the same properties as {@code this}.
	 * @param worker
	 * @return true if the {@code Project} passed as parameter has the same properties as {@code this}
	 * @return false if the {@code Project} passed as parameter has not the same properties as 
	 * {@code this}
	 */
	public boolean hasSameProperties(Project project){
		if( pid != project.getPID()){
			return false;
		}
		if( ! local.equals(project.getLocal())){
			return false;
		}
		if(!hasSameManager(project)){
			return false;
		}
		if(!hasSameTeam(project)){
			return false;
		}
		if(!hasSameSubprojects(project)){
			return false;
		}
		return true;
	}

	public boolean hasSameManager(Project project){
		
		if( manager == null && project.getManager() != null){
			return false;
		}
		if( manager != null && ! manager.equals(project.getManager())){
			return false;
		}
		return true;
	}
	
	/**
	 * Verifies if the {@code Project} passed as parameter has the same team as {@code this}.
	 * @param worker
	 * @return true if the {@code Project} passed as parameter has the same team as {@code this}
	 * @return false if the {@code Project} passed as parameter has not the same team as 
	 * {@code this}
	 */
	public boolean hasSameTeam(Project project){
		if( getTeam().size() != project.getTeam().size()){
			return false;
		}
		
		Iterator<AWorker> otherTeam = project.getTeam().iterator();
		for(AWorker worker : getTeam()){
			if (! worker.equals(otherTeam.next())){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Verifies if the {@code Project} passed as parameter has the same subprojects as {@code this}.
	 * @param worker
	 * @return true if the {@code Project} passed as parameter has the same subprojects as {@code this}
	 * @return false if the {@code Project} passed as parameter has not the same subprojects as 
	 * {@code this}
	 */
	public boolean hasSameSubprojects(Project project){
		if( getContainerProject().size() != project.getContainerProject().size()){
			return false;
		}
		Iterator<Project> subprojects = project.getContainerProject().iterator();
		for(Project proj : getContainerProject()){
			if (! proj.equals(subprojects.next())){
				return false;
			}
		}
		return true;
	}

	/**
	 * @return The Manager of the {@code Project}.
	 */
	public Leader getManager(){
		return manager;
	}

	/**
	 * @param manager
	 *            The manager to set in the {@code Project}.
	 */
	public void setManager(Leader manager){
		this.manager = manager;
	}

	/**
	 * @return The Project Identification (PID).
	 */
	@Override
	public long getPID(){
		return pid;
	}

	/**
	 * Override of the method {@code updateLongitude(double newLongitude)} from
	 * {@code ProjectInterface}
	 */
	@Override
	public boolean updateLongitude(double newLongitude){
		return local.setLongitude(newLongitude);
	}

	/**
	 * Override of the method {@code updateLatitude(double newLatitude)} from
	 * {@code ProjectInterface}
	 */
	@Override
	public boolean updateLatitude(double newLatitude){
		return local.setLatitude(newLatitude);
	}

	/**
	 * Override of the method {@code updateLocalName(String newName)} from
	 * {@code ProjectInterface}
	 */
	@Override
	public void updateLocalName(String newName){
		local.setName(newName);
	}

	/**
	 * Override of the method {@code updateLocalPrice(double newPrice)} from
	 * {@code ProjectInterface}
	 */
	@Override
	public boolean updateLocalPrice(double newPrice){
		return local.setPrice(newPrice);
	}

	/**
	 * Returns how many subprojects {@code this} project has
	 * @return the number of subprojects
	 */
	public int getSubprojectsNumber(){
		return projectsContainer.size();
	}
}
