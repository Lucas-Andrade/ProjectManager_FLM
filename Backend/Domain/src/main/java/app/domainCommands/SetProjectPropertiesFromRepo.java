package app.domainCommands;

import utils.Local;
import utils.Project;
import app.AppElement;
import app.domainCommands.exceptions.CommandExecutionException;
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
	 * The new {@code longitude}
	 */
	double longitude;

	/**
	 * The new {@code latitude}
	 */
	double latitude;

	/**
	 * The new {@code Local}'s {@code price}
	 */
	double price;

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
	 * @throws CommandExecutionException 
	 * @see Command#call()
	 */
	@Override
	public AppElement[] call() throws CommandExecutionException {

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
	 * @throws CommandExecutionException 
	 */
	private void patchParameters(Project project)
			throws CommandExecutionException {
		setName(project);
		setLongitude(project);
		setLatitude(project);
		setPrice(project);
		
		Local local = constructNewLocal();
		if(local != null) {
			project.setLocal(local);
		}
	}
	
	/**
	 * Checks if it has been introduced a new price for the {@code Local}. If it has, the 
	 * price is parsed into a {@code double} format. If it has not, the price of the previous
	 * local is used.
	 * 
	 * @param project
	 */
	private void setPrice(Project project) {
		if (priceString == null) {
			price = project.getLocal().getCost();
		} else {
			price = Double.parseDouble(priceString);
		}
	}

	/**
	 * Checks if it has been introduced a new latitude for the {@code Local}. If it has, the 
	 * latitude is parsed into a {@code double} format. If it has not, the latitude of the previous
	 * local is used.
	 * 
	 * @param project
	 */
	private void setLatitude(Project project) {
		if (latitudeString == null) {
			latitude = project.getLocal().getLatitude();
		} else {
			latitude = Double.parseDouble(latitudeString);
		}
	}

	/**
	 * Checks if it has been introduced a new longitude for the {@code Local}. If it has, the 
	 * longitude is parsed into a {@code double} format. If it has not, the longitude of the previous
	 * local is used.
	 * 
	 * @param project
	 */
	private void setLongitude(Project project) {
		if (longitudeString == null) {
			longitude = project.getLocal().getLongitude();
		} else {
			longitude = Double.parseDouble(longitudeString);
		}
	}

	/**
	 * Checks if it has been introduced a new name for the {@code Local}. 
	 * If it has not, the name of the previous local is used.
	 * 
	 * @param project
	 */
	private void setName(Project project) {
		if (localName == null) {
			localName = project.getLocal().getName();
		} 
	}

	/**
	 * Constructs and returns the {@code Project}'s new {@code Local}
	 * @return the {@code Project}'s new {@code Local}
	 * @throws CommandExecutionException 
	 */
	private Local constructNewLocal() throws CommandExecutionException {
		Local local = null;
		try {
			local = new Local(latitude, longitude, localName, price);
		} catch (IllegalArgumentException e) {
			throw new CommandExecutionException(e.getMessage());
		}
		
		return local;
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
