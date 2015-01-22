package parser_commands;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;

import output_methods.Result;
import parser_commands.exceptions.CommandException;
import utils.Project;
import app.AppElement;
import app.domain_commands.AddSubprojectToRepo;
import app.domain_commands.exceptions.AddedExistingSubproject;
import app.domain_commands.exceptions.NoSuchProjectException;
import app.domain_commands.exceptions.ProjectAddedToItselfException;
import app.elements.Message;
import app.repository.ProjectsRepository;
import app.repository.UserRepository;

/**
 * Class whose instances are commands that add {@link Project}s as
 * subprojects to other {@code Project}s. A Sub{@code Project} is a
 * {@code Project} inside (that belongs to) another {@code Project}.
 * 
 * Caller {@code String}: POST /project/{pid}/subproject {parameter list}
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class PostSubprojects extends BaseCommandUserAuthentication {

	/**
	 * The {@link ProjectsRepository} with the {@code Project}s. Sub
	 * {@code Project}s are {@code Project}s. This {@code ProjectRepository} is
	 * accessed to get the {@code Project}s where the Sub{@code Project}s are
	 * going to be added, and is accessed to get the Sub{@code Project}s.
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
	 * {@code String} with the Sub{@code Project}ID argument's name.
	 */
	public static final String SUBPID = "subPid";

	/**
	 * An array of {@code String}s with the names of all mandatory arguments.
	 */
	public static final String[] DEMANDING_PARAMETERS = new String[] { PID,
			SUBPID };

	/**
	 * Class that implements the {@link PostSubprojects} factory, according to
	 * the {@link CommandFactory}.
	 */
	public static class Factory implements CommandFactory{

		/**
		 * The {@link ProjectsRepository} with the {@code Project}s. Sub
		 * {@code Project}s are {@code Project}s. This {@code ProjectRepository}
		 * is accessed to get the {@code Project}s where the Sub{@code Project}s
		 * are going to be added, and is accessed to get the Sub{@code Project}
		 * s.
		 */
		private final ProjectsRepository repository;

		/**
		 * @see BaseCommandUserAuthentication#repository
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
		public Factory(UserRepository uRepository, ProjectsRepository repository){
			this.repository = repository;
			this.uRepository = uRepository;
		}

		/**
		 * @see CommandFactory#newInstance(Map)
		 */
		@Override
		public Callable<Result> newInstance(Map<String, String> parameters){
			return new PostSubprojects(uRepository, repository, parameters);
		}
	}

	/**
	 * The constructor for {@code PostSubproject}.
	 * 
	 * @param uRepository   The {@code UserRepository}.
	 * @param repository    The {@code ProjectRepository}.
	 * @param parameters    The {@code Command} arguments.
	 */
	public PostSubprojects(UserRepository uRepository,
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
	 * Adds a {@code Project} as a subproject of another {@code Project} if both
	 * {@code Project}s have different IDs (if they are not the same), if the
	 * {@code Project} to be added as subproject isn't a subproject and if both
	 * {@code Project}s exist. Outputs a successful message if successful and
	 * vice-versa.
	 * 
	 * @see BaseCommandUserAuthentication#internalCall()
	 */
	@Override
	protected AppElement[] internalCall()
			throws CommandException, IOException {
		String pid = getParameterAsString(PID);
		String subPid = getParameterAsString(SUBPID);
		
		try{
			new AddSubprojectToRepo(repository, pid, subPid).call();
		} catch(ProjectAddedToItselfException e) {
			return new AppElement[]{ new Message("Specified project identifications are equal!")};
		} catch(AddedExistingSubproject e) {
			return new AppElement[]{ new Message("Could not add subproject to project, because "
					+ "subproject already is a subproject (of this or another project).")};
		} catch(NoSuchProjectException e) {
			return new AppElement[]{ new Message("At least one of the specified projects to not exist.")};
		}
		
		return new AppElement[]{ new Message("Success.")};
	}
}
