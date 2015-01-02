package utils;

import java.text.DecimalFormat;

import org.json.JSONObject;

import app.elements.WorkerInterface;

/**
 * Abstract class {@code AWorker} that will define the general fields shared by
 * all types of workers.
 * 
 * Extends {@link Element}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public abstract class AWorker extends Element implements WorkerInterface
{

	/**
	 * @field name - String with the name of the worker.
	 * @field costPerHour - price receive by the worker for one hour of work.
	 * @field hoursWorked - total of hours worked by the worker.
	 * @field cid - the ID of the worker.
	 */
	private String name;
	private double costPerHour;
	private final double hoursWorked;
	private final long cid;

	/**
	 * AWorker constructor that will receive the worker's name, cost per hour
	 * and the total amount of hours worked as parameters.
	 * 
	 * Throws {@link IllegalArgumentException} if the {@code name} is null or
	 * the {@code costPerHour} and {@code hoursWorked} are less than 0.
	 * 
	 * @param name
	 *            - name of the worker.
	 * @param costPerHour
	 *            - price per hour earned by the worker.
	 * @param hoursWorked
	 *            - total amount of hours worked by the worker.
	 */
	public AWorker(String name, double costPerHour, double hoursWorked, long cid)
	{

		if (name == null || costPerHour < 0 || hoursWorked < 0)
			throw new IllegalArgumentException();

		this.costPerHour = costPerHour;
		this.hoursWorked = hoursWorked;
		this.name = name;
		this.cid = cid;
	}

	/**
	 * @return {@code costPerHour}.
	 */
	public double getCostPerHour()
	{

		return costPerHour;
	}

	/**
	 * @return {@code hoursWorked}.
	 */
	public double getWorkerHours()
	{

		return hoursWorked;
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
		return costPerHour * hoursWorked;
	}

	/**
	 * @return The worker identification number.
	 */
	@Override
	public long getCID()
	{
		return cid;
	}

	/**
	 * Patch the name of the worker.
	 * 
	 * @param name
	 *            The name of the worker.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Patch the worker cost per hour.
	 * 
	 * @param costPerHour
	 *            The cost of the worker for one hour of work.
	 */
	public void setCostPerHour(double costPerHour)
	{
		this.costPerHour = costPerHour;
	}

	/**
	 * Override of the method {@code toString()} from {@code Object}.
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		DecimalFormat df = new DecimalFormat("#.##");

		builder.append("Name: ").append(name).append(", Payment per hour: ")
				.append(df.format(costPerHour)).append(" Euros, Cost: ")
				.append(df.format(getCost())).append(" Euros");

		return builder.toString();
	}

	@Override
	public JSONObject getJson() {
		DecimalFormat df = new DecimalFormat("#.##");

		//instancia um novo JSONObject 
		JSONObject worker = new JSONObject(); 
		
		//preenche o objeto com os campos: name, payment per hour e cost
		worker.put("Cost (Euros)", df.format(getCost()).replaceAll(",", ".")); 
		worker.put("Payment per hour (Euros)", df.format(costPerHour).replaceAll(",", ".")); 
		worker.put("Consultant ID", cid);
		worker.put("Name", name); 
		
		return worker;
	}
	
	
	/**
	 * Override of the method {@code compareTo()} from the {@link Comparable}
	 * Interface.
	 * 
	 * It will allow the workers to be ordered in a {@link Team} by ascending
	 * order of their {@code costPerHour}.
	 * 
	 * Two workers are considered to be equal when their {@code name} and
	 * {@code costPerHour} are the same. It is consistent with the method
	 * {@code equals} from {@code Object}.
	 */
	@Override
	public int compareTo(Element element)
	{
		if (element == null)
			throw new IllegalArgumentException();

		if (!(element instanceof AWorker))
			throw new ClassCastException();

		AWorker worker = (AWorker) element;

		if (this.costPerHour > worker.getCostPerHour())
			return 1;

		return this.costPerHour < worker.getCostPerHour() ? -1 : this.getName()
				.compareTo(worker.getName());
	}

	/**
	 * Override of the method {@code hashCode()} from {@code Object}.
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		long temp;

		temp = Double.doubleToLongBits(costPerHour);

		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());

		return result;
	}

	/**
	 * Override of the method {@code equals()} from {@code Object} to be
	 * consistent with the {@code compareTo()} method.
	 */
	@Override
	public boolean equals(Object worker)
	{
		if (this == worker)
			return true;

		if (worker == null)
			return false;

		if (getClass() != worker.getClass())
			return false;

		if (((AWorker) worker).compareTo(this) != 0)
			return false;

		return true;
	}

}