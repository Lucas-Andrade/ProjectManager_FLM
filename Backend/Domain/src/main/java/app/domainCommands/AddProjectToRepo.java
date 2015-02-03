package app.domainCommands;

import app.AppElement;
import app.elements.mutable.ProjectCreationDescriptor;
import app.repository.ProjectsRepository;

/**
 * This {@code Command} allows to construct a new {@code Project} and add it to
 * the repository.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class AddProjectToRepo implements Command {

	/**
	 * The {@code ProjectsRepository} where the {@code Project} is to be added.
	 */
	ProjectsRepository pRepo;

	/**
	 * The {@code latitude} of the new {@code Project}.
	 */
	String latitudeString;

	/**
	 * The {@code longitude} of the new {@code Project}.
	 */
	String longitudeString;

	/**
	 * The name of the new {@code Project}'s {@code Local}.
	 */
	String name;

	/**
	 * The {@code price} of the {@code Project}'s {@code Local}.
	 */
	String priceString;

	/**
	 * Constructor of the {@code Command}.
	 * 
	 * @param pRepo
	 *            The {@code ProjectsRepository} where the {@code Project} is to
	 *            be added.
	 * @param latitude
	 *            The {@code latitude} of the new {@code Project}.
	 * @param longitude
	 *            The {@code longitude} of the new {@code Project}.
	 * @param name
	 *            The name of the new {@code Project}'s {@code Local}.
	 * @param price
	 *            The {@code price} of the {@code Project}'s {@code Local}.
	 */
	public AddProjectToRepo(ProjectsRepository pRepo, String latitude,
			String longitude, String name, String price) {
		if (pRepo == null || latitude == null || longitude == null
				|| name == null || price == null) {
			throw new IllegalArgumentException();
		}
		this.pRepo = pRepo;
		this.latitudeString = latitude;
		this.longitudeString = longitude;
		this.name = name;
		this.priceString = price;
	}

	/**
	 * @return an array of {@code AppElement}s containing the new
	 *         {@code Project}.
	 * @see Command#call()
	 */
	@Override
	public AppElement[] call() {

		double latitude = Double.parseDouble(latitudeString);
		double longitude = Double.parseDouble(longitudeString);
		double price = Double.parseDouble(priceString);
		
		ProjectCreationDescriptor projectCreation = new ProjectCreationDescriptor(latitude, longitude, name, price);

		long projectId = pRepo.addProject(projectCreation);

		return new AppElement[] { pRepo.getProjectById(projectId) };
	}

}
