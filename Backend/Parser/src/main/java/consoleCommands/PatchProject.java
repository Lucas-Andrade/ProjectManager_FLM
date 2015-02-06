package consoleCommands;

import java.util.Map;
import java.util.concurrent.Callable;

import outputMethods.Result;
import parser.CommandParser;
import parserUtils.CommandFactory;
import utils.Project;
import app.AppElement;
import app.domainCommands.SetProjectPropertiesFromRepo;
import app.domainCommands.exceptions.CostOutOfBoundsException;
import app.domainCommands.exceptions.GeographicCoordinatesOutOfBoundsException;
import app.domainCommands.exceptions.NoSuchProjectException;
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
	protected AppElement[] internalCall() {
		
		try{
			new SetProjectPropertiesFromRepo(pRepository, getParameterAsString(PID),
				getParameterAsString(LONGITUDE), getParameterAsString(LATITUDE), getParameterAsString(PRICE), 
				getParameterAsString(NAME)).call();
		} catch(NoSuchProjectException e) {
			return new AppElement[]{new Message("Project not found!")};
		} catch(GeographicCoordinatesOutOfBoundsException e) {
			return new AppElement[]{new Message("Longitude and/or latitude out of bounds.")};
		} catch(CostOutOfBoundsException e) {
			return new AppElement[]{new Message("A negative price is not allowed.")};
		}
		
		return new AppElement[]{new Message("The Project parameters were successfully changed!")};
	}

	/**
	 * @see app.commands.BaseCommand#getMandatoryParameters()
	 */
	@Override
	protected String[] getMandatoryParameters(){
		return DEMANDING_PARAMETERS;
	}
}
