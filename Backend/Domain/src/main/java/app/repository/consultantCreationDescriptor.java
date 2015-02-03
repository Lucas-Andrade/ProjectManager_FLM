package app.repository;

import utils.Consultant;

public class ConsultantCreationDescriptor extends
		WorkerCreationDescriptor<Consultant> {

	/**
	 * An empty constructor for this class.
	 */
	public ConsultantCreationDescriptor() {
		super();
	}

	/**
	 * A constructor for this class with the fields for creating a
	 * {@code Consultant}.
	 */
	public ConsultantCreationDescriptor(String name, double costPerHour, double hoursWorked, double bonus) {
		super(name, costPerHour, hoursWorked, bonus);
	}

	/**
	 * Method that instantiates a {@code Consultant}.
	 * 
	 * @see super{@link #build(Long)}
	 */
	@Override
	public Consultant build(Long newWorkerCID) {
		return new Consultant(super.name, super.costPerHour, super.hoursWorked,
				newWorkerCID);
	}

}
