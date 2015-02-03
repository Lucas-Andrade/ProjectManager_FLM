package commandProxy;

import utils.AWorker;
import app.AppElement;
import app.domainCommands.exceptions.CostOutOfBoundsException;
import app.domainCommands.exceptions.NoSuchWorkerException;
import app.repository.WorkerRepository;

/**
 * This {@code Command} allows to modify a {@code Consultant}'s properties.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015 
 */
public class SetConsultantPropertiesFromRepo implements Command{

	
	/**
	 * The {@code CID} of the {@code Consultant} whose properties are to be modified.
	 */
	String cidString; 
	
	/**
	 * The new {@code name} of the {@code Consultant}.
	 */
	String name;
	
	/**
	 * The new {@code priceHour} of the {@code Consultant}.
	 */
	String priceHourString;
	
	/**
	 * Constructs the {@code Command}. It is allowed to pass as parameter new
	 * values for all the modifiable properties of the {@code Consultant}. But if
	 * some properties are not to be changed it is allowed not to introduce any
	 * new values for them: if {@code null} or an empty {@code String} is passed
	 * in one of those parameters, the corresponding property will not be
	 * altered.
	 * 
	 * Non mandatory parameters are: {@code name} and {@code priceHour}.
	 * 
	 * Mandatory parameters are: {@code pRepo} and {@code pidString}. If one of these
	 * is null, a {@code IllegalArgumentException} will be thrown.
	 * 
	 * @param wRepo
	 * The {@code WorkerRepository} where the {@code Consultant} is stored.
	 * @param cidString
	 * The {@code CID} of the {@code Consultant} whose properties are to be modified.
	 * @param name
	 * The new {@code name} of the {@code Consultant}.
	 * @param priceHour
	 * The new {@code priceHour} of the {@code Consultant}.
	 */
	public SetConsultantPropertiesFromRepo(String cidString, String name, String priceHour){
		if (cidString == null){
			throw new IllegalArgumentException();
		}
		this.cidString = cidString;
		this.name = name;
		this.priceHourString = priceHour;
		
		setEmptyStringsToNull();
	}
	
	/**
	 * @return An array of {@code AppElement}s containing the modified {@code Consultant}.
	 * @see Command#call()
	 */
	@Override
	public AppElement[] call() throws NoSuchWorkerException, CostOutOfBoundsException {
		
		long cid = Long.parseLong(cidString);	
		AWorker worker = wRepo.getAWorkerByID(cid);
		
		if (worker == null){
			throw new NoSuchWorkerException("There is no worker with that ID.");
		}
		
		if (name != null){
			worker.setName(name);
		}
		
		if (priceHourString != null){
			double priceHour = Double.parseDouble(priceHourString);
			if(!worker.setCostPerHour(priceHour)){
				throw new CostOutOfBoundsException("The cost cannot be negative.");
			}
		}
		
		return new AppElement[]{worker};
	}

	/**
	 * Checks if any of the construcor's non mandatory parameters are empty
	 * {@code String}s. If any of them is an empty {@code String}, it is set to
	 * {@code null}.
	 */
	private void setEmptyStringsToNull() {
		if (name != null && "".equals(name)) {
			name = null;
		}
		if (priceHourString != null && "".equals(priceHourString)) {
			priceHourString = null;
		}
	}
}
