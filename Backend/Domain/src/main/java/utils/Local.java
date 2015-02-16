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
	 * The lock to be used in all {@code this} methods.
	 */
	private final Object lockLocal = new Object();

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
					"Latitude must be between -90 and 90, Longitude\nbetween -180 and 180 and Price must be above 0.");
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
		synchronized (lockLocal) {
			return name;
		}
	}

	/**
	 * Override of the method {@code getCosts()} from the {@code ICost}
	 * Interface.
	 */
	@Override
	public double getCost() {
		synchronized (lockLocal) {
			return cost;
		}
	}

	/**
	 * @return The latitude of the {@code Local}
	 */
	public double getLatitude() {
		synchronized (lockLocal) {
			return latitude;
		}
	}

	/**
	 * @return The longitude of the {@code Local}
	 */
	public double getLongitude() {
		synchronized (lockLocal) {
			return longitude;
		}
	}

	/**
	 * Override of the method {@code toString()} from {@code Object}.
	 */
	@Override
	public String toString() {
		synchronized (lockLocal) {
			DecimalFormat df = new DecimalFormat("#.##");
			return name + ", " + "(" + latitude + ", " + longitude + ")"
					+ ", cost: " + df.format(getCost()) + " Euros";
		}
	}

	public JSONObject getJson() {
		synchronized (lockLocal) {
			DecimalFormat df = new DecimalFormat("#.##");
			JSONObject json = new JSONObject();
			json.put("Cost (Euros)", df.format(getCost()).replaceAll(",", "."));
			json.put("Longitude", longitude);
			json.put("Latitude", latitude);
			json.put("Name", name.replaceAll("%20", " "));
			return json;
		}
	}

	/**
	 * sets the value of the {@code longitude} to the new value passed as
	 * parameter, provided it is between the correct bounds
	 * 
	 * @param newLongitude
	 *            - new {@code longitude} value
	 * 
	 * @return {@code true} if the {@code longitude} was set to the new value
	 * @return {@code false} if the {@code longitude} was not in the correct
	 *         bounds
	 */
	public boolean setLongitude(double newLongitude) {
		synchronized (lockLocal) {
			if (checkLongitude(newLongitude)) {
				longitude = newLongitude;
				return true;
			}
			return false;
		}
	}

	/**
	 * sets the value of the {@code latitude} to the new value passed as
	 * parameter, provided it is between the correct bounds
	 * 
	 * @param newLatitude
	 *            - new {@code latitude} value
	 * 
	 * @return {@code true} if the {@code latitude} was set to the new value
	 * @return {@code false} if the {@code latitude} was not in the correct
	 *         bounds
	 */
	public boolean setLatitude(double newLatitude) {
		synchronized (lockLocal) {
			if (checkLatitude(newLatitude)) {
				latitude = newLatitude;
				return true;
			}
			return false;
		}
	}

	/**
	 * sets the {@code name} of the {@code Local} to the new {@code String},
	 * passed as parameter
	 * 
	 * @param newName
	 *            - the new name of the {@code Local}
	 */
	public void setName(String newName) {
		synchronized (lockLocal) {
			this.name = newName;
		}
	}

	/**
	 * sets the value of the {@code price} to the new value passed as parameter,
	 * provided it is between the correct bounds
	 * 
	 * @param newPrice
	 *            - new {@code price} value
	 * 
	 * @return {@code true} if the {@code price} was set to the new value
	 * @return {@code false} if the {@code price} was not in the correct bounds
	 */
	public boolean setPrice(double newPrice) {
		synchronized (lockLocal) {
			if (checkPrice(newPrice)) {
				this.cost = newPrice;
				return true;
			}
			return false;
		}
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
		synchronized (lockLocal) {
			return latitude >= -90 && latitude <= 90;
		}
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
		synchronized (lockLocal) {
			return longitude >= -180 && longitude <= 180;
		}
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
		synchronized (lockLocal) {
			return price >= 0;
		}
	}

	@Override
	public int hashCode() {
		synchronized (lockLocal) {
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
	}

	/**
	 * Override of the method {@code equals()} from {@code Object} to be
	 * consistent with the {@code compareTo()} method.
	 */
	@Override
	public boolean equals(Object local) {
		synchronized (lockLocal) {
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
		synchronized (lockLocal) {
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

}