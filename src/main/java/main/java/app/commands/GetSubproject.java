package main.java.app.commands;

import java.io.IOException;
import java.util.Map;

import main.java.app.commands.exceptions.CommandException;
import main.java.app.repository.ProjectRepository;
import main.java.app.resultsOutputMethods.ResultOutputMethod;
import main.java.utils.Project;

/**
 * Class whose instances are {@link Command}s that return the Sub{@link Project}
 * s in a {@link Project}.
 * Caller {@code String}: GET /project/{pid}/subproject
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class GetSubproject extends BaseCommand
{

	/**
	 * The {@link ProjectRepository} with the {@code Project}s. This
	 * {@code ProjectRepository} is accessed to get the {@code Project} with the
	 * wanted Sub{@code Project}s.
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
		 * {@code ProjectRepository} is accessed to get the {@code Project} with
		 * the wanted Sub{@code Project}s.
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
			return new GetSubproject(repository, parameters);
		}
	}

	/**
	 * The constructor for {@code GetSubproject}.
	 * 
	 * @param repository
	 *            The {@code ProjectRepository}.
	 * @param parameters
	 *            The {@code Command} arguments.
	 */
	public GetSubproject(ProjectRepository repository,
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
	 * Get's the Sub{@code Project}s from the {@code Project} with the argument
	 * PID stored in {@link GetSubproject#parameters} (argument's name is
	 * {@link GetSubproject#PID}), if the {@code Project} exists and has at
	 * least one Sub{@code Project}).
	 * 
	 * @see BaseCommand#internalExecute(ResultOutputMethod)
	 */
	@Override
	protected void internalExecute(ResultOutputMethod out)
			throws CommandException, IOException
	{
		Project project = repository.getProjectById(getParameterAsLong(PID));
		out.giveResults(project.getContainerProject());
	}

}