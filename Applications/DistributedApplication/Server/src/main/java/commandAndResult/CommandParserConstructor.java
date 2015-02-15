/**
 * 
 */
package commandAndResult;

import java.util.TreeMap;

import parser.CommandParser;
import parser.CommandParser.CommandsRegister;
import parserCommands.AuthenticateUser;
import parserCommands.DeleteProjects;
import parserCommands.GetProjectWorkers;
import parserCommands.GetProjects;
import parserCommands.GetSubproject;
import parserCommands.GetUser;
import parserCommands.GetUsers;
import parserCommands.PatchConsultant;
import parserCommands.PatchProject;
import parserCommands.PatchUser;
import parserCommands.PostConsultant;
import parserCommands.PostProjects;
import parserCommands.PostSubprojects;
import parserCommands.PostUsers;
import parserCommands.PostWorkerInProject;
import parserUtils.CommandFactory;
import parserUtils.CommandParserException;
import app.repository.ProjectsRepository;
import app.repository.UserRepository;
import app.repository.WorkerRepository;

/**
 * Class that implements {@link CommandsRegister}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 09/02/2015
 */
public class CommandParserConstructor implements CommandsRegister {

	/**
	 * A {@code Map} containing the factories of the commands to be registered.
	 */
	private volatile TreeMap<Integer, CommandFactory> cmdsMap;

	/**
	 * A {@code Map} containing the paths of the commands to be registered.
	 */
	private volatile TreeMap<Integer, String> pathsMap;

	/**
	 * A {@code Map} containing the methods of the commands to be registered.
	 */
	private volatile TreeMap<Integer, String> methodsMap;

	/**
	 * The constructor for this class.
	 */
	public CommandParserConstructor() {
		this.cmdsMap = new TreeMap<Integer, CommandFactory>();
		this.pathsMap = new TreeMap<Integer, String>();
		this.methodsMap = new TreeMap<Integer, String>();
	}

	/**
	 * Method for constructing the singleton instance of {@code CommandParser}.
	 * 
	 * @param userRepo
	 *            The {@code UserRepository} to be used by the
	 *            {@code CommandParser} commands.
	 * @param projectRepo
	 *            The {@code ProjectsRepository} to be used by the
	 *            {@code CommandParser} commands.
	 * @param workersRepo
	 *            The {@code WorkersRepository} to be used by the
	 *            {@code CommandParser} commands.
	 * @throws CommandParserException
	 */
	public void constructCommandParser(UserRepository userRepo,
			ProjectsRepository projectRepo, WorkerRepository workersRepo)
			throws CommandParserException {
		methodsMap.put(1, "POST");
		pathsMap.put(1, "/users");
		cmdsMap.put(1, new PostUsers.Factory(userRepo));
		methodsMap.put(2, "POST");
		pathsMap.put(2, "/consultants");
		cmdsMap.put(2, new PostConsultant.Factory(userRepo, workersRepo));
		methodsMap.put(3, "POST");
		pathsMap.put(3, "/projects");
		cmdsMap.put(3, new PostProjects.Factory(userRepo, projectRepo));
		methodsMap.put(4, "POST");
		pathsMap.put(4, "/projects/{" + PostWorkerInProject.PID + "}/{"
				+ PostWorkerInProject.WTYPE + "}");
		cmdsMap.put(4, new PostWorkerInProject.Factory(userRepo, projectRepo,
				workersRepo));
		methodsMap.put(5, "POST");
		pathsMap.put(5, "/projects/{" + PostSubprojects.PID + "}/subproject");
		cmdsMap.put(5, new PostSubprojects.Factory(userRepo, projectRepo));
		methodsMap.put(6, "GET");
		pathsMap.put(6, "/users");
		cmdsMap.put(6, new GetUsers.Factory(userRepo));
		methodsMap.put(7, "GET");
		pathsMap.put(7, "/users/{" + GetUser.USERNAME + "}");
		cmdsMap.put(7, new GetUser.Factory(userRepo));
		methodsMap.put(8, "GET");
		pathsMap.put(8, "/projects/{" + GetProjectWorkers.PID + "}/{"
				+ GetProjectWorkers.WTYPE + "}");
		cmdsMap.put(8, new GetProjectWorkers.Factory(projectRepo));
		methodsMap.put(9, "GET");
		pathsMap.put(9, "/projects/{" + GetSubproject.PID + "}/subproject");
		cmdsMap.put(9, new GetSubproject.Factory(projectRepo));
		methodsMap.put(10, "GET");
		pathsMap.put(10, "/projects/{" + GetProjects.PID + "}");
		cmdsMap.put(10, new GetProjects.Factory(projectRepo));
		methodsMap.put(11, "PATCH");
		pathsMap.put(11, "/users/{" + PatchUser.USERNAME + "}");
		cmdsMap.put(11, new PatchUser.Factory(userRepo));
		methodsMap.put(12, "PATCH");
		pathsMap.put(12, "/projects/{" + PatchProject.PID + "}");
		cmdsMap.put(12, new PatchProject.Factory(userRepo, projectRepo));
		methodsMap.put(13, "PATCH");
		pathsMap.put(13, "/consultants/{" + PatchConsultant.CID + "}");
		cmdsMap.put(13, new PatchConsultant.Factory(userRepo, workersRepo));
		methodsMap.put(14, "DELETE");
		pathsMap.put(14, "/projects/{" + GetProjects.PID + "}");
		cmdsMap.put(14, new DeleteProjects.Factory(userRepo, projectRepo));
		methodsMap.put(17, "POST");
		pathsMap.put(17, "/users/{" + PatchUser.USERNAME + "}/update");
		cmdsMap.put(17, new PatchUser.Factory(userRepo));
		methodsMap.put(18, "POST");
		pathsMap.put(18, "/projects/{" + PatchProject.PID + "}/update");
		cmdsMap.put(18, new PatchProject.Factory(userRepo, projectRepo));
		methodsMap.put(19, "POST");
		pathsMap.put(19, "/consultants/{" + PatchConsultant.CID + "}/update");
		cmdsMap.put(19, new PatchConsultant.Factory(userRepo, workersRepo));
		methodsMap.put(16, "GET");
		pathsMap.put(16, "/authenticate");
		cmdsMap.put(16, new AuthenticateUser.Factory(userRepo));

		CommandParser.register(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see parser.CommandParser.CommandsRegister#getMethods()
	 */
	@Override
	public TreeMap<Integer, String> getMethods() {
		return methodsMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see parser.CommandParser.CommandsRegister#getPaths()
	 */
	@Override
	public TreeMap<Integer, String> getPaths() {
		return pathsMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see parser.CommandParser.CommandsRegister#getFactories()
	 */
	@Override
	public TreeMap<Integer, CommandFactory> getFactories() {
		return cmdsMap;
	}

}
