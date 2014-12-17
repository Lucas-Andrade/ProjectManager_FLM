package app.commands;

import java.io.IOException;
import java.util.Map;

import app.commands.exceptions.CommandException;
import app.repository.ProjectRepository;
import app.repository.UserRepository;
import app.resultsOutputMethods.ResultOutputMethod;

public class DeleteProject extends BaseCommandAuthentication{

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
		private final ProjectRepository pRepository;
		
		private final UserRepository uRepository;

		/**
		 * The constructor for {@code Factory}.
		 * 
		 * @param repository
		 *            The {@code ProjectRepository} with the {@code Project}.
		 */
		public Factory(UserRepository uRepository, ProjectRepository pRepository)
		{
			this.pRepository = pRepository;
			this.uRepository = uRepository;
		}

		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Command newInstance(Map<String, String> parameters)
		{
			return new DeleteProject(uRepository, pRepository, parameters);
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
	public DeleteProject(UserRepository uRepository, ProjectRepository repository, Map<String, String> parameters)
	{
		super(uRepository, parameters);
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


	@Override
	protected void internalPostExecute(ResultOutputMethod out)
			throws CommandException, IOException {
		// TODO Auto-generated method stub
		
	}

}
