package app.commands;

import java.io.IOException;
import java.util.Map;

import utils.AWorker;
import app.commands.exceptions.CommandException;
import app.repository.UserRepository;
import app.repository.WorkerRepository;
import app.resultsOutputMethods.ResultOutputMethod;

/**
 * Class whose instances are {@link Command}s that modifies {@link AWorker}s.
 * Caller {@code String}: PATCH /consultants/{cid} {parameter list}
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 17/12/2014
 */
public class PatchConsultant extends BaseCommandAuthentication
{

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
	public static class Factory implements CommandFactory
	{

		/**
		 * The {@link WorkerRepository} with the {@code AWorker}s. The modified
		 * {@code AWorker}s are stored in this {@code WorkerRepository}.
		 */
		private final WorkerRepository pRepository;

		private final UserRepository uRepository;

		/**
		 * The constructor for {@code Factory}.
		 * 
		 * @param uRepository
		 *            The {@code UserRepository} with the {@code User}.
		 * @param repository
		 *            The {@code WorkerRepository} with the {@code AWorker}s.
		 */
		public Factory(UserRepository uRepository, WorkerRepository pRepository)
		{
			this.pRepository = pRepository;
			this.uRepository = uRepository;
		}

		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Command newInstance(Map<String, String> parameters)
		{
			return new PatchConsultant(uRepository, pRepository, parameters);
		}
	}

	/**
	 * The constructor for {@code PostConsultant}.
	 * 
	 * @param repository
	 *            The {@code WorkerRepository}.
	 * @param parameters
	 *            The {@code Command} arguments.
	 */
	public PatchConsultant(UserRepository uRepository,
			WorkerRepository repository, Map<String, String> parameters)
	{
		super(uRepository, parameters);
		this.repository = repository;
	}

	/**
	 * Modifies an {@code AWorker}. Get's the {@code AWorker}'s from the
	 * {@code WorkerRepository} and modifies it.
	 * 
	 * @see BaseCommandAuthentication#internalPostExecute(ResultOutputMethod)
	 */
	@Override
	protected void internalPostExecute(ResultOutputMethod out)
			throws CommandException, IOException
	{
		AWorker worker = repository
				.getAWorkerByID(this.getParameterAsLong(CID));
		if (parameters.containsKey(NAME))
			worker.setName(parameters.get(NAME));
		if (parameters.containsKey(PRICE_HOUR))
			worker.setCostPerHour(this.getParameterAsDouble(PRICE_HOUR));
	}

	/**
	 * @see app.commands.BaseCommand#getMandatoryParameters()
	 */
	@Override
	protected String[] getMandatoryParameters()
	{
		return DEMANDING_PARAMETERS;
	}

}
