package app.commands;

import java.util.Map;
import java.util.concurrent.Callable;

import utils.Project;
import app.commandParser.CommandParser;
import app.elements.DatabaseElement;
import app.repository.ProjectsRepository;
import app.resultsAndOutputMethods.Result;

/**
 * Class whose instances are {@link Command}s that return the {@link Project} with specified {@code PID}
 * Caller {@code String}: GET /project/{pid}
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class GetProjects extends BaseCommandResultsOutputMethod
{

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
	public static class Factory implements CommandFactory
	{

		/**
		 * The {@link ProjectsRepository} with the {@code Project}s. This
		 * {@code ProjectRepository} is accessed to get the {@code Project}
		 */
		private final ProjectsRepository repository;

		/**
		 * The constructor for {@code Factory}.
		 * 
		 * @param repository
		 *            The {@code ProjectRepository} with the {@code Project}.
		 */
		public Factory(ProjectsRepository repository)
		{
			this.repository = repository;
		}

		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Callable<Result> newInstance(Map<String, String> parameters)
		{
			return new GetProjects(repository, parameters);
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
	public GetProjects(ProjectsRepository repository,
			Map<String, String> parameters)
	{
		super(parameters);
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
	 * Gets the {@code Project} with the argument
	 * PID stored in {@link GetProjects#parameters} 
	 * 
	 * @see BaseCommandResultsOutputMethod#internalExecuteAfterDefiningTheOutputMethodForResults(ResultOutputMethod)
	 */
	@Override
	protected DatabaseElement[] internalCall() throws Exception
	{
		Project project = repository.getProjectById(getParameterAsLong(PID));
		
		if(project == null)
		{
			throw new NullPointerException("Project not found!");
		}
		DatabaseElement[] projectAux = {project};
		return projectAux;
	}

}
