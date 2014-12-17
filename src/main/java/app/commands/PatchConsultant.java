package app.commands;

import java.io.IOException;
import java.util.Map;

import app.commands.exceptions.CommandException;
import app.repository.ProjectRepository;
import app.repository.UserRepository;
import app.repository.WorkerRepository;
import app.resultsOutputMethods.ResultOutputMethod;

public class PatchConsultant extends BaseCommandAuthentication{

	/**
	 * The {@link WorkerRepository} with the {@code AWorker}s. The created
	 * {@code AWorker}s are stored in this {@code WorkerRepository}. Also, the
	 * {@code AWorker}ID for the new {@code AWorker}s is obtained from the
	 * {@code WorkerRepository} (there can't be more than one {@code AWorker}
	 * with the same {@code AWorker}ID).
	 */
	private final WorkerRepository repository;

	/**
	 *
	 */
	public static final String CID = "cid";

	/**
	 * An array of {@code String}s with the names of all mandatory arguments.
	 */
	private static final String[] DEMANDING_PARAMETERS = { CID };

	/**
	 * Class that implements the {@link PostConsultant} factory, according to
	 * the {@link CommandFactory}.
	 */
	public static class Factory implements CommandFactory
	{

		/**
		 * The {@link WorkerRepository} with the {@code AWorker}s. The created
		 * {@code AWorker}s are stored in this {@code WorkerRepository}. Also,
		 * the {@code AWorker}ID for the new {@code AWorker}s is obtained from
		 * the {@code WorkerRepository} (there can't be more than one
		 * {@code AWorker} with the same {@code AWorker}ID).
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
	 * @param repository
	 *            The {@code WorkerRepository}.
	 * @param parameters
	 *            The {@code Command} arguments.
	 */
	public PatchConsultant(UserRepository uRepository, WorkerRepository repository, Map<String, String> parameters)
	{
		super(uRepository, parameters);
		this.repository = repository;
	}

	@Override
	protected void internalPostExecute(ResultOutputMethod out)
			throws CommandException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String[] getMandatoryParameters() {
		// TODO Auto-generated method stub
		return null;
	}



}
