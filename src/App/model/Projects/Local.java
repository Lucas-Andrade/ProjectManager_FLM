package App.model.Projects;
/**
 * Class {@code Local} whose objects will represent the local of a project.
 * 
 * Implements the Interfaces {@link ICost}, {@link IName}.
 */
public class Local implements ICost, IName {

	/**
	 * @field name - String with the name of the local.
	 * @field localization - String with the adress of the local.
	 * @field cost - the cost associated with the local.
	 */
	private final String name;
	private final String address;
	private final double cost;

	/**
	 * Local contructor that wiil receive the local's name, address and cost as parameters.
	 * 
	 * Thows {@link IllegalArgumentException} if the name or the address are null or the cost is
	 * less than 0.
	 * 
	 * @param name
	 *            - the name of the local.
	 * @param address
	 *            - the address of the local.
	 * @param cost
	 *            - the cost associated with the local.
	 */
	public Local(String name, String address, double cost) {

		if (name == null || address == null || cost < 0)
			throw new IllegalArgumentException();

		this.name = name;
		this.address = address;
		this.cost = cost;
	}

	/**
	 * Override of the method {@code getName()} from the {@code IName} Interface.
	 */
	@Override
	public String getName() {

		return name;
	}

	/**
	 * @return {@code address}.
	 */
	public String getAddress() {

		return address;
	}

	/**
	 * Override of the method {@code getCosts()} from the {@code ICost} Interface.
	 */
	@Override
	public double getCost() {

		return cost;
	}

	/**
	 * Override of the method {@code toString()} from {@code Object}.
	 */
	@Override
	public String toString() {

		return getName() + ", " + getAddress() + ", cost: " + getCost() + "�";
	}
}