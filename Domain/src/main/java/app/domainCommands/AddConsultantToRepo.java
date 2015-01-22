package app.domainCommands;

import utils.Consultant;
import utils.Leader;
import app.AppElement;
import app.domainCommands.exceptions.CostOutOfBoundsException;
import app.repository.WorkerRepository;

public class AddConsultantToRepo implements Command{
	
	WorkerRepository wRepo;
	String name;
	String priceHourString;
	String bonusString;
	
	public AddConsultantToRepo(WorkerRepository wRepo, String name, String priceHourString,
			String bonusString) {
		
		if (wRepo == null || name == null || priceHourString == null){
			throw new IllegalArgumentException();
		}
		this.wRepo = wRepo;
		this.name = name;
		this.priceHourString = priceHourString;
		this.bonusString = bonusString;
		
		setEmptyStringsToNull();
	}
	
	@Override
	public AppElement[] call() throws CostOutOfBoundsException {
		
		double priceHour = Double.parseDouble(priceHourString);
		
		if (priceHour < 0){
			throw new CostOutOfBoundsException("The cost cannot be negative.");
		}

		long cid = wRepo.nextCID();
		
		if(bonusString == null){
			Consultant consultant = new Consultant(name, priceHour, 0, cid);
			wRepo.addConsultant(consultant);
			return new AppElement[]{consultant};
		} else {
			double bonus = Double.parseDouble(bonusString);
			Leader manager = new Leader(name, priceHour, 0, bonus, cid);
			wRepo.addManager(manager);
			return new AppElement[]{manager};
		}
	}

	private void setEmptyStringsToNull() {
		if (bonusString != null && bonusString.equals("")) {
			bonusString = null;
		}
	}
}
