package commandProxy;

import app.AppElement;
import app.domainCommands.exceptions.CostOutOfBoundsException;
import app.repository.ConsultantCreationDescriptor;
import app.repository.LeaderCreationDescriptor;
import app.repository.WorkerRepository;

/**
 * This {@code Command} allows to construct a new {@code AWorker} and add it to
 * the repository.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class AddConsultantToRepo implements Command {



	/**
	 * The {@code name} of the new {@code AWorker}.
	 */
	String name;

	/**
	 * The price per hour of the new {@code AWorker}.
	 */
	String priceHourString;

	/**
	 * The bonus of the new {@code AWorker}, if it happens to be a
	 * {@code Leader}. If {@code null} or an empty {@code String} is introduced
	 * in this parameter, a {@code Consultant} will be constructed instead.
	 */
	String bonusString;

	/**
	 * Constructor of the {@code Command}.
	 * 
	 * @param name
	 *            The {@code name} of the new {@code AWorker}.
	 * @param priceHourString
	 *            The price per hour of the new {@code AWorker}.
	 * @param bonusString
	 *            The bonus of the new {@code AWorker}, if it happens to be a
	 *            {@code Leader}. If {@code null} or an empty {@code String} is
	 *            introduced in this parameter, a {@code Consultant} will be
	 *            constructed instead.
	 */
	public AddConsultantToRepo(String name,	String priceHourString, String bonusString) {

		if (name == null || priceHourString == null) {
			throw new IllegalArgumentException();
		}
		this.name = name;
		this.priceHourString = priceHourString;
		this.bonusString = bonusString;

		setEmptyStringsToNull();
	}

	/**
	 * @return an array of {@code AppElement}s containing the newly constructed
	 *         {@code AWorker}.
	 * @see Command#call()
	 */
	@Override
	public AppElement[] call() throws CostOutOfBoundsException {

		double priceHour = Double.parseDouble(priceHourString);

		if (priceHour < 0) {
			throw new CostOutOfBoundsException("The cost cannot be negative.");
		}

		
		if (bonusString == null) {
			ConsultantCreationDescriptor consultantCreation = new ConsultantCreationDescriptor(name, priceHour, 0, 0);
			return new AppElement[] { wRepo.getConsultantByID(wRepo.addConsultant(consultantCreation)) };
		} else {
			double bonus = Double.parseDouble(bonusString);
			LeaderCreationDescriptor managerCreation = new LeaderCreationDescriptor(name, priceHour, 0, bonus);
			
			return new AppElement[] {wRepo.getManagerByID(wRepo.addManager(managerCreation)) };
		}
	}

	/**
	 * Sets {@code bonus} to {@code null} if an empty string was introduced.
	 */
	private void setEmptyStringsToNull() {
		if ("".equals(bonusString)) {
			bonusString = null;
		}
	}
}
