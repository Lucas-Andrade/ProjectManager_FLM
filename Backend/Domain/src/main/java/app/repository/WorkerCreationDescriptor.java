package app.repository;

import utils.AWorker;

/**
 * Class with the task of creating a new {@code AWorker} with the supplied
 * parameters.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 03/02/2015
 * 
 * @param <T>
 *            The type of {@code AWorker}.
 */
public abstract class WorkerCreationDescriptor<T extends AWorker> {

	/**
	 * String with the name of the {@code AWorker}.
	 */
	protected String name;

	/**
	 * Price receive by the worker for one hour of work.
	 */
	protected double costPerHour;

	/**
	 * Total of hours worked by the worker.
	 */
	protected double hoursWorked;

	/**
	 * Bonus received by the worker for being a leader or a manager.
	 */
	protected double bonus;
	
	/**
	 * An empty constructor for this class.
	 */
	public WorkerCreationDescriptor() {
	}

	/**
	 * A constructor for this class with the fields for creating a
	 * {@code Project}.
	 */
	public WorkerCreationDescriptor(String name, double costPerHour,
			double hoursWorked, double bonus) {
		this.name = name;
		this.costPerHour = costPerHour;
		this.hoursWorked = hoursWorked;
		this.bonus = bonus;
	}

	public WorkerCreationDescriptor<T> name(String name) {
		this.name = name;
		return this;
	}

	public WorkerCreationDescriptor<T> costPerHour(long costPerHour) {
		this.costPerHour = costPerHour;
		return this;
	}

	public WorkerCreationDescriptor<T> hoursWorked(long hoursWorked) {
		this.hoursWorked = hoursWorked;
		return this;
	}

	public WorkerCreationDescriptor<T> bonus(long bonus) {
		this.bonus = bonus;
		return this;
	}

	public abstract T build(Long newWorkerCID);

}