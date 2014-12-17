package app.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import utils.Project;
import app.commands.exceptions.CommandException;
import app.elements.DatabaseElement;
import app.repository.ProjectRepository;
import app.repository.UserRepository;
import app.resultsOutputMethods.ResultOutputMethod;

/**
 * Class whose instances are {@link Command}s that deletes {@link Project}s and
 * their Sub{@code Project}s.
 * 
 * Caller {@code String}: DELETE /projects/{pid} {parameter list}
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 17/12/2014
 */
public class DeleteProject extends BaseCommandAuthentication
{

	/**
	 * The {@link ProjectRepository} with the {@code Project}s. This
	 * {@code ProjectRepository} is accessed to get the {@code Project}s (and
	 * Sub{@code Project}s) to be deleted.
	 */
	private final ProjectRepository repository;

	/**
	 * {@code String} with the {@code Project}ID argument's name. The
	 * {@link CommandParser.Node.content} to be used (between "{" and "}", see
	 * {@link CommandParser#Node#isPlaceHolderNode()}) in the
	 * {@link CommandParser#Node} that has the {@code DeleteProject#Factory} in
	 * the field {@link CommandParser#Node#factory}.
	 */
	public static final String PID = "pid";

	/**
	 * An array of {@code String}s with the names of all mandatory arguments.
	 */
	public static final String[] DEMANDING_PARAMETERS = new String[] { PID };

	/**
	 * Class that implements the {@code DeleteProject} factory, according to the
	 * {@link CommandFactory}.
	 */
	public static class Factory implements CommandFactory
	{

		/**
		 * The {@link ProjectRepository} with the {@code Project}s. This
		 * {@code ProjectRepository} is accessed to get the {@code Project}s
		 * (and Sub{@code Project}s) to be deleted.
		 */
		private final ProjectRepository pRepository;

		/**
		 * @see BaseCommandAuthentication#repository
		 */
		private final UserRepository uRepository;

		/**
		 * The constructor for {@code Factory}.
		 * 
		 * @param uRepository
		 *            The {@code UserRepository} with the {@code User}.
		 * @param pRepository
		 *            The {@code ProjectRepository} with the {@code Project}s.
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
	 * The constructor for {@code DeleteProject}.
	 * 
	 * @param uRepository
	 *            The {@code UserRepository}.
	 * @param repository
	 *            The {@code ProjectRepository}.
	 * @param parameters
	 *            The {@code Command} arguments.
	 */
	public DeleteProject(UserRepository uRepository,
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

	@Override
	protected void internalPostExecute(ResultOutputMethod out)
			throws CommandException, IOException
	{
		long pid = this.getParameterAsLong(PID);
		List<Long> allPIDsToDelete = new ArrayList<Long>();
		allPIDsToDelete = this.getAllPIDsToDelete(pid, allPIDsToDelete);

		this.removeAllProjectsToDeleteFromTheRepository(allPIDsToDelete);
		this.removeAllProjectsToDeleteFromAllProjectContainers(allPIDsToDelete);

		out.giveResults("Done.");
	}

	private void removeAllProjectsToDeleteFromTheRepository(
			List<Long> allPIDsToDelete)
	{
		for (long pidToDelete : allPIDsToDelete)
		{
			Project projectToDelete = repository.getProjectById(pidToDelete);
			this.repository.removeProject(projectToDelete);
		}
	}

	private void removeAllProjectsToDeleteFromAllProjectContainers(
			List<Long> allPIDsToDelete)
	{
		for (DatabaseElement project : this.repository.getAll())
			for (Long pidToDelete : allPIDsToDelete)
				((Project) project).removeProject(this.repository
						.getProjectById(pidToDelete).getName());
	}

	private List<Long> getAllPIDsToDelete(long pid, List<Long> allPIDsToDelete)
	{
		allPIDsToDelete.add(pid);

		for (Project projectToDelete : repository.getProjectById(pid)
				.getContainerProject())
			allPIDsToDelete = this.getAllPIDsToDelete(projectToDelete.getPID(),
					allPIDsToDelete);

		return allPIDsToDelete;
	}

}
