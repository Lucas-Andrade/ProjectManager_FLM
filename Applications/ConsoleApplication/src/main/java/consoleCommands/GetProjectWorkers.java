package parserCommands;

import java.util.Map;
import java.util.concurrent.Callable;

import outputMethods.Result;
import utils.AWorker;
import utils.Consultant;
import utils.Leader;
import utils.Project;
import utils.Team;
import app.AppElement;
import app.domainCommands.GetProjectWorkersFromRepo;
import app.domainCommands.exceptions.IllegalWorkerTypeException;
import app.domainCommands.exceptions.NoManagerInProjectException;
import app.domainCommands.exceptions.NoSuchProjectException;
import app.domainCommands.exceptions.NoWorkersFoundException;
import app.elements.Message;
import app.repository.ProjectsRepository;

/**
 * Class whose instances are {@link Command}s that return the {@link AWorker}s
 * in a {@link Project}.
 * Caller {@code String}: GET /project/{pid}/{type}
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class GetProjectWorkers extends BaseCommandResultsOutputMethod {

	/**
	 * The {@link ProjectsRepository} with the {@code Project}s. This
	 * {@code ProjectRepository} is accessed to get the {@code AWorker}s from an
	 * existing {@code Project}.
	 */
	private final ProjectsRepository projectRepository;

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
	private String projectId;

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
	public static class Factory implements CommandFactory{

		/**
		 * The {@link ProjectsRepository} with the {@code Project}s. This
		 * {@code ProjectRepository} is accessed to get the {@code AWorker}s
		 * from an existing {@code Project}.
		 */
		private final ProjectsRepository pRepository;

		/**
		 * The constructor for {@code Factory}.
		 * 
		 * @param pRepository  The {@code ProjectRepository} with the {@code Project}.
		 */
		public Factory(ProjectsRepository pRepository){
			this.pRepository = pRepository;
		}

		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Callable<Result> newInstance(Map<String, String> parameters){
			return new GetProjectWorkers(pRepository, parameters);
		}
	}

	/**
	 * The constructor for {@code GetProjectWorkers}.
	 * 
	 * @param pRepository  The {@code ProjectRepository}.
	 * @param parameters   The {@code Command} arguments.
	 */
	private GetProjectWorkers(ProjectsRepository pRepository,
			Map<String, String> parameters){
		super(parameters);
		this.projectRepository = pRepository;
	}

	/**
	 * @see app.commands.BaseCommand#getMandatoryParameters()
	 */
	@Override
	protected String[] getMandatoryParameters(){
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
	 * @return An array of {@code DatabaseElement} with one element carrying 
	 * the {@code AWorker}
	 * 
	 * @see BaseCommandResultsOutputMethod#internalCall()
	 */
	@Override
	protected AppElement[] internalCall() {
		
		projectId = getParameterAsString(PID);
		typeWorker = getParameterAsString(WTYPE);
		AppElement[] toReturn = null;
		
		try{
			toReturn = new GetProjectWorkersFromRepo(projectRepository, projectId, typeWorker).call();
		} catch(NoSuchProjectException e) {
			return new AppElement[]{new Message("Project with ID: " + projectId 
					+ " was not found!")};
		} catch(IllegalWorkerTypeException e) {
			new Message("Unrecognised type of worker.");
		} catch(NoManagerInProjectException e) {
			return new AppElement[]{new Message("Project with ID: " + projectId 
					+ " has no manager.")};
		} catch(NoWorkersFoundException e) {
			return new AppElement[]{new Message("Project with ID: " + projectId 
					+ " has no assigned workers.")};
		}
		
		return toReturn;
	}
}
