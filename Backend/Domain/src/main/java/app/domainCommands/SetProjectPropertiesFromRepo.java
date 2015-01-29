package app.domainCommands;

import utils.Project;
import app.AppElement;
import app.domainCommands.exceptions.CostOutOfBoundsException;
import app.domainCommands.exceptions.GeographicCoordinatesOutOfBoundsException;
import app.domainCommands.exceptions.NoSuchProjectException;
import app.repository.ProjectsRepository;

/**
 * This {@code Command} allows to perform alterations to a project's properties.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class SetProjectPropertiesFromRepo implements Command {

	/**
	 * The {@code ProjectsRepository} where the {@code Project} is stored
	 */
	ProjectsRepository pRepo;

	/**
	 * The {@code PID} of the {@code Project} which properties are to be altered
	 */
	String pidString;

	/**
	 * The new {@code longitude}
	 */
	String longitudeString;

	/**
	 * The new {@code latitude}
	 */
	String latitudeString;

	/**
	 * The new {@code Local}'s {@code price}
	 */
	String priceString;

	/**
	 * The new {@code Local}'s {@code name}
	 */
	String localName;

	/**
	 * Constructs the {@code Command}. It is allowed to pass as parameter new
	 * values for all the modifiable properties of the {@code Project}. But if
	 * some properties are not to be changed it is allowed not to introduce any
	 * new values for them: if {@code null} or an empty {@code String} is passed
	 * in one of those parameters, the corresponding property will not be
	 * altered.
	 * 
	 * Non mandatory parameters are: {@code longitudeString}, {@code latitudeString}, 
	 * {@code priceString} and {@code localName}
	 * 
	 * Mandatory parameters are: {@code pRepo} and {@code pidString}. If one of these
	 * is null, a {@code IllegalArgumentException} will be thrown.
	 * 
	 * @param pRepo
	 *            The {@code ProjectsRepository} where the {@code Project} is
	 *            stored
	 * @param pidString
	 *            The {@code PID} of the {@code Project} which properties are to
	 *            be altered
	 * @param longitudeString
	 *            The new {@code longitude}
	 * @param latitudeString
	 *            The new {@code latitude}
	 * @param priceString
	 *            The new {@code Local}'s {@code price}
	 * @param localName
	 *            The new {@code Local}'s {@code name}
	 */
	public SetProjectPropertiesFromRepo(ProjectsRepository pRepo,
			String pidString, String longitudeString, String latitudeString,
			String priceString, String localName) {

		if (pRepo == null || pidString == null) {
			throw new IllegalArgumentException();
		}
		this.pRepo = pRepo;
		this.pidString = pidString;
		this.longitudeString = longitudeString;
		this.latitudeString = latitudeString;
		this.priceString = priceString;
		this.localName = localName;

		setEmptyStringsToNull();
	}

	/**
	 * @return an array of {@code AppElement}s containing the modified {@code Project}.
	 * @see Command#call()
	 */
	@Override
	public AppElement[] call() throws NoSuchProjectException,
			GeographicCoordinatesOutOfBoundsException, CostOutOfBoundsException {

		long pid = Long.parseLong(pidString);

		Project project = pRepo.getProjectById(pid);

		if (project == null) {
			throw new NoSuchProjectException(
					"There is no project with that ID.");
		}

		patchParameters(project);

		return new AppElement[] { project };
	}

	/**
	 * Verifies which parameters of the {@code Project} were introduced and
	 * updates the {@code Project} accordingly. If any parameter is out of
	 * bounds a {@code Message} will be returned.
	 * 
	 * @param project
	 * @throws GeographicCoordinatesOutOfBoundsException
	 * @throws CostOutOfBoundsException
	 */
	private void patchParameters(Project project)
			throws GeographicCoordinatesOutOfBoundsException,
			CostOutOfBoundsException {
		if (localName != null) {
			project.updateLocalName(localName);
		}
		if (longitudeString != null) {
			patchLongitude(project);
		}
		if (latitudeString != null) {
			patchLatitude(project);
		}
		if (priceString != null) {
			patchPrice(project);
		}
	}

	/**
	 * Updates the {@code longitude} of the {@code Project}, if it is within the
	 * correct boundaries
	 * 
	 * @param project
	 * @throws GeographicCoordinatesOutOfBoundsException
	 */
	private void patchLongitude(Project project)
			throws GeographicCoordinatesOutOfBoundsException {
		double longitude = Double.parseDouble(longitudeString);
		if (!project.updateLongitude(longitude)) {
			throw new GeographicCoordinatesOutOfBoundsException(
					"The longitude is out of bounds.");
		}
	}

	/**
	 * Updates the {@code latitude} of the {@code Project} if it is within the
	 * correct boundaries
	 * 
	 * @param project
	 * @throws GeographicCoordinatesOutOfBoundsException
	 */
	private void patchLatitude(Project project)
			throws GeographicCoordinatesOutOfBoundsException {
		double latitude = Double.parseDouble(latitudeString);
		if (!project.updateLatitude(latitude)) {
			throw new GeographicCoordinatesOutOfBoundsException(
					"The latitude is out of bounds.");
		}
	}

	/**
	 * Updates the {@code cost} of the {@code Project} as long it is not
	 * negative
	 * 
	 * @param project
	 * @throws CostOutOfBoundsException
	 */
	private void patchPrice(Project project) throws CostOutOfBoundsException {
		double price = Double.parseDouble(priceString);
		if (!project.updateLocalPrice(price)) {
			throw new CostOutOfBoundsException("The cost cannot be negative.");
		}
	}

	/**
	 * Checks if any of the construcor's non mandatory parameters are empty
	 * {@code String}s. If any of them is an empty {@code String}, it is set to
	 * {@code null}.
	 */
	private void setEmptyStringsToNull() {
		if ("".equals(longitudeString)) {
			longitudeString = null;
		}
		if ("".equals(latitudeString)) {
			latitudeString = null;
		}
		if ("".equals(priceString)) {
			priceString = null;
		}
		if ("".equals(localName)) {
			localName = null;
		}
	}
}
