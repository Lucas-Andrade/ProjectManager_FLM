package app.repository;

import utils.AWorker;

/**
 * Class with the task of creating a new {@code AWorker} with the supplied
 * parameters.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 03/02/2015
 */
public class WorkerCreationDescriptor {

	/**
	 * @field name - String with the name of the worker.
	 * @field costPerHour - price receive by the worker for one hour of work.
	 * @field hoursWorked - total of hours worked by the worker.
	 * @field cid - the ID of the worker.
	 */
	private String name;
	private double costPerHour;
	private double hoursWorked;

	/**
	 * @field bonus - bonus received by the worker for being a leader or a
	 *        manager.
	 */
	private double bonus;

	public WorkerCreationDescriptor() { }
	

	public WorkerCreationDescriptor(String name, double costPerHour, double hoursWorked, double bonus) {
		this.name=name;
		this.costPerHour=costPerHour;
		this.hoursWorked=hoursWorked;
		this.bonus=bonus;
	}
	
	
	public WorkerCreationDescriptor latitude(double lat) {
		this.latitude = lat;
		return this;
	}
	
	public AWorker build(Long newWorkerCID) {
		// TODO Auto-generated method stub
		return null;
	}

}
