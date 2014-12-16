package app.commands;

import java.io.IOException;
import java.util.Map;

import app.commands.exceptions.CommandException;
import app.repository.ProjectRepository;
import app.resultsOutputMethods.ResultOutputMethod;

public class DeleteProject extends BaseCommand{

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
			return new DeleteProject(repository, parameters);
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
	public DeleteProject(ProjectRepository repository,Map<String, String> parameters)
	{
		super(parameters);
		this.repository = repository;
	}


	@Override
	protected void internalExecute(ResultOutputMethod out)
			throws CommandException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String[] getMandatoryParameters() {
		// TODO Auto-generated method stub
		return null;
	}

}
