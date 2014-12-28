package app.commands;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;

import utils.AWorker;
import utils.Consultant;
import utils.Leader;
import utils.Project;
import utils.Team;
import app.commandParser.CommandParser;
import app.commands.exceptions.CommandException;
import app.elements.DatabaseElement;
import app.repository.ProjectsRepository;
import app.repository.UserRepository;
import app.repository.WorkerRepository;
import app.resultsAndOutputMethods.Result;
import app.resultsAndOutputMethods.ResultOutputMethod;
import app.resultsAndOutputMethods.ResultOutputMethodToStream;

/**
 * Class whose instances are {@link Command}s that add {@link AWorker}s to
 * {@code Project}s.
 * Caller {@code String}: POST /project/{pid}/{type} {parameter list}
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class PostWorkerInProject extends BaseCommandUserAuthentication
{

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
	 * {@link CommandParser#Node} that has the
	 * {@code PostWorkerInProject#Factory} in the field
	 * {@link CommandParser#Node#factory}.
	 */
	public static final String WTYPE = "type";

	/**
	 * {@code String} with the {@code AWorker}ID argument's name.
	 */
	public static final String CID = "cid";

	/**
	 * The {@link ProjectsRepository} with the {@code Project}s. This
	 * {@code ProjectRepository} is accessed to get the {@code Project} where
	 * the {@code AWorker} is going to be added.
	 */
	private final ProjectsRepository projectRepository;

	/**
	 * The {@link WorkerRepository} with the {@code AWorker}s. This
	 * {@code WorkerRepository} is accessed to get the {@code AWorker} that is
	 * going to be added to an existing {@code Project}.
	 */
	private final WorkerRepository workerRepository;

	/**
	 * An array of {@code String}s with the names of all mandatory arguments.
	 */
	private static final String[] DEMANDING_PARAMETERS = { PID, WTYPE, CID };

	/**
	 * {@code long} with the argument PID. This argument is used for getting the
	 * {@code Project} from the {@code ProjectRepository}.
	 */
	private long projectId;

	/**
	 * {@code String} with the {@code AWorker}'s Type argument. This argument is
	 * used for adding the right type of {@code AWorker} to the {@code Project}.
	 */
	private String typeWorker;

	/**
	 * {@code long} with the argument {@code AWorker}ID. This argument is used
	 * for getting the {@code AWorker} from the {@code WorkerRepository}.
	 */
	private long workerId;

	/**
	 * Class that implements the {@link PostWorkerInProject} factory, according to
	 * the {@link CommandFactory}.
	 */
	public static class Factory implements CommandFactory
	{

		/**
		 * The {@link ProjectsRepository} with the {@code Project}s. This
		 * {@code ProjectRepository} is accessed to get the {@code Project}
		 * where the {@code AWorker} is going to be added.
		 */
		private final ProjectsRepository pRepository;

		/**
		 * The {@link WorkerRepository} with the {@code AWorker}s. This
		 * {@code WorkerRepository} is accessed to get the {@code AWorker} that
		 * is going to be added to an existing {@code Project}.
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
		 * @param pRepository
		 *            The {@code ProjectRepository} with the {@code Project}.
		 * @param wRepository
		 *            The {@code WorkerRepository} with the {@code AWorker}.
		 */
		public Factory(UserRepository uRepository,
				ProjectsRepository pRepository, WorkerRepository wRepository)
		{
			this.pRepository = pRepository;
			this.wRepository = wRepository;
			this.uRepository = uRepository;
		}

		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Callable<Result> newInstance(Map<String, String> parameters)
		{
			return new PostWorkerInProject(uRepository, pRepository,
					wRepository, parameters);
		}

	}

	/**
	 * The constructor for {@code PostWorkerInProject}.
	 * 
	 * @param uRepository
	 *            The {@code UserRepository}.
	 * @param pRepository
	 *            The {@code ProjectRepository}.
	 * @param wRepository
	 *            The {@code WorkerRepository}.
	 * @param parameters
	 *            The {@code Command} arguments.
	 */
	public PostWorkerInProject(UserRepository uRepository,
			ProjectsRepository pRepository, WorkerRepository wRepository,
			Map<String, String> parameters)
	{
		super(uRepository, parameters);
		this.projectRepository = pRepository;
		this.workerRepository = wRepository;
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
	 * Adds the {@code AWorker} with the {@code AWorker}ID (
	 * {@link PostWorkerInProject#workerId}; if the {@code AWorker} exists) to
	 * the {@code Project} with the PID {@link PostWorkerInProject#projectId}
	 * (if the {@code Project} exists). If
	 * {@link PostWorkerInProject#typeWorker} indicates Manager, then this
	 * {@code Command} adds {@code AWorker} as a Manager ( {@link Leader};
	 * {@link Project#manager}). If indicates Consultant adds {@code AWorker} as
	 * a {@link Consultant} in the {@link Team} of the {@code Project} (
	 * {@link Project#team}). Outputs True if successful, False if not.
	 * 
	 * @see PostWorkerInProject#addConsultant(ResultOutputMethod, long, long)
	 * @see PostWorkerInProject#addManager(ResultOutputMethod, long, long)
	 * @see BaseCommandUserAuthentication#internalExecuteAfterUserAuthentication(ResultOutputMethod)
	 */
	@Override
	protected DatabaseElement internalCall()
			throws CommandException, IOException
	{
		this.projectId = getParameterAsLong(PID);
		this.typeWorker = getParameterAsString(WTYPE);
		this.workerId = getParameterAsLong(CID);

		Project project = projectRepository.getProjectById(projectId);
		if (project == null)
		{
			out.giveResults("The Specified Project does not exists in repository.");
			return;
		}

		if (typeWorker.equalsIgnoreCase("manager"))
		{
			out.giveResults(addManager(out, projectId, workerId) ? "Success." : 
				"Not successfull. Manager may already be in the project.");
			return;
		} else if (typeWorker.equalsIgnoreCase("consultant")) 
		{
			out.giveResults(addConsultant(out, projectId, workerId) ? "Success." : 
				"Not successfull. Consultant may already be in the project.");

			return;
		} else
			out.giveResults("Unrecognised type of worker.");

	}

	/**
	 * @see PostWorkerInProject#internalExecuteAfterUserAuthentication(ResultOutputMethod)
	 * 
	 * @param out
	 *            The {@link ResultOutputMethodToStream} that receives the
	 *            Results, treats them and gives them to a Stream.
	 * @param projectId
	 *            {@code long} with the argument PID.
	 * @param workerId
	 *            {@code long} with the argument {@code AWorker}ID.
	 * @return True if successful, False if not.
	 * @throws IOException
	 */
	private Boolean addConsultant(ResultOutputMethod out, long projectId,
			long workerId) throws IOException
	{
		Consultant consultant = workerRepository.getConsultantByID(workerId);

		if (consultant != null)
		{
			projectRepository.getProjectById(projectId).addWorker(consultant);
			return true;
		}
		out.giveResults("The Specified Consultant does not exists in repository.");
		return false;
	}

	/**
	 * @see PostWorkerInProject#internalExecuteAfterUserAuthentication(ResultOutputMethod)
	 * 
	 * @param out
	 *            The {@link ResultOutputMethodToStream} that receives the
	 *            Results, treats them and gives them to a Stream.
	 * @param projectId
	 *            {@code long} with the argument PID.
	 * @param workerId
	 *            {@code long} with the argument {@code AWorker}ID.
	 * @return True if successful, False if not.
	 * @throws IOException
	 */
	private Boolean addManager(ResultOutputMethod out, long projectId,
			long workerId) throws IOException
	{
		Leader manager = workerRepository.getManagerByID(workerId);
		if (manager != null)
		{
			projectRepository.getProjectById(projectId).setManager(manager);
			return true;
		}
		out.giveResults("The Specified Manager does not exists in repository.");
		return false;
	}
}
