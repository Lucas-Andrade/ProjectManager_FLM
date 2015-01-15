package commands;

import java.util.Map;
import java.util.concurrent.Callable;

import outputMethods.Result;
import utils.Project;
import app.AppElement;
import app.elements.Message;
import app.repository.ProjectsRepository;
import app.repository.UserRepository;

/**
 * Class whose instances are commands that modifies {@link Project}s.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 05/01/2015
 */
public class PatchProject extends BaseCommandUserAuthentication {

	/**
	 * The {@link ProjectsRepository} with the {@code Project}s. This
	 * {@code ProjectRepository} is accessed to get the {@code Project}.
	 */
	private final ProjectsRepository pRepository;

	/**
	 * {@code String} with the {@code Project}ID argument's name. The
	 * {@link CommandParser.Node.content} to be used (between "{" and "}", see
	 * {@link CommandParser#Node#isPlaceHolderNode()}) in one of the
	 * {@link CommandParser#Node}s of this {@code Command}'s Path.
	 */
	public static final String PID = "pid";

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
	public static final String[] DEMANDING_PARAMETERS = new String[] { PID };

	/**
	 * Class that implements the {@code GetSubproject} factory, according to the
	 * {@link CommandFactory}.
	 */
	public static class Factory implements CommandFactory{

		/**
		 * The {@link ProjectsRepository} with the {@code Project}s. This
		 * {@code ProjectRepository} is accessed to get the {@code Project}
		 */
		private final ProjectsRepository pRepository;

		private final UserRepository uRepository;

		/**
		 * The constructor for {@code Factory}.
		 * 
		 * @param repository
		 *            The {@code ProjectRepository} with the {@code Project}.
		 */
		public Factory(UserRepository uRepository,
				ProjectsRepository pRepository){
			this.pRepository = pRepository;
			this.uRepository = uRepository;
		}

		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Callable<Result> newInstance(Map<String, String> parameters){
			return new PatchProject(uRepository, pRepository, parameters);
		}
	}

	/**
	 * The constructor for {@code GetProject}.
	 * 
	 * @param repository
	 *            The {@code ProjectRepository}.
	 * @param parameters
	 *            The command arguments.
	 */
	public PatchProject(UserRepository uRepository,
			ProjectsRepository pRepository, Map<String, String> parameters){
		super(uRepository, parameters);
		this.pRepository = pRepository;
	}

	/**
	 * Modifies the information stored in the parameters of the {@code Project}
	 * with the specified ID.
	 * 
	 * @see BaseCommandUserAuthentication#internalCall()
	 */
	@Override
	protected AppElement[] internalCall() throws Exception{

		Project project = pRepository.getProjectById(getParameterAsLong(PID));
		AppElement[] messageAux = new AppElement[1];

		if (project == null){
			messageAux[0] = new Message("Project not found!");
			return messageAux;
		}
		
		AppElement patch = patchParameters(project);
		if(patch != null){
			messageAux[0] = patch;
			return messageAux;
		}
		
		messageAux[0] = new Message("The Project parameters were successfully changed!");
		return messageAux;
	}
	
	/**
	 * Verifies which parameters of the {@code Project} were parsed into the parameter's {@code Map},
	 * and updates the {@code Project} accordingly. If any parameter is out of bounds a {@code Message}
	 * will be returned.
	 * @param project
	 * @return A {@code Message} if a parameter is out of bounds
	 * @return {@code null} if no {@code Message} was needed 
	 */
	private AppElement patchParameters(Project project){
		if (parameters.containsKey(NAME)){
			project.updateLocalName(getParameterAsString(NAME));
		}
		return checkParametersToPatch(project);
	}
	
	/**
	 * Verifies any parameter of the {@code Project} that could be out of bounds was parsed 
	 * to the parameters {@code Map}. If any parameter is out of bounds a {@code Message}
	 * will be returned.
	 * @param project
	 * @return A {@code Message} if a parameter is out of bounds
	 * @return {@code null} if no {@code Message} was needed 
	 */
	private AppElement checkParametersToPatch(Project project){
		if (parameters.containsKey(LONGITUDE) && 
				!project.updateLongitude(getParameterAsDouble(LONGITUDE))){
			return new Message("Longitude out of bounds.");
		}
	
		if (parameters.containsKey(LATITUDE) && 
					!project.updateLatitude(getParameterAsDouble(LATITUDE))){
			return new Message("Latitude out of bounds.");
		}
	
		if (parameters.containsKey(PRICE) &&
					!project.updateLocalPrice(getParameterAsDouble(PRICE))){
			return new Message("A negative price is not allowed.");
		}
		
		return null;
	}

	/**
	 * @see app.commands.BaseCommand#getMandatoryParameters()
	 */
	@Override
	protected String[] getMandatoryParameters(){
		return DEMANDING_PARAMETERS;
	}
}
