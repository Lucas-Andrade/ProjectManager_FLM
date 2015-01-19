package app.commands;

import utils.AWorker;
import app.AppElement;
import app.commands.exceptions.CostOutOfBoundsException;
import app.commands.exceptions.NoSuchWorkerException;
import app.repository.WorkerRepository;

public class SetConsultantPropertiesFromRepo implements Command{

	WorkerRepository wRepo;
	String cidString; 
	String name;
	String priceHourString;
	
	public SetConsultantPropertiesFromRepo(WorkerRepository wRepo, String cidString, 
			String name, String priceHour){
		if (wRepo == null || cidString == null || name == null || priceHour == null){
			throw new IllegalArgumentException();
		}
		this.wRepo = wRepo;
		this.cidString = cidString;
		this.name = name;
		this.priceHourString = priceHour;
	}
	
	@Override
	public AppElement[] call() throws NoSuchWorkerException, CostOutOfBoundsException {
		
		long cid = Long.parseLong(cidString);
		double priceHour = Double.parseDouble(priceHourString);
		AWorker worker = wRepo.getAWorkerByID(cid);
		
		if (worker == null){
			throw new NoSuchWorkerException();
		}
		
		if (name != null){
			worker.setName(name);
		}
		
		if (priceHourString != null && !worker.setCostPerHour(priceHour)){
			throw new CostOutOfBoundsException();
		}
		
		return new AppElement[]{worker};
	}

}
