package app.commands;

import java.util.Map;

import app.repository.ProjectRepository;
import app.repository.UserRepository;
import app.repository.WorkerRepository;

public class PatchConsultant extends BasePatchCommand{

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
		private final WorkerRepository repository;


		/**
		 * The constructor for {@code Factory}.
		 * 
		 * @param uRepository
		 *            The {@code UserRepository} with the {@code User}.
		 * @param repository
		 *            The {@code WorkerRepository} with the {@code AWorker}s.
		 */
		public Factory(WorkerRepository repository)
		{
			this.repository = repository;
		}

		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Command newInstance(Map<String, String> parameters)
		{
			return new PatchConsultant(repository, parameters);
		}
	}

	/**
	 * The constructor for {@code PostConsultant}.
	 * @param repository
	 *            The {@code WorkerRepository}.
	 * @param parameters
	 *            The {@code Command} arguments.
	 */
	public PatchConsultant(WorkerRepository repository, Map<String, String> parameters)
	{
		super(parameters);
		this.repository = repository;
	}



}
