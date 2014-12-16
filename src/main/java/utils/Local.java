package main.java.utils;

import java.text.DecimalFormat;

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
	private final String name;
	private final double cost;
	public final double latitude;
	public final double longitude;

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

		if (name == null || latitude < -90 || latitude > 90 || longitude < -180
				|| longitude > 180 || cost < 0)
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
		return getName() + ", " + "(" + latitude + ", " + longitude + ")"
				+ ", cost: " + df.format(getCost()) + " Euros";
	}
}