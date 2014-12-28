package utils;

import java.text.DecimalFormat;
import org.json.JSONObject;

/**
 * Class {@code Local} whose objects will represent the local of a project.
 * 
 * Implements the Interfaces {@link ICost}, {@link IName}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class Local implements ICost, IName
{

	/**
	 * @field name - String with the name of the local.
	 * @field localization - String with the address of the local.
	 * @field cost - the cost associated with the local.
	 */
	private String name;
	private double cost;
	private double latitude;
	private double longitude;

	/**
	 * Local constructor that will receive the local's name, address and cost as
	 * parameters.
	 * 
	 * Throws {@link IllegalArgumentException} if the name or the address are
	 * null or the cost is less than 0.
	 * 
	 * @param name
	 *            - the name of the local.
	 * @param address
	 *            - the address of the local.
	 * @param cost
	 *            - the cost associated with the local.
	 */
	public Local(double latitude, double longitude, String name, double cost)
	{

		if (name == null || !checkLatitude(latitude) || !checkLongitude(longitude) || 
				!checkPrice(cost))
			throw new IllegalArgumentException();

		this.name = name;
		this.cost = cost;
		this.latitude = latitude;
		this.longitude = longitude;
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
	 * Override of the method {@code getCosts()} from the {@code ICost}
	 * Interface.
	 */
	@Override
	public double getCost()
	{

		return cost;
	}

	/**
	 * Override of the method {@code toString()} from {@code Object}.
	 */
	@Override
	public String toString()
	{
		DecimalFormat df = new DecimalFormat("#.##");
		return name + ", " + "(" + latitude + ", " + longitude + ")"
				+ ", cost: " + df.format(getCost()) + " Euros";
	}

	
	public JSONObject getJson() 
	{
		DecimalFormat df = new DecimalFormat("#.##");
		JSONObject json = new JSONObject();
		json.put("Cost (Euros)", df.format(getCost()));
		json.put("Longitude", longitude);
		json.put("Latitude", latitude);
		json.put("Name", name);
		return json;
	}
	
	
	/**
	 * sets the value of the {@code longitude} to the new value passed as parameter, provided 
	 * it is between the correct bounds
	 * 
	 * @param newLongitude - new {@code longitude} value
	 * 
	 * @return {@code true} if the {@code longitude} was set to the new value
	 * @return {@code false} if the {@code longitude} was not in the correct bounds
	 */
	public boolean setLongitude(double newLongitude) {
		if(checkLongitude(newLongitude))
		{
			this.latitude = newLongitude;
			return true;
		}
		return false;
	}
	
	/**
	 * sets the value of the {@code latitude} to the new value passed as parameter, provided 
	 * it is between the correct bounds
	 * 
	 * @param newLatitude - new {@code latitude} value
	 * 
	 * @return {@code true} if the {@code latitude} was set to the new value
	 * @return {@code false} if the {@code latitude} was not in the correct bounds
	 */
	public boolean setLatitude(double newLatitude) {

		if(checkLatitude(newLatitude))
		{
			this.latitude = newLatitude;
			return true;
		}
		return false;
	}

	/**
	 * sets the {@code name} of the {@code Local} to the new {@code String}, passed as parameter
	 * @param newName - the new name of the {@code Local}
	 */
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	 * sets the value of the {@code price} to the new value passed as parameter, provided 
	 * it is between the correct bounds
	 * 
	 * @param newPrice - new {@code price} value
	 * 
	 * @return {@code true} if the {@code price} was set to the new value
	 * @return {@code false} if the {@code price} was not in the correct bounds
	 */
	public boolean setPrice(double newPrice) {
		if(checkPrice(newPrice))
		{
			this.cost = newPrice;
			return true;
		}
		return false;
	}
	
	/**
	 * verifies if the value of the {@code latitude} is between the correct interval
	 * @param latitude - value of the {@code latitude} to be verified
	 * @return {@code true} if it is inside the interval
	 * @return {@code false} if it is not inside the interval
	 */
	private boolean checkLatitude(double latitude)
	{
		return latitude >= -90 || latitude <= 90;
	}
	
	/**
	 * verifies if the value of the {@code longitude} is between the correct interval
	 * @param latitude - value of the {@code longitude} to be verified
	 * @return {@code true} if it is inside the interval
	 * @return {@code false} if it is not inside the interval
	 */
	private boolean checkLongitude(double longitude)
	{
		return longitude >= -180 || longitude <= 180;
	}
	
	/**
	 * verifies if the value of the {@code price} non negative
	 * @param latitude - value of the {@code price} to be verified
	 * @return {@code true} if it is non negative
	 * @return {@code false} if it is negative
	 */
	private boolean checkPrice(double price)
	{
		return price >= 0;
	}


}