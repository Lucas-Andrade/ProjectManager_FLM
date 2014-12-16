package main.java.app.commands;

import java.io.IOException;
import java.util.Map;

import main.java.app.commands.exceptions.CommandException;
import main.java.app.repository.ProjectRepository;
import main.java.app.resultsOutputMethods.ResultOutputMethod;
import main.java.utils.Project;

/**
 * Class whose instances are {@link Command}s that return the {@link Project} with specified {@code PID}
 * Caller {@code String}: GET /project/{pid}
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class GetProject extends BaseCommand
{

	/**
	 * The {@link ProjectRepository} with the {@code Project}s. This
	 * {@code ProjectRepository} is accessed to get the {@code Project}.
	 */
	private final ProjectRepository repository;

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
		 * The {@link ProjectRepository} with the {@code Project}s. This
		 * {@code ProjectRepository} is accessed to get the {@code Project}
		 */
		private final ProjectRepository repository;

		/**
		 * The constructor for {@code Factory}.
		 * 
		 * @param repository
		 *            The {@code ProjectRepository} with the {@code Project}.
		 */
		public Factory(ProjectRepository repository)
		{
			this.repository = repository;
		}

		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Command newInstance(Map<String, String> parameters)
		{
			return new GetProject(repository, parameters);
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
	public GetProject(ProjectRepository repository,
			Map<String, String> parameters)
	{
		super(parameters);
		this.repository = repository;
	}

	/**
	 * @see main.java.app.commands.BaseCommand#getMandatoryParameters()
	 */
	@Override
	protected String[] getMandatoryParameters()
	{
		return DEMANDING_PARAMETERS;
	}

	/**
	 * Gets the {@code Project} with the argument
	 * PID stored in {@link GetProject#parameters} 
	 * 
	 * @see BaseCommand#internalExecute(ResultOutputMethod)
	 */
	@Override
	protected void internalExecute(ResultOutputMethod out)
			throws CommandException, IOException
	{
		Project project = repository.getProjectById(getParameterAsLong(PID));
		out.giveResults(project);
	}

}
