package app.domainCommands;

import utils.Project;
import app.AppElement;
import app.commands.exceptions.CostOutOfBoundsException;
import app.commands.exceptions.GeographicCoordinatesOutOfBoundsException;
import app.commands.exceptions.NoSuchProjectException;
import app.repository.ProjectsRepository;

public class SetProjectPropertiesFromRepo implements Command{

	ProjectsRepository pRepo;
	String pidString;
	String longitudeString;
	String latitudeString;
	String priceString;
	String localName;
	
	public SetProjectPropertiesFromRepo(ProjectsRepository pRepo, String pidString,
			String longitudeString, String latitudeString, String priceString, String localName) {
		
		if (pRepo == null || pidString == null){
			throw new IllegalArgumentException();
		}
		this.pRepo = pRepo;
		this.pidString = pidString;
		this.longitudeString = longitudeString;
		this.latitudeString = latitudeString;
		this.priceString = priceString;
		this.localName= localName;
	}
	
	@Override
	public AppElement[] call() throws NoSuchProjectException, 
			GeographicCoordinatesOutOfBoundsException, CostOutOfBoundsException {
		
		long pid = Long.parseLong(pidString);
		
		Project project = pRepo.getProjectById(pid);

		if (project == null){
			throw new NoSuchProjectException();
		}
		
		patchParameters(project);

		return new AppElement[]{project};
	}
	
	/** 
	 * Verifies which parameters of the {@code Project} were parsed into the parameter's {@code Map},
	 * and updates the {@code Project} accordingly. If any parameter is out of bounds a {@code Message}
	 * will be returned.
	 * @param project
	 * @return A {@code Message} if a parameter is out of bounds
	 * @return {@code null} if no {@code Message} was needed 
	 * @throws GeographicCoordinatesOutOfBoundsException 
	 * @throws CostOutOfBoundsException 
	 */
	private void patchParameters(Project project) throws GeographicCoordinatesOutOfBoundsException, 
			CostOutOfBoundsException{
		//TODO actualizar documentacao
		if (localName != null){
			project.updateLocalName(localName);
		}
		if (longitudeString != null){
			patchLongitude(project);
		}
		if (latitudeString != null){
			patchLatitude(project);
		}
		if (priceString != null){
			patchPrice(project);
		}
	}
	
	private void patchLongitude(Project project) throws GeographicCoordinatesOutOfBoundsException{
		double longitude = Double.parseDouble(longitudeString);
		if (!project.updateLongitude(longitude)){
			throw new GeographicCoordinatesOutOfBoundsException();
		}
	}
	
	private void patchLatitude(Project project) throws GeographicCoordinatesOutOfBoundsException{
		double latitude = Double.parseDouble(latitudeString);
		if (!project.updateLatitude(latitude)){
			throw new GeographicCoordinatesOutOfBoundsException();
		}
	}
	
	private void patchPrice(Project project) throws CostOutOfBoundsException{
		double price = Double.parseDouble(priceString);
		if(!project.updateLocalPrice(price)){
			throw new CostOutOfBoundsException();
		}
	}
}
