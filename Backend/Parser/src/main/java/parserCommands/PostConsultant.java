package parserCommands;

import java.util.Map;
import java.util.concurrent.Callable;

import outputMethods.Result;
import parserUtils.CommandFactory;
import utils.AWorker;
import app.AppElement;
import app.domainCommands.AddConsultantToRepo;
import app.domainCommands.exceptions.CostOutOfBoundsException;
import app.elements.Message;
import app.repository.UserRepository;
import app.repository.WorkerRepository;

/**
 * Class whose instances are commands that create new {@link AWorker}s.
 * Caller {@code String}: POST /consultant {parameter list}
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class PostConsultant extends BaseCommandUserAuthentication{

	/**
	 * The {@link WorkerRepository} with the {@code AWorker}s. The created
	 * {@code AWorker}s are stored in this {@code WorkerRepository}. Also, the
	 * {@code AWorker}ID for the new {@code AWorker}s is obtained from the
	 * {@code WorkerRepository} (there can't be more than one {@code AWorker}
	 * with the same {@code AWorker}ID).
	 */
	private final WorkerRepository repository;

	/**
	 * {@code String} with the {@code AWorker} Name argument's name.
	 */
	public static final String NAME = "name";

	/**
	 * {@code String} with the {@code Consultant} or {@code Leader} Price Per
	 * Hour argument's name.
	 */
	public static final String PRICE_HOUR = "priceHour";

	/**
	 * {@code String} with the {@code Leader} Bonus argument's name.
	 */
	public static final String BONUS = "bonus";

	/**
	 * An array of {@code String}s with the names of all mandatory arguments.
	 */
	private static final String[] DEMANDING_PARAMETERS = { NAME, PRICE_HOUR };

	/**
	 * Class that implements the {@link PostConsultant} factory, according to
	 * the {@link CommandFactory}.
	 */
	public static class Factory implements CommandFactory{

		/**
		 * The {@link WorkerRepository} with the {@code AWorker}s. The created
		 * {@code AWorker}s are stored in this {@code WorkerRepository}. Also,
		 * the {@code AWorker}ID for the new {@code AWorker}s is obtained from
		 * the {@code WorkerRepository} (there can't be more than one
		 * {@code AWorker} with the same {@code AWorker}ID).
		 */
		private final WorkerRepository repository;

		/**
		 * @see BaseCommandUserAuthentication#repository
		 */
		private final UserRepository uRepository;

		/**
		 * The constructor for {@code Factory}.
		 * 
		 * @param uRepository    The {@code UserRepository} with the {@code User}.
		 * @param repository     The {@code WorkerRepository} with the {@code AWorker}s.
		 */
		public Factory(UserRepository uRepository, WorkerRepository repository){
			this.repository = repository;
			this.uRepository = uRepository;
		}

		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Callable<Result> newInstance(Map<String, String> parameters){
			return new PostConsultant(uRepository, repository, parameters);
		}
	}

	/**
	 * The constructor for {@code PostConsultant}.
	 * 
	 * @param uRepository    The {@code UserRepository}.
	 * @param repository     The {@code WorkerRepository}.
	 * @param parameters     The command arguments.
	 */
	public PostConsultant(UserRepository uRepository,
			WorkerRepository repository, Map<String, String> parameters){
		super(uRepository, parameters);
		this.repository = repository;
	}

	/**
	 * @see app.commands.BaseCommand#getMandatoryParameters()
	 */
	@Override
	protected String[] getMandatoryParameters(){
		return DEMANDING_PARAMETERS;
	}

	/**
	 * Creates a new {@code AWorker}. Get's the new {@code AWorker}'s ID from
	 * the {@code WorkerRepository} and adds it to the {@code WorkerRepository}.
	 * Outputs the new {@code AWorker}'s ID. If it has Bonus, that it creates a
	 * {@code Leader}, else creates a {@code Consultant}.
	 * 
	 * @see BaseCommandUserAuthentication#internalCall()
	 */
	@Override
	protected AppElement[] internalCall(){
		String name = getParameterAsString(NAME);
		String priceHour = getParameterAsString(PRICE_HOUR);
		String bonus = getParameterAsString(BONUS);
		
		AppElement[] elements;
		try{
			elements = new AddConsultantToRepo(repository, name, priceHour, bonus).call();
		} catch(CostOutOfBoundsException e) {
			return new AppElement[]{new Message("Specified price per hour of the worker is less than zero.")};
		} catch(IllegalArgumentException e) {
			return new AppElement[]{new Message("Cannot post a manager with negative bonus.")};
		} catch(ClassCastException e) {
			return new AppElement[]{new Message("Unexpected result.")}; //just in case
		}
		
		return elements;
	}
}
