package parserCommands;

import java.util.Map;
import java.util.concurrent.Callable;

import outputMethods.Result;
import utils.Project;
import app.AppElement;
import app.domainCommands.GetProjectFromRepo;
import app.domainCommands.exceptions.NoSuchProjectException;
import app.elements.Message;
import app.repository.ProjectsRepository;
import commandParser.CommandParser;

/**
 * Class whose instances are commands that return the {@link Project} with specified {@code PID}
 * Caller {@code String}: GET /project/{pid}
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class GetProjects extends BaseCommandResultsOutputMethod{

	/**
	 * The {@link ProjectsRepository} with the {@code Project}s. This
	 * {@code ProjectRepository} is accessed to get the {@code Project}.
	 */
	private final ProjectsRepository repository;

	/**
	 * {@code String} with the {@code Project}ID argument's name. The
	 * {@link CommandParser.Node.content} to be used (between "{" and "}", see
	 * {@link CommandParser#Node#isPlaceHolderNode()}) in one of the
	 * {@link CommandParser#Node}s of this {@code Command}'s Path.
	 */
	public static final String PID = "pid";

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
		private final ProjectsRepository repository;

		/**
		 * The constructor for {@code Factory}.
		 * 
		 * @param repository    The {@code ProjectRepository} with the {@code Project}.
		 */
		public Factory(ProjectsRepository repository){
			this.repository = repository;
		}

		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Callable<Result> newInstance(Map<String, String> parameters)	{
			return new GetProjects(repository, parameters);
		}
	}

	/**
	 * The constructor for {@code GetProject}.
	 * 
	 * @param repository    The {@code ProjectRepository}.
	 * @param parameters    The Command arguments.
	 */
	public GetProjects(ProjectsRepository repository,
			Map<String, String> parameters){
		super(parameters);
		this.repository = repository;
	}

	/**
	 * @see app.commands.BaseCommand#getMandatoryParameters()
	 */
	@Override
	protected String[] getMandatoryParameters(){
		return DEMANDING_PARAMETERS;
	}

	/**
	 * Gets the {@code Project} with the argument
	 * PID stored in {@link GetProjects#parameters} 
	 * 
	 * @return An array of {@code DatabaseElement} with one element carrying the 
	 * {@code Project}
	 */
	@Override
	protected AppElement[] internalCall() throws Exception{
		
		AppElement[] project;
		
		try{
			project = new GetProjectFromRepo(repository, getParameterAsString(PID)).call();
		} catch(NoSuchProjectException e ) {
			return new AppElement[]{new Message("Project not found!")};
		}

		return project;
	}
}
