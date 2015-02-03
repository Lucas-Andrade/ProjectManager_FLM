package app.repository;

import utils.Leader;

public class leaderCreationDescriptor extends WorkerCreationDescriptor<Leader> {

	/**
	 * 
	 */
	public leaderCreationDescriptor() {
		super();
	}

	/**
	 * @param name
	 * @param costPerHour
	 * @param hoursWorked
	 * @param bonus
	 */
	public leaderCreationDescriptor(String name, double costPerHour,
			double hoursWorked, double bonus) {
		super(name, costPerHour, hoursWorked, bonus);
	}

	@Override
	public Leader build(Long newWorkerCID) {
		return new Leader(super.name, super.costPerHour, super.hoursWorked,
				super.bonus, newWorkerCID);
	}

}
