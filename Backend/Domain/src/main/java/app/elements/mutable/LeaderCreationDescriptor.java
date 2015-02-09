package app.elements.mutable;

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
	 * Method to update the {@code super#bonus}.
	 * 
	 * @param bonus
	 *            Bonus received by the worker for being a leader or a manager.
	 * @return {@code this}.
	 */
	public WorkerCreationDescriptor<Leader> bonus(long bonus) {
		this.bonus = bonus;
		return this;
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
