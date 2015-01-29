package app.domainCommands;

import utils.Consultant;
import utils.Leader;
import app.AppElement;
import app.domainCommands.exceptions.CostOutOfBoundsException;
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
	 * The {@code WorkerRepository} where the {@code AWorker} is to be stored.
	 */
	WorkerRepository wRepo;

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
	 * @param wRepo
	 *            The {@code WorkerRepository} where the {@code AWorker} is to
	 *            be stored.
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
	public AddConsultantToRepo(WorkerRepository wRepo, String name,
			String priceHourString, String bonusString) {

		if (wRepo == null || name == null || priceHourString == null) {
			throw new IllegalArgumentException();
		}
		this.wRepo = wRepo;
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

		long cid = wRepo.nextCID();

		if (bonusString == null) {
			Consultant consultant = new Consultant(name, priceHour, 0, cid);
			wRepo.addConsultant(consultant);
			return new AppElement[] { consultant };
		} else {
			double bonus = Double.parseDouble(bonusString);
			Leader manager = new Leader(name, priceHour, 0, bonus, cid);
			wRepo.addManager(manager);
			return new AppElement[] { manager };
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
