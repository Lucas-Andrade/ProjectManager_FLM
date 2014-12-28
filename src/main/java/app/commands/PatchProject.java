package app.commands;

import java.util.Map;
import java.util.concurrent.Callable;

import utils.Project;
import app.commandParser.CommandParser;
import app.commands.exceptions.InvalidParameterValueException;
import app.elements.DatabaseElement;
import app.repository.ProjectsRepository;
import app.repository.UserRepository;

public class PatchProject extends BaseCommandUserAuthentication{

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
	public static class Factory implements CommandFactory
	{

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
		public Factory(UserRepository uRepository, ProjectsRepository pRepository)
		{
			this.pRepository = pRepository;
			this.uRepository = uRepository;
		}

		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Callable<Result> newInstance(Map<String, String> parameters)
		{
			return new PatchProject(uRepository, pRepository, parameters);
		}
	}
	
	
	/**
	 * The constructor for {@code GetProject}.
	 * 
	 * @param repository
	 *            The {@code ProjectRepository}.
	 * @param parameters
	 *            The {@code Command} arguments.
	 */
	public PatchProject(UserRepository uRepository, ProjectsRepository pRepository, Map<String, String> parameters)
	{
		super(uRepository, parameters);
		this.pRepository = pRepository;
	}


	@Override
	protected DatabaseElement internalCall()
			throws Exception {

		Project project = pRepository.getProjectById(getParameterAsLong(PID));
		
		if(project == null)
		{
			throw new InvalidParameterValueException("Project not found!");
		}
		
		if (parameters.containsKey(LONGITUDE))
		{
			if( ! project.updateLongitude(getParameterAsDouble(LONGITUDE)))
				throw new InvalidParameterValueException("Longitude out of bounds.");
		}
		
		if(parameters.containsKey(LATITUDE))
		{
			if( ! project.updateLatitude(getParameterAsDouble(LATITUDE)))
				throw new InvalidParameterValueException("Latitude out of bounds.");
		}
		
		if(parameters.containsKey(NAME))
		{
			project.updateLocalName(getParameterAsString(NAME));
		}
		
		if(parameters.containsKey(PRICE))
		{
			if( ! project.updateLocalPrice(getParameterAsDouble(PRICE)))
				throw new InvalidParameterValueException("A negative price is not allowed.");
		}
		
		return project;
	}


	@Override
	protected String[] getMandatoryParameters() {
		return DEMANDING_PARAMETERS;
	}

}
