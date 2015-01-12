package utils;

import org.json.JSONObject;

/**
 * Class {@code Leader} whose objects will represent the managers and team
 * leader of a project.
 * 
 * Extends {@link Consultant}.
 */
public class Leader extends Consultant
{

	/**
	 * @field bonus - bonus received by the worker for being a leader or a
	 *        manager.
	 */
	private final double bonus;

	/**
	 * Leader constructor that will receive as parameters the worker's name,
	 * cost per hour, the total amount of hours worked and its bonus.
	 * 
	 * Explicitly calls the constructor of its super class to initiate the
	 * fields shared with the other workers.
	 * 
	 * Throws {@link IllegalArgumentException} if the {@code name} is null or
	 * the {@code costPerHour} , the {@code hoursWorked} or the bonus are less
	 * than 0.
	 * 
	 * @param name
	 *            - name of the worker.
	 * @param costPerHour
	 *            - price per hour earned by the worker.
	 * @param hoursWorked
	 *            - total amount of hours worked by the worker.
	 * @param bonus
	 *            - bonus received by the worker for being a leader or a
	 *            manager.
	 */
	public Leader(String name, double costPerHour, double hoursWorked,
			double bonus, long cid)
	{

		super(name, costPerHour, hoursWorked, cid);

		if (bonus < 0)
			throw new IllegalArgumentException();

		this.bonus = bonus;
	}

	/**
	 * Override of the method {@code getCosts()} from the {@code ICost}
	 * Interface.
	 */
	@Override
	public double getCost()
	{

		return this.getCostPerHour() * this.getWorkerHours() + bonus;
	}

	/**
	 * @return {@code bonus}.
	 */
	public double getBonus()
	{
		return bonus;
	}
	
	@Override
	public JSONObject getJson() 
	{
		JSONObject worker = super.getJson();
		worker.put("Bonus", bonus);
		return worker;
	}
	
	/**
	 * Override of the method {@code equals()} from {@code Object} to be
	 * consistent with the {@code compareTo()} method.
	 */
	@Override
	public boolean equals(Object worker)
	{
		if(! (worker instanceof Leader))
			return false;
		
		if(bonus != ((Leader) worker).getBonus())
			return false;
		
		return super.equals(worker);
	}
}