package app.commands;

import java.io.IOException;
import java.util.Map;

import utils.AWorker;
import utils.Consultant;
import utils.Leader;
import utils.Project;
import utils.Team;
import app.commands.exceptions.CommandException;
import app.repository.ProjectRepository;
import app.resultsOutputMethods.ResultOutputMethod;

/**
 * Class whose instances are {@link Command}s that return the {@link AWorker}s
 * in a {@link Project}.
 */
public class GetProjectWorkers extends BaseCommand
{

	/**
	 * The {@link ProjectRepository} with the {@code Project}s. This
	 * {@code ProjectRepository} is accessed to get the {@code AWorker}s from an
	 * existing {@code Project}.
	 */
	private final ProjectRepository projectRepository;

	/**
	 * {@code String} with the {@code AWorker}'s Type argument. This argument is
	 * used for getting the right type of {@code AWorker} from the
	 * {@code Project}.
	 */
	private String typeWorker;

	/**
	 * {@code long} with the argument PID. This argument is used for getting the
	 * {@code Project} from the {@code ProjectRepository}.
	 */
	private long projectId;

	/**
	 * {@code String} with the {@code Project}ID argument's name. The
	 * {@link CommandParser.Node.content} to be used (between "{" and "}", see
	 * {@link CommandParser#Node#isPlaceHolderNode()}) in one of the
	 * {@link CommandParser#Node}s of this {@code Command}'s Path.
	 */
	public static final String PID = "pid";

	/**
	 * {@code String} with the {@code AWorker} type argument's name. The
	 * {@link CommandParser.Node.content} to be used (between "{" and "}", see
	 * {@link CommandParser#Node#isPlaceHolderNode()}) in the
	 * {@link CommandParser#Node} that has the {@code GetProjectWorkers#Factory}
	 * in the field {@link CommandParser#Node#factory}.
	 */
	public static final String WTYPE = "type";

	/**
	 * An array of {@code String}s with the names of all mandatory arguments.
	 */
	private static final String[] DEMANDING_PARAMETERS = { PID, WTYPE };

	/**
	 * Class that implements the {@code GetProjectWorkers} factory, according to
	 * the {@link CommandFactory}.
	 */
	public static class Factory implements CommandFactory
	{

		/**
		 * The {@link ProjectRepository} with the {@code Project}s. This
		 * {@code ProjectRepository} is accessed to get the {@code AWorker}s
		 * from an existing {@code Project}.
		 */
		private final ProjectRepository pRepository;

		/**
		 * The constructor for {@code Factory}.
		 * 
		 * @param pRepository
		 *            The {@code ProjectRepository} with the {@code Project}.
		 */
		public Factory(ProjectRepository pRepository)
		{
			this.pRepository = pRepository;
		}

		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Command newInstance(Map<String, String> parameters)
		{
			return new GetProjectWorkers(pRepository, parameters);
		}
	}

	/**
	 * The constructor for {@code GetProjectWorkers}.
	 * 
	 * @param pRepository
	 *            The {@code ProjectRepository}.
	 * @param parameters
	 *            The {@code Command} arguments.
	 */
	private GetProjectWorkers(ProjectRepository pRepository,
			Map<String, String> parameters)
	{
		super(parameters);
		this.projectRepository = pRepository;
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
	 * Get's the {@code AWorker}s from the {@code Project} with the PID
	 * {@link GetProjectWorkers#projectId} (if the {@code Project} exists and
	 * has the {@code AWorker}(s)). If {@link GetProjectWorkers#typeWorker}
	 * indicates Manager, then this {@code Command} get's the Manager (
	 * {@link Leader}) from the {@code Project} ({@link Project#manager}). If
	 * indicates Consultant get's all the {@link Consultant}s in the
	 * {@link Team} of the {@code Project} ({@link Project#team}).
	 * 
	 * @see BaseCommand#internalExecute(ResultOutputMethod)
	 */
	@Override
	protected void internalExecute(ResultOutputMethod out)
			throws CommandException, IOException
	{

		projectId = getParameterAsLong(PID);
		this.typeWorker = getParameterAsString(WTYPE);

		if (typeWorker.equalsIgnoreCase("Manager"))
		{
			Leader manager = projectRepository.getProjectById(projectId)
					.getManager();
			out.giveResults(manager);
			return;
		} else if (typeWorker.equalsIgnoreCase("Consultant"))
		{
			Iterable<AWorker> workers = projectRepository.getProjectById(
					projectId).getTeam();
			out.giveResults(workers);

			return;
		} else
			out.giveResults("Unrecognised type of worker.");
	}

}
