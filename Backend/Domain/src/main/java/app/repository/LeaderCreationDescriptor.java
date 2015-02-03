package app.repository;

import utils.Leader;

public class LeaderCreationDescriptor extends WorkerCreationDescriptor<Leader> {

	/**
	 * An empty constructor for this class.
	 */
	public LeaderCreationDescriptor() {
		super();
	}

	/**
	 * A constructor for this class with the fields for creating a
	 * {@code Leader}.
	 */
	public LeaderCreationDescriptor(String name, double costPerHour,
			double hoursWorked, double bonus) {
		super(name, costPerHour, hoursWorked, bonus);
	}

	/**
	 * Method that instantiates a {@code Leader}.
	 * 
	 * @see super{@link #build(Long)}
	 */
	@Override
	public Leader build(Long newWorkerCID) {
		return new Leader(super.name, super.costPerHour, super.hoursWorked,
				super.bonus, newWorkerCID);
	}

}
