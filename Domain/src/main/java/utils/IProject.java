package utils;

import app.AppElement;

/**
 * Class that defines the contract for the {@link Project}s. Extends
 * {@link AppElement}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public interface IProject extends UtilsElement{

	/**
	 * Gives the Container with all subprojects of the project.
	 */
	public Iterable<Project> getContainerProject();

	/**
	 * Returns the identification PID of the project.
	 */
	public long getPID();

	public void setManager(Leader manager);

	/**
	 * Method that will allow a subproject to be added to the {@code Project}.
	 * 
	 * @param project
	 *            The subproject to the added to the {@code Project}.
	 * 
	 * @return true if the subproject is successfully added and false otherwise.
	 */
	public boolean addProject(Project project);

	/**
	 * Method that will allow a worker to be added to the {@code Project}.
	 * 
	 * @param worker
	 *            - The {@code AWorker} to be add to the {@code team}.
	 * 
	 * @return true if the worker is successfully added and false otherwise.
	 */
	public boolean addWorker(AWorker worker);

	/**
	 * @see Object#toString()
	 */
	public String toString();
	
	/**
	 * updates the value of the {@code longitude} of the {@code Local} of the {@code project} to
	 * the new value passed as parameter.
	 * 
	 * @param newLongitude - new longitude value
	 */
	public boolean updateLongitude(double newLongitude);

	/**
	 * updates the value of the {@code latitude} of the {@code Local} of the {@code project} to
	 * the new value passed as parameter.
	 * 
	 * @param newLatitude - new latitude value
	 */
	public boolean updateLatitude(double newLatitude);

	/**
	 * updates the value of the {@code name} of the {@code Local} of the {@code project} to
	 * the new value passed as parameter.
	 * 
	 * @param newName - the new name
	 */
	public void updateLocalName(String newName);

	/**
	 * updates the value of the {@code cost} of the {@code Local} of the {@code project} to
	 * the new value passed as parameter.
	 * 
	 * @param newPrice - new value of the price
	 */
	public boolean updateLocalPrice(double newPrice);
}
