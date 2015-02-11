package parserCommands;

import java.util.Map;
import java.util.concurrent.Callable;

import outputMethods.Result;
import parser.CommandParser;
import parserUtils.CommandFactory;
import utils.AWorker;
import utils.Consultant;
import utils.Leader;
import utils.Project;
import utils.Team;
import app.AppElement;
import app.domainCommands.AddWorkerToProjectInRepo;
import app.domainCommands.exceptions.IllegalWorkerTypeException;
import app.domainCommands.exceptions.NoSuchManagerException;
import app.domainCommands.exceptions.NoSuchProjectException;
import app.domainCommands.exceptions.NoSuchWorkerException;
import app.domainCommands.exceptions.WorkerNotAddedException;
import app.elements.Message;
import app.repository.ProjectsRepository;
import app.repository.UserRepository;
import app.repository.WorkerRepository;

/**
 * Class whose instances are commands that add {@link AWorker}s to
 * {@code Project}s.
 * Caller {@code String}: POST /project/{pid}/{type} {parameter list}
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class PostWorkerInProject extends BaseCommandUserAuthentication{

	/**
	 * {@code String} with the {@code Project}ID argument's name. The
	 * {@link CommandParser.Node.content} to be used (between "{" and "}", see
	 * {@link CommandParser#Node#isPlaceHolderNode()}) in one of the
	 * {@link CommandParser#Node}s of this command's Path.
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
	private String projectId;

	/**
	 * {@code String} with the {@code AWorker}'s Type argument. This argument is
	 * used for adding the right type of {@code AWorker} to the {@code Project}.
	 */
	private String typeWorker;

	/**
	 * {@code long} with the argument {@code AWorker}ID. This argument is used
	 * for getting the {@code AWorker} from the {@code WorkerRepository}.
	 */
	private String workerId;

	/**
	 * Class that implements the {@link PostWorkerInProject} factory, according to
	 * the {@link CommandFactory}.
	 */
	public static class Factory implements CommandFactory{
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
		 * @param uRepository  The {@code UserRepository} with the {@code User}.
		 * @param pRepository  The {@code ProjectRepository} with the {@code Project}.
		 * @param wRepository  The {@code WorkerRepository} with the {@code AWorker}.
		 */
		public Factory(UserRepository uRepository,
				ProjectsRepository pRepository, WorkerRepository wRepository){
			this.pRepository = pRepository;
			this.wRepository = wRepository;
			this.uRepository = uRepository;
		}

		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Callable<Result> newInstance(Map<String, String> parameters)	{
			return new PostWorkerInProject(uRepository, pRepository,
					wRepository, parameters);
		}
	}

	/**
	 * The constructor for {@code PostWorkerInProject}.
	 * 
	 * @param uRepository  The {@code UserRepository}.
	 * @param pRepository  The {@code ProjectRepository}.
	 * @param wRepository  The {@code WorkerRepository}.
	 * @param parameters   The Command arguments.
	 */
	public PostWorkerInProject(UserRepository uRepository,
			ProjectsRepository pRepository, WorkerRepository wRepository,
			Map<String, String> parameters){
		super(uRepository, parameters);
		this.projectRepository = pRepository;
		this.workerRepository = wRepository;
	}

	/**
	 * @see app.commands.BaseCommand#getMandatoryParameters()
	 */
	@Override
	protected String[] getMandatoryParameters(){
		return DEMANDING_PARAMETERS;
	}

	/**
	 * Adds the {@code AWorker} with the {@code AWorker}ID (
	 * {@link PostWorkerInProject#workerId}; if the {@code AWorker} exists) to
	 * the {@code Project} with the PID {@link PostWorkerInProject#projectId}
	 * (if the {@code Project} exists). 
	 * If {@link PostWorkerInProject#typeWorker} indicates Manager, then this
	 * {@code Command} adds {@code AWorker} as a Manager ( {@link Leader};
	 * {@link Project#manager}). 
	 * If indicates Consultant adds {@code AWorker} as
	 * a {@link Consultant} in the {@link Team} of the {@code Project} (
	 * {@link Project#team}). Outputs True if successful, False if not.
	 * 
	 * @see PostWorkerInProject#addConsultant(long, long)
	 * @see PostWorkerInProject#addManager(long, long)
	 * @see BaseCommandUserAuthentication#internalCall()
	 */
	@Override
	protected AppElement[] internalCall(){
		this.projectId = getParameterAsString(PID);
		this.typeWorker = getParameterAsString(WTYPE);
		this.workerId = getParameterAsString(CID);

		AppElement[] element;
		try{
			element = new AddWorkerToProjectInRepo(projectRepository, workerRepository, 
						projectId, workerId, typeWorker).call();
		} catch (WorkerNotAddedException e) {
			return new AppElement[]{new Message("Could not add the worker to the project. That worker may already be assigned to the project.")};
		} catch(NoSuchProjectException e) {
			return new AppElement[]{new Message("The Specified Project does not exists in repository.")};
		} catch(IllegalWorkerTypeException e) {
			return new AppElement[]{new Message("Unrecognised type of worker.")};
		} catch(NoSuchManagerException e) {
			return new AppElement[]{new Message("The Specified Manager does not exist.")};
		} catch(NoSuchWorkerException e) {
			return new AppElement[]{new Message("The Specified Consultant does not exist.")};
		}

		return element;
	}
}
