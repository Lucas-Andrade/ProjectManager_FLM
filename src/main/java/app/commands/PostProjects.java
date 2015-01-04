package app.commands;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;

import utils.Local;
import utils.Project;
import app.commands.exceptions.CommandException;
import app.elements.DatabaseElement;
import app.elements.Message;
import app.repository.ProjectsRepository;
import app.repository.UserRepository;
import app.resultsAndOutputMethods.Result;

/**
 * Class whose instances are {@link Command}s that create new {@link Project}s.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class PostProjects extends BaseCommandUserAuthentication
{

	/**
	 * The {@link ProjectsRepository} with the {@code Project}s. The created
	 * {@code Project}s are stored in this {@code ProjectRepository}. Also, the
	 * PID for the new {@code Project}s is obtained from the
	 * {@code ProjectRepository} (there can't be more than one {@code Project}
	 * with the same PID). For creating a new {@code Project}, this
	 * {@code Command} has to create the {@code Project}'s {@link Local}.
	 * Caller {@code String}: POST /project {parameter list}
	 */
	private final ProjectsRepository repository;

	/**
	 * {@code String} with the {@code Local} Latitude argument's name.
	 */
	private static final String LATITUDE = "latitude";

	/**
	 * {@code String} with the {@code Local} Longitude argument's name.
	 */
	private static final String LONGITUDE = "longitude";

	/**
	 * {@code String} with the {@code Local} Name argument's name.
	 */
	private static final String NAME = "name";

	/**
	 * {@code String} with the {@code Local} Price argument's name.
	 */
	private static final String PRICE = "price";

	/**
	 * An array of {@code String}s with the names of all mandatory arguments.
	 */
	private static final String[] DEMANDING_PARAMETERS = { LATITUDE, LONGITUDE,
			NAME, PRICE };

	/**
	 * Class that implements the {@link PostProjects} factory, according to the
	 * {@link CommandFactory}.
	 */
	public static class Factory implements CommandFactory
	{

		/**
		 * The {@link ProjectsRepository} with the {@code Project}s. The created
		 * {@code Project}s are stored in this {@code ProjectRepository}. Also,
		 * the PID for the new {@code Project}s is obtained from the
		 * {@code ProjectRepository} (there can't be more than one
		 * {@code Project} with the same PID).
		 */
		private final ProjectsRepository repository;

		/**
		 * @see BaseCommandUserAuthentication#repository
		 */
		private final UserRepository uRepository;

		/**
		 * The constructor for {@code Factory}.
		 * 
		 * @param uRepository
		 *            The {@code UserRepository} with the {@code User}.
		 * @param repository
		 *            The {@code ProjectRepository} with the {@code Project}.
		 */
		public Factory(UserRepository uRepository, ProjectsRepository repository)
		{
			this.repository = repository;
			this.uRepository = uRepository;
		}

		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Callable<Result> newInstance(Map<String, String> parameters)
		{
			return new PostProjects(uRepository, repository, parameters);
		}
	}

	/**
	 * The constructor for {@code GetProjectWorkers}.
	 * 
	 * @param uRepository
	 *            The {@code UserRepository}.
	 * @param repository
	 *            The {@code ProjectRepository}.
	 * @param parameters
	 *            The {@code Command} arguments.
	 */
	public PostProjects(UserRepository uRepository,
			ProjectsRepository repository, Map<String, String> parameters)
	{
		super(uRepository, parameters);
		this.repository = repository;
	}

	/**
	 * @see app.commands.BaseCommand#getMandatoryParameters()
	 */
	@Override
	protected String[] getMandatoryParameters()
	{
		return DEMANDING_PARAMETERS;
	}

	/**
	 * Creates a new {@code Local} and a new {@code Project} with the created
	 * {@code Local}. Get's the new {@code Project}'s PID from the
	 * {@code ProjectRepository} and adds it to the {@code ProjectRepository}.
	 * Outputs the new {@code Project}'s PID.
	 * 
	 * @see BaseCommandUserAuthentication#internalCall()
	 */
	@Override
	protected DatabaseElement[] internalCall()
			throws CommandException, IOException
	{
		double latitude = getParameterAsDouble(LATITUDE);
		double longitude = getParameterAsDouble(LONGITUDE);
		String name = getParameterAsString(NAME);
		double price = getParameterAsDouble(PRICE);

		Local local = new Local(latitude, longitude, name, price);
		long pid = repository.getNextPID();

		Project project = new Project(local, pid);

		repository.addProject(project);
		
		//acrescentei ... 
		Message message = new Message("Product identification (PID): " + pid);
		DatabaseElement[] messageAux = {message};
		return messageAux;
	}

}
