package commands;

import java.util.Map;
import java.util.concurrent.Callable;

import outputMethods.Result;
import utils.Project;
import app.AppElement;
import app.commands.RemoveProjectToRepo;
import app.commands.exceptions.NoSuchProjectException;
import app.elements.Message;
import app.repository.ProjectsRepository;
import app.repository.UserRepository;

/**
 * Class whose instances are {@link Command}s that deletes {@link Project}s and
 * their Sub{@code Project}s.
 * 
 * Caller {@code String}: DELETE /projects/{pid} {parameter list}
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 17/12/2014
 */
public class DeleteProjects extends BaseCommandUserAuthentication{

	/**
	 * The {@link ProjectsRepository} with the {@code Project}s. This
	 * {@code ProjectRepository} is accessed to get the {@code Project}s (and
	 * Sub{@code Project}s) to be deleted.
	 */
	private final ProjectsRepository repository;

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
	public static class Factory implements CommandFactory{
		/**
		 * The {@link ProjectsRepository} with the {@code Project}s. This
		 * {@code ProjectRepository} is accessed to get the {@code Project}s
		 * (and Sub{@code Project}s) to be deleted.
		 */
		private final ProjectsRepository pRepository;

		/**
		 * @see BaseCommandUserAuthentication#repository
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
			return new DeleteProjects(uRepository, pRepository, parameters);
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
	public DeleteProjects(UserRepository uRepository,
			ProjectsRepository repository, Map<String, String> parameters){
		super(uRepository, parameters);
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
	 * Deletes a {@code Project} and all it's subprojects, and their own
	 * subprojects and so on.
	 * 
	 * @return An array of {@code DatabaseElement} with one element carrying a
	 *         {@code Message} with the success of unsuccess of the operation.
	 * 
	 * @see BaseCommandUserAuthentication#internalCall()
	 */
	@Override
	protected AppElement[] internalCall() throws Exception{
		String pid = this.getParameterAsString(PID);

		try{
			new RemoveProjectToRepo(repository, pid).call();
		} catch( NoSuchProjectException e) {
			return new AppElement[]{new Message("Project does not exist.")};
		}
		return new AppElement[]{new Message("Project successfully deleted.")};
	}

}
