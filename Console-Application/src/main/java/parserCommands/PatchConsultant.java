package parserCommands;

import java.util.Map;
import java.util.concurrent.Callable;

import outputMethods.Result;
import utils.AWorker;
import app.AppElement;
import app.domainCommands.SetConsultantPropertiesFromRepo;
import app.domainCommands.exceptions.CostOutOfBoundsException;
import app.domainCommands.exceptions.NoSuchWorkerException;
import app.elements.Message;
import app.repository.UserRepository;
import app.repository.WorkerRepository;

/**
 * Class whose instances are commands that modifies {@link AWorker}s.
 * 
 * Caller {@code String}: PATCH /consultants/{cid} {parameter list}
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 17/12/2014
 */
public class PatchConsultant extends BaseCommandUserAuthentication {

	/**
	 * The {@link WorkerRepository} with the {@code AWorker}s. The modified
	 * {@code AWorker}s are stored in this {@code WorkerRepository}.
	 */
	private final WorkerRepository repository;

	/**
	 * {@code String} with the {@code AWorker}ID argument's name. The
	 * {@link CommandParser.Node.content} to be used (between "{" and "}", see
	 * {@link CommandParser#Node#isPlaceHolderNode()}) in the
	 * {@link CommandParser#Node} that has the {@code PatchConsultant#Factory}
	 * in the field {@link CommandParser#Node#factory}.
	 */
	public static final String CID = "cid";

	/**
	 * {@code String} with the {@code AWorker} Name argument's name.
	 */
	public static final String NAME = "name";

	/**
	 * {@code String} with the {@code AWorker} Price Per Hour argument's name.
	 */
	public static final String PRICE_HOUR = "priceHour";

	/**
	 * An array of {@code String}s with the names of all mandatory arguments.
	 */
	private static final String[] DEMANDING_PARAMETERS = { CID };

	/**
	 * Class that implements the {@link PatchConsultant} factory, according to
	 * the {@link CommandFactory}.
	 */
	public static class Factory implements CommandFactory{

		/**
		 * The {@link WorkerRepository} with the {@code AWorker}s. The modified
		 * {@code AWorker}s are stored in this {@code WorkerRepository}.
		 */
		private final WorkerRepository wRepository;

		/**
		 * @see BaseCommandUserAuthentication#repository
		 */
		private final UserRepository uRepository;

		/**
		 * The constructor for {@code Factory}.
		 * 
		 * @param uRepository
		 *            The {@code UserRepository} with the {@code User}.
		 * @param wRepository
		 *            The {@code WorkerRepository} with the {@code AWorker}s.
		 */
		public Factory(UserRepository uRepository, WorkerRepository wRepository){
			this.wRepository = wRepository;
			this.uRepository = uRepository;
		}

		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Callable<Result> newInstance(Map<String, String> parameters){
			return new PatchConsultant(uRepository, wRepository, parameters);
		}
	}

	/**
	 * The constructor for {@code PatchConsultant}.
	 * 
	 * @param uRepository
	 *            The {@code UserRepository}.
	 * @param repository
	 *            The {@code WorkerRepository}.
	 * @param parameters
	 *            The {@code Command} arguments.
	 */
	public PatchConsultant(UserRepository uRepository,
			WorkerRepository repository, Map<String, String> parameters){
		super(uRepository, parameters);
		this.repository = repository;
	}

	/**
	 * Modifies an {@code AWorker}. Get's the {@code AWorker}'s from the
	 * {@code WorkerRepository} and modifies it.
	 * 
	 * @return An array of {@code DatabaseElement} with one element carrying the
	 *         modified {@code AWorker}.
	 */
	@Override
	protected AppElement[] internalCall() {
		
		try{
			new SetConsultantPropertiesFromRepo(repository, getParameterAsString(CID), 
					getParameterAsString(NAME), getParameterAsString(PRICE_HOUR)).call();
		} catch(NoSuchWorkerException e) {
			return new AppElement[] { new Message("Worker with CID: "
					+ getParameterAsLong(CID) + "was not found!") };
		} catch(CostOutOfBoundsException e) {
			return new AppElement[] { new Message(
					"Worker's cost per hour cannot be negative.") };
		}
		
		return new AppElement[] { new Message("The Consultant parameters were successfully changed!")};
	}

	/**
	 * @see app.commands.BaseCommand#getMandatoryParameters()
	 */
	@Override
	protected String[] getMandatoryParameters(){
		return DEMANDING_PARAMETERS;
	}

}
