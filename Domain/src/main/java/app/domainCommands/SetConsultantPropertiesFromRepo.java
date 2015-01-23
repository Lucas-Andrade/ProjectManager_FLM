package app.domainCommands;

import utils.AWorker;
import app.AppElement;
import app.domainCommands.exceptions.CostOutOfBoundsException;
import app.domainCommands.exceptions.NoSuchWorkerException;
import app.repository.WorkerRepository;

public class SetConsultantPropertiesFromRepo implements Command{

	WorkerRepository wRepo;
	String cidString; 
	String name;
	String priceHourString;
	
	public SetConsultantPropertiesFromRepo(WorkerRepository wRepo, String cidString, 
			String name, String priceHour){
		if (wRepo == null || cidString == null){
			throw new IllegalArgumentException();
		}
		this.wRepo = wRepo;
		this.cidString = cidString;
		this.name = name;
		this.priceHourString = priceHour;
		
		setEmptyStringsToNull();
	}
	
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

	private void setEmptyStringsToNull() {
		if (name != null && "".equals(name)) {
			name = null;
		}
		if (priceHourString != null && "".equals(priceHourString)) {
			priceHourString = null;
		}
	}
}
