package app.repository;

import utils.Local;
import utils.Project;

/**
 * Class with the task of creating a new {@code Project} with the supplied
 * parameters.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 03/02/2015
 */
public class ProjectCreationDescriptor {

	/**
	 * Holds the latitude of the {@code Project}'s {@code Local}.
	 */
	private double latitude;

	/**
	 * Holds the longitude of the {@code Project}'s {@code Local}.
	 */
	private double longitude;

	/**
	 * Holds the name of the {@code Project}'s {@code Local}.
	 */
	private String name;

	/**
	 * Holds the cost of the {@code Project}'s {@code Local}.
	 */
	private double cost;

	/**
	 * An empty constructor for this class.
	 */
	protected ProjectCreationDescriptor() {
	}

	/**
	 * A constructor for this class with the fields for creating a
	 * {@code Project}.
	 */
	public ProjectCreationDescriptor(double lat, double lon, String name,
			double cost) {
		this.latitude = lat;
		this.longitude = lon;
		this.name = name;
		this.cost = cost;
	}

	

	/**
	 * Method to update the {@code this#latitude}.
	 * 
	 * @param lat
	 *            The latitude of the {@code Project}'s {@code Local}.
	 * @return {@code this}.
	 */
	public ProjectCreationDescriptor latitude(double lat) {
		this.latitude = lat;
		return this;
	}

	/**
	 * Method to update the {@code this#longitude}.
	 * 
	 * @param lon
	 *            The longitude of the {@code Project}'s {@code Local}.
	 * @return {@code this}.
	 */
	public ProjectCreationDescriptor longitude(double lon) {
		this.longitude = lon;
		return this;
	}

	/**
	 * Method to update the {@code this#name}.
	 * 
	 * @param name
	 *            The name of the {@code Project}'s {@code Local}.
	 * @return {@code this}.
	 */
	public ProjectCreationDescriptor name(String localName) {
		this.name = localName;
		return this;
	}

	/**
	 * Method to update the {@code this#cost}.
	 * 
	 * @param cost
	 *            The cost of the {@code Project}'s {@code Local}.
	 * @return {@code this}.
	 */
	public ProjectCreationDescriptor cost(double cost) {
		this.cost = cost;
		return this;
	}

	/**
	 * Method that instatiates a {@code Project} with the fields from
	 * {@code this} object.
	 * 
	 * @param pid
	 *            The PID of the {@code Project} to be created.
	 * @return The created {@code Project}.
	 */
	public Project build(long pid) {
		return new Project(new Local(latitude, longitude, name, cost), pid);
	}

}