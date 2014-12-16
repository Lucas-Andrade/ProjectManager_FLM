package app.commands;

import java.io.IOException;
import java.util.Map;

import utils.Project;
import app.commands.exceptions.CommandException;
import app.repository.ProjectRepository;
import app.repository.UserRepository;
import app.resultsOutputMethods.ResultOutputMethod;

/**
 * Class whose instances are {@link Command}s that add Sub{@link Project}s to
 * {@code Project}s. A Sub{@code Project} is a {@code Project} inside (that
 * belongs to) another {@code Project}.
 * Caller {@code String}: POST /project/{pid}/subproject {parameter list}
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class PostSubproject extends BasePostCommand
{

	/**
	 * The {@link ProjectRepository} with the {@code Project}s. Sub
	 * {@code Project}s are {@code Project}s. This {@code ProjectRepository} is
	 * accessed to get the {@code Project}s where the Sub{@code Project}s are
	 * going to be added, and is accessed to get the Sub{@code Project}s.
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
	 * {@code String} with the Sub{@code Project}ID argument's name.
	 */
	public static final String SUBPID = "subPid";

	/**
	 * An array of {@code String}s with the names of all mandatory arguments.
	 */
	public static final String[] DEMANDING_PARAMETERS = new String[] { PID,
			SUBPID };

	/**
	 * Class that implements the {@link PostSubproject} factory, according to
	 * the {@link CommandFactory}.
	 */
	public static class Factory implements CommandFactory
	{

		/**
		 * The {@link ProjectRepository} with the {@code Project}s. Sub
		 * {@code Project}s are {@code Project}s. This {@code ProjectRepository}
		 * is accessed to get the {@code Project}s where the Sub{@code Project}s
		 * are going to be added, and is accessed to get the Sub{@code Project}
		 * s.
		 */
		private final ProjectRepository repository;

		/**
		 * @see BasePostCommand#repository
		 */
		private final UserRepository uRepository;

		/**
		 * The constructor for {@code Factory}.
		 * 
		 * @param uRepository
		 *            The {@code UserRepository} with the {@code User}.
		 * @param repository
		 *            The {@code ProjectRepository} with the {@code Project}.
		 */
		public Factory(UserRepository uRepository, ProjectRepository repository)
		{
			this.repository = repository;
			this.uRepository = uRepository;
		}

		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Command newInstance(Map<String, String> parameters)
		{
			return new PostSubproject(uRepository, repository, parameters);
		}
	}

	/**
	 * The constructor for {@code PostSubproject}.
	 * 
	 * @param uRepository
	 *            The {@code UserRepository}.
	 * @param repository
	 *            The {@code ProjectRepository}.
	 * @param parameters
	 *            The {@code Command} arguments.
	 */
	public PostSubproject(UserRepository uRepository,
			ProjectRepository repository, Map<String, String> parameters)
	{
		super(uRepository, parameters);
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
	 * Adds a Sub{@code Project} to a {@code Project} if both {@code Project}s
	 * have different IDs (if they are not the same) and have different Names
	 * and if both exist. Outputs a successful message if successful and
	 * vice-versa.
	 * 
	 * @see BasePostCommand#internalPostExecute(ResultOutputMethod)
	 */
	@Override
	protected void internalPostExecute(ResultOutputMethod out)
			throws CommandException, IOException
	{
		long pid = getParameterAsLong(PID);
		long subPid = getParameterAsLong(SUBPID);

		if (pid == subPid)
		{
			out.giveResults("Specified project identifications are equal!");
			return;
		}

		Project project = repository.getProjectById(pid);
		if (project == null)
		{
			out.giveResults("Specified project does not exist.");
			return;
		}

		Project subProject = repository.getProjectById(subPid);
		if (subProject == null)
		{
			out.giveResults("Specified subproject does not exist.");
			return;
		}

		if (project.addProject(subProject))
			out.giveResults("Success.");
		else
			out.giveResults("Could not add subproject to project, because both have the same name.");
	}

}
