/**
 * 
 */
package app;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import consoleCommands.DeleteProjects;
import consoleCommands.GetProjectWorkers;
import consoleCommands.GetProjects;
import consoleCommands.GetSubproject;
import consoleCommands.GetUser;
import consoleCommands.GetUsers;
import consoleCommands.Option;
import consoleCommands.PatchConsultant;
import consoleCommands.PatchProject;
import consoleCommands.PatchUser;
import consoleCommands.PostConsultant;
import consoleCommands.PostProjects;
import consoleCommands.PostSubprojects;
import consoleCommands.PostUsers;
import consoleCommands.PostWorkerInProject;
import app.repository.ProjectsRepository;
import app.repository.UserRepository;
import app.repository.WorkerRepository;
import parser.CommandParser.CommandsRegister;
import parserUtils.CommandFactory;

/**
 * Class that implements {@link CommandsRegister}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 09/02/2015
 */
public class ConsoleCommandsRegister implements CommandsRegister {

	/**
	 * A {@code Map} containing the methods and the factories of the commands to
	 * be registered.
	 */
	private final Map<String, CommandFactory> cmdsMap;

	/**
	 * A {@code Map} containing the methods and the paths of the commands to be
	 * registered.
	 */
	private final Map<String, String> pathsMap;

	/**
	 * The constructor for this class.
	 */
	public ConsoleCommandsRegister(UserRepository userRepo,
			ProjectsRepository projectRepo, WorkerRepository workersRepo) {
		this.cmdsMap = new HashMap<String, CommandFactory>();
		this.pathsMap = new HashMap<String, String>();

		pathsMap.put("POST", "/users");
		cmdsMap.put("POST", new PostUsers.Factory(userRepo));
		pathsMap.put("POST", "/consultants");
		cmdsMap.put("POST", new PostConsultant.Factory(userRepo, workersRepo));
		pathsMap.put("POST", "/projects");
		cmdsMap.put("POST", new PostProjects.Factory(userRepo, projectRepo));
		pathsMap.put("POST", "/projects/{" + PostWorkerInProject.PID + "}/{"
				+ PostWorkerInProject.WTYPE + "}");
		cmdsMap.put("POST", new PostWorkerInProject.Factory(userRepo,
				projectRepo, workersRepo));
		pathsMap.put("POST", "/projects/{" + PostSubprojects.PID
				+ "}/subproject");
		cmdsMap.put("POST", new PostSubprojects.Factory(userRepo, projectRepo));
		pathsMap.put("GET", "/users");
		cmdsMap.put("GET", new GetUsers.Factory(userRepo));
		pathsMap.put("GET", "/users/{" + GetUser.USERNAME + "}");
		cmdsMap.put("GET", new GetUser.Factory(userRepo));
		pathsMap.put("GET", "/projects/{" + GetProjectWorkers.PID + "}/{"
				+ GetProjectWorkers.WTYPE + "}");
		cmdsMap.put("GET", new GetProjectWorkers.Factory(projectRepo));
		pathsMap.put("GET", "/projects/{" + GetSubproject.PID + "}/subproject");
		cmdsMap.put("GET", new GetSubproject.Factory(projectRepo));
		pathsMap.put("GET", "/projects/{" + GetProjects.PID + "}");
		cmdsMap.put("GET", new GetProjects.Factory(projectRepo));
		pathsMap.put("PATCH", "/users/{" + PatchUser.USERNAME + "}");
		cmdsMap.put("PATCH", new PatchUser.Factory(userRepo));
		pathsMap.put("PATCH", "/projects/{" + PatchProject.PID + "}");
		cmdsMap.put("PATCH", new PatchProject.Factory(userRepo, projectRepo));
		pathsMap.put("PATCH", "/consultants/{" + PatchConsultant.CID + "}");
		cmdsMap.put("PATCH", new PatchConsultant.Factory(userRepo, workersRepo));
		pathsMap.put("DELETE", "/projects/{" + GetProjects.PID + "}");
		cmdsMap.put("DELETE", new DeleteProjects.Factory(userRepo, projectRepo));
		pathsMap.put("OPTION", "/");
		cmdsMap.put("OPTION", new Option.Factory());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see parser.CommandParser.CommandsRegister#getMethods()
	 */
	@Override
	public Iterator<String> getMethods() {
		return cmdsMap.keySet().iterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see parser.CommandParser.CommandsRegister#getPaths()
	 */
	@Override
	public Iterator<String> getPaths() {
		return pathsMap.values().iterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see parser.CommandParser.CommandsRegister#getFactories()
	 */
	@Override
	public Iterator<CommandFactory> getFactories() {
		return cmdsMap.values().iterator();
	}

}
