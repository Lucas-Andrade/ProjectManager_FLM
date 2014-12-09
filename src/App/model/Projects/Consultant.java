package App.model.Projects;

/**
 * Class {@code Consultant} whose objects will represent the consultants that will constitute a
 * {@code Team}
 * 
 * Extends {@link AWorker}.
 */
public class Consultant extends AWorker {

	/**
	 * Consultant constructor that will receive as parameters the worker's name, cost per hour and
	 * the total amount of hours worked.
	 * 
	 * Explicitly calls the constructor of its super class to iniciate its fields.
	 * 
	 * Thows {@link IllegalArgumentException} if the {@code name} is null or the {@code costPerHour}
	 * and {@code hoursWorked} are less than 0.
	 * 
	 * @param name
	 *            - name of the worker.
	 * @param costPerHour
	 *            - price per hour earned by the worker.
	 * @param hoursWorked
	 *            - total amount of hours worked by the worker.
	 */
	public Consultant(String name, double costPerHour, double hoursWorked) {

		super(name, costPerHour, hoursWorked);
	}
}
