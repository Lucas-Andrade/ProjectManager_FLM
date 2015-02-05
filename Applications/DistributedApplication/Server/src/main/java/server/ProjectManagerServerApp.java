package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import parser.CommandParser;
import parserUtils.InvalidRegisterException;
import app.repository.InMemoryProjectRepo;
import app.repository.InMemoryUserRepo;
import app.repository.InMemoryWorkerRepo;
import app.repository.ProjectsRepository;
import app.repository.UserRepository;
import app.repository.WorkerRepository;


public class ProjectManagerServerApp{

	private static final int LISTEN_PORT = 9999;
	public static final CommandParser PARSER = constructParser();
	
	
	public static void main(String[] args) throws Exception {

		UserRepository uRepo = new InMemoryUserRepo();
		ProjectsRepository pRepo = new InMemoryProjectRepo();
		WorkerRepository wRepo = new InMemoryWorkerRepo();

		RegisterCommand(constructParser(), uRepo, pRepo, wRepo);
		constructServer();
	}

	
	
	
	
	
	
	private static void constructServer() throws Exception {
		Server server = new Server(LISTEN_PORT);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        
        handler.addServletWithMapping(ProjectManagerServlet.class, "/*");
        
        server.start();
        System.out.println("Server is started");
        
        System.in.read();
        server.stop();
        System.out.println("Server is stopped, it was an honor serving you."); 
	}

	
	
	
	private static CommandParser constructParser() {
		CommandParser cp = CommandParser.getInstance();
		return cp;
	}
	
	
	/**
	 * This method associates the method and the path that the user will insert
	 * to reach the corresponding command factory, and register the command.
	 * 
	 * @param parser
	 * @param userRepo
	 *            repository to store all registered users
	 * @param projectRepo
	 *            repository to store all created projects
	 * @param workersRepo
	 *            repository to store all created Consultants (and managers)
	 * @throws InvalidRegisterException
	 */
	public static void RegisterCommand(CommandParser parser,
			UserRepository userRepo, ProjectsRepository projectRepo,
			WorkerRepository workersRepo) throws InvalidRegisterException{

		parser.registerCommand("POST", "/users",
				new consoleCommands.PostUsers.Factory(userRepo));
		parser.registerCommand("POST", "/consultants",
				new consoleCommands.PostConsultant.Factory(userRepo,
						workersRepo));
		parser.registerCommand("POST", "/projects",
				new consoleCommands.PostProjects.Factory(userRepo, projectRepo));
		parser.registerCommand("POST", "/projects/{"
				+ consoleCommands.PostWorkerInProject.PID + "}/{"
				+ consoleCommands.PostWorkerInProject.WTYPE + "}",
				new consoleCommands.PostWorkerInProject.Factory(userRepo,
						projectRepo, workersRepo));
		parser.registerCommand("POST", "/projects/{"
				+ consoleCommands.PostSubprojects.PID + "}/subproject",
				new consoleCommands.PostSubprojects.Factory(userRepo,
						projectRepo));
		parser.registerCommand("GET", "/users",
				new consoleCommands.GetUsers.Factory(userRepo));
		parser.registerCommand("GET", "/users/{"
				+ consoleCommands.GetUser.USERNAME + "}",
				new consoleCommands.GetUser.Factory(userRepo));
		parser.registerCommand("GET", "/projects/{"
				+ consoleCommands.GetProjectWorkers.PID + "}/{"
				+ consoleCommands.GetProjectWorkers.WTYPE + "}",
				new consoleCommands.GetProjectWorkers.Factory(projectRepo));
		parser.registerCommand("GET", "/projects/{"
				+ consoleCommands.GetSubproject.PID + "}/subproject",
				new consoleCommands.GetSubproject.Factory(projectRepo));
		parser.registerCommand("GET", "/projects/{"
				+ consoleCommands.GetProjects.PID + "}",
				new consoleCommands.GetProjects.Factory(projectRepo));
		// parser.registerCommand("PATCH", "/users/{" + PatchUser.USERNAME +
		// "}", new PatchUser.Factory(userRepo));
//		parser.registerCommand("PATCH", "/projects/{" + PatchProject.PID + "}",
//				new PatchProject.Factory(userRepo, projectRepo));
//		parser.registerCommand("PATCH", "/consultants/{" + PatchConsultant.CID
//				+ "}", new PatchConsultant.Factory(userRepo, workersRepo));
//		parser.registerCommand("DELETE", "/projects/{" + GetProjects.PID + "}",
//				new DeleteProjects.Factory(userRepo, projectRepo));
//		parser.registerCommand("OPTION", "/", new Option.Factory());
	}

}
