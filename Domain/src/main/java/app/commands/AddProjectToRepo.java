package app.commands;

import utils.Local;
import utils.Project;
import app.AppElement;
import app.repository.ProjectsRepository;

public class AddProjectToRepo implements Command{
	
	ProjectsRepository pRepo;
	String latitudeString;
	String longitudeString;
	String name;
	String priceString;
	
	public AddProjectToRepo(ProjectsRepository pRepo, String latitude, String longitude, String name, String price){
		if (pRepo == null || latitude == null || longitude == null || name == null || price == null){
			throw new IllegalArgumentException();
		}
		this.pRepo = pRepo;
		this.latitudeString = latitude;
		this.longitudeString = longitude;
		this.name = name;
		this.priceString = price;
	}
	
	@Override
	public AppElement[] call() {
		
		double latitude = Double.parseDouble(latitudeString);
		double longitude = Double.parseDouble(longitudeString);
		double price = Double.parseDouble(priceString);
		
		Local local = new Local(latitude, longitude, name, price);
	
		long pid = pRepo.getNextPID();
		Project project = new Project(local, pid);

		pRepo.addProject(project);
		
		return new AppElement[]{project};
	}

}
