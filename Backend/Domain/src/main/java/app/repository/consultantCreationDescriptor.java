package app.repository;

import utils.Consultant;

public class consultantCreationDescriptor extends
		WorkerCreationDescriptor<Consultant> {

	/**
	 * 
	 */
	public consultantCreationDescriptor() {
		super();
	}

	/**
	 * @param name
	 * @param costPerHour
	 * @param hoursWorked
	 * @param bonus
	 */
	public consultantCreationDescriptor(String name, double costPerHour,
			double hoursWorked, double bonus) {
		super(name, costPerHour, hoursWorked, bonus);
	}

	@Override
	public Consultant build(Long newWorkerCID) {
		return new Consultant(super.name, super.costPerHour, super.hoursWorked,
				newWorkerCID);
	}

}
