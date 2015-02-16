package utils;

import java.text.DecimalFormat;

import org.json.JSONObject;

/**
 * Class {@code Local} whose objects will represent the local of a project. All
 * {@code Local} instances are thread-safe. Implements the Interfaces
 * {@link ICost}, {@link IName}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class Local implements ICost, IName {

	/**
	 * All fields are Volatile.
	 * 
	 * @field name - String with the name of the local.
	 * @field latitude/longitude - coordinates of the local.
	 * @field cost - the cost associated with the local.
	 */
	private volatile String name;
	private volatile double cost;
	private volatile double latitude;
	private volatile double longitude;

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
	public Local(double latitude, double longitude, String name, double cost) {

		if (name == null || !checkLatitude(latitude)
				|| !checkLongitude(longitude) || !checkPrice(cost)) {
			throw new IllegalArgumentException(
					"Latitude must be between -90 and 90, Longitude must be between -180 and 180 and Price must be above 0.");
		}
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
	public String getName() {
		return name;
	}

	/**
	 * Override of the method {@code getCosts()} from the {@code ICost}
	 * Interface.
	 */
	@Override
	public double getCost() {
		return cost;
	}

	/**
	 * @return The latitude of the {@code Local}
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @return The longitude of the {@code Local}
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Override of the method {@code toString()} from {@code Object}.
	 */
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.##");
		return name + ", " + "(" + latitude + ", " + longitude + ")"
				+ ", cost: " + df.format(getCost()) + " Euros";
	}

	public JSONObject getJson() {
		DecimalFormat df = new DecimalFormat("#.##");
		JSONObject json = new JSONObject();
		json.put("Cost (Euros)", df.format(getCost()).replaceAll(",", "."));
		json.put("Longitude", longitude);
		json.put("Latitude", latitude);
		json.put("Name", name.replaceAll("%20", " "));
		return json;
	}

	/**
	 * verifies if the value of the {@code latitude} is between the correct
	 * interval
	 * 
	 * @param latitude
	 *            - value of the {@code latitude} to be verified
	 * @return {@code true} if it is inside the interval
	 * @return {@code false} if it is not inside the interval
	 */
	private boolean checkLatitude(double latitude) {
		return latitude >= -90 && latitude <= 90;
	}

	/**
	 * verifies if the value of the {@code longitude} is between the correct
	 * interval
	 * 
	 * @param latitudeString
	 *            - value of the {@code longitude} to be verified
	 * @return {@code true} if it is inside the interval
	 * @return {@code false} if it is not inside the interval
	 */
	private boolean checkLongitude(double longitude) {
		return longitude >= -180 && longitude <= 180;
	}

	/**
	 * verifies if the value of the {@code price} non negative
	 * 
	 * @param latitudeString
	 *            - value of the {@code price} to be verified
	 * @return {@code true} if it is non negative
	 * @return {@code false} if it is negative
	 */
	private boolean checkPrice(double price) {
		return price >= 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Override of the method {@code equals()} from {@code Object} to be
	 * consistent with the {@code compareTo()} method.
	 */
	@Override
	public boolean equals(Object local) {
		if (this == local) {
			return true;
		}

		if (local == null) {
			return false;
		}

		if (getClass() != local.getClass()) {
			return false;
		}

		return hasSameParameters((Local) local);
	}

	/**
	 * Verifies if the {@code Local} passed as parameter has the same properties
	 * as {@code this}.
	 * 
	 * @param worker
	 * @return true if the {@code Local} passed as parameter has the same
	 *         properties as {@code this}
	 * @return false if the {@code Local} passed as parameter has not the same
	 *         properties as {@code this}
	 */
	public boolean hasSameParameters(Local local) {
		if (longitude != local.getLongitude()) {
			return false;
		}

		if (latitude != local.getLatitude()) {
			return false;
		}

		if (cost != local.getCost()) {
			return false;
		}

		if (!name.equals(local.getName())) {
			return false;
		}

		return true;
	}

}