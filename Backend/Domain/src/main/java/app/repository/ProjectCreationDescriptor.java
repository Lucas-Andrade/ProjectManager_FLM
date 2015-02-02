package app.repository;


import utils.Project;

/**
 * Using Jim Coplien's Curiously Recurring Template Pattern. 
 *
 * @param <T>
 */
public abstract class ProjectCreationDescriptor<T extends ProjectCreationDescriptor<?>> {

	/**
	 * Holds the latitude of the project's local
	 */
	private double latitude;

	/**
	 * Holds the longitude of the project's local
	 */
	private double longitude;

	/**
	 * Holds the name of the project's local
	 */
	private String name;

	/**
	 * Holds the cost of the project's local
	 */
	private double cost;

	
	protected ProjectCreationDescriptor() { }

	protected ProjectCreationDescriptor(double lat, double lon, String name, double cost){
		this.latitude = lat;
		this.longitude = lon;
		this.name = name;
		this.cost = cost;
	}
	
	@SuppressWarnings("unchecked")
	public T latitude(double lat)
	{
		this.latitude = lat;
		return (T) this;
	}
	
	@SuppressWarnings("unchecked")
	public T longitude(double lon)
	{
		this.longitude = lon;
		return (T) this;
	}
	
	@SuppressWarnings("unchecked")
	public T name( String localName)
	{
		this.name = localName;
		return (T) this;
	}
	
	
	@SuppressWarnings("unchecked")
	public T cost(double cost)
	{
		this.cost = cost;
		return (T) this;
	}
	
	public abstract Project build(long nEXT_PID_TO_BE_USED); 


}
