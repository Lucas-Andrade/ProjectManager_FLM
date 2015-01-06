import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import app.commandParser.CommandParser;
import app.commandParser.CommandParserException;
import app.commandParser.InvalidRegisterException;
import app.commands.DeleteProjects;
import app.commands.GetProjects;
import app.commands.GetProjectWorkers;
import app.commands.GetSubproject;
import app.commands.GetUser;
import app.commands.GetUsers;
import app.commands.Option;
import app.commands.PatchConsultant;
import app.commands.PatchProject;
import app.commands.PatchUser;
import app.commands.PostConsultant;
import app.commands.PostProjects;
import app.commands.PostSubprojects;
import app.commands.PostUsers;
import app.commands.PostWorkerInProject;
import app.commands.exceptions.CommandException;
import app.repository.InMemoryProjectRepo;
import app.repository.InMemoryUserRepo;
import app.repository.InMemoryWorkerRepo;
import app.repository.ProjectsRepository;
import app.repository.UserRepository;
import app.repository.WorkerRepository;
import app.resultsAndOutputMethods.Result;

/**
 * Class for Project Management.
 * 
 * Create a container of Projects, another of Users and other of Workers.
 * 
 * This application returns the results of the executed command in the console,
 * but can be modified to print to another Output Stream.
 * 
 * <p>
 * AVAIABLE COMMANDS:
 * <li>POST /users {parameter list} : add a user to the User Repository
 * <li>POST /projects {parameter list} : add a Project to the Project
 * repository.
 * <li>POST /consultants {parameter list} : add a consultant to the Worker
 * Repository.
 * <li>POST /projects/{pid}/{type} {parameter list} : add a consultant or
 * Manager to a project/subproject.
 * <li>POST /projects/{pid}/subproject {parameter list} : add a subproject to a
 * projects/subprojects.
 * <li>GET /users : Return the information of all users in the User Repository
 * <li>GET /users/{username} : Return the information of the user with the
 * specify {@code username} of the User Repository
 * <li>GET /projects/{pid}/{type} : Return the information of all consultants or
 * of the Manager of a project with the specify {@code ProjectId}.
 * <li>GET /projects/{pid} : Return the information of the project with the
 * specify {@code ProjectId}.
 * <li>GET /projects/{pid}/subproject : Return the information of all
 * subprojects of a project with the specify {@code ProjectId}.
 * <li>PATCH /users/{username} {parameter list} : Updates the password of the
 * user identified by the specify {@code username}.
 * <li>PATCH /projects/{pid} {parameter list}: Update the information of the
 * project identified by the specify {@code ProjectId}.
 * <li>PATCH /consultants/{cid} {parameter list}: Updates the information of the
 * consultant with the specify {@code WorkerId}.
 * <li>DELETE /projects/{pid} : Deletes the project with the specify
 * {@code ProjectId} and all its subprojects
 * <li>OPTION: Displays a description of all available commands.
 * <li>HELP: Show an user guide to use the application
 * <li>EXIT: terminates the application.
 * 
 * <p>
 * IMPLEMENTATION NOTES:
 * <li>Only registered users can use POST commands;
 * <li>Before start to use the commands, the user has to create the
 * administrator data. This operation allows the inclusion of other users to
 * users repository.
 * <li>The user's LoginName and Password must be introduce with the parameters
 * list of the POST Commands to authentication;
 * <li>All the commands have the same follow generic strutter: {method} {path}
 * {parameter list} Example: POST /consultants/
 * loginName=FilipaG&password=123456&name=Filipe%20Maia&priceHour=20
 * 
 * <p>
 * Public methods:
 * 
 * <li>execute: Ask for a command and execute it, till the EXIT command is
 * called.
 * <li>main: run the app.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class AppProjectManager
{

	/**
	 * The variable that defines the default Output used in this application.
	 */
	private static PrintStream DEFAULT_SYSTEM_OUT = System.out;

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
			WorkerRepository workersRepo) throws InvalidRegisterException
	{

		parser.registerCommand("POST", "/users",
				new PostUsers.Factory(userRepo));
		parser.registerCommand("POST", "/consultants",
				new PostConsultant.Factory(userRepo, workersRepo));
		parser.registerCommand("POST", "/projects", new PostProjects.Factory(
				userRepo, projectRepo));
		parser.registerCommand("POST", "/projects/{" + PostWorkerInProject.PID
				+ "}/{" + PostWorkerInProject.WTYPE + "}",
				new PostWorkerInProject.Factory(userRepo, projectRepo,
						workersRepo));
		parser.registerCommand("POST", "/projects/{" + PostSubprojects.PID
				+ "}/subproject", new PostSubprojects.Factory(userRepo,
				projectRepo));
		parser.registerCommand("GET", "/users", new GetUsers.Factory(userRepo));
		parser.registerCommand("GET", "/users/{" + GetUser.USERNAME + "}",
				new GetUser.Factory(userRepo));
		parser.registerCommand("GET", "/projects/{" + GetProjectWorkers.PID
				+ "}/{" + GetProjectWorkers.WTYPE + "}",
				new GetProjectWorkers.Factory(projectRepo));
		parser.registerCommand("GET", "/projects/{" + GetSubproject.PID
				+ "}/subproject", new GetSubproject.Factory(projectRepo));
		parser.registerCommand("GET", "/projects/{" + GetProjects.PID + "}",
				new GetProjects.Factory(projectRepo));
		parser.registerCommand("PATCH", "/users/{" + PatchUser.USERNAME + "}",
				new PatchUser.Factory(userRepo));
		parser.registerCommand("PATCH", "/projects/{" + PatchProject.PID + "}",
				new PatchProject.Factory(userRepo, projectRepo));
		parser.registerCommand("PATCH", "/consultants/{" + PatchConsultant.CID
				+ "}", new PatchConsultant.Factory(userRepo, workersRepo));
		parser.registerCommand("DELETE", "/projects/{" + GetProjects.PID + "}",
				new DeleteProjects.Factory(userRepo, projectRepo));
		parser.registerCommand("OPTION", "/", new Option.Factory());
	}

	/**
	 * This method has all information that the user has to know to correctly
	 * use this application: The actually available commands and the
	 * implementation notes
	 */
	public static void helpCommand()
	{
		DEFAULT_SYSTEM_OUT.println("IMPLEMENTATION NOTES:");
		DEFAULT_SYSTEM_OUT
				.println(" - Only registered users can use POST commands;"
						+ "\n    Before start to use the commands, the user has to create the administrator data.");

		DEFAULT_SYSTEM_OUT
				.println("     The user's LoginName and Password must be introduce with the parameters list of"
						+ "\n    the POST Commands to authentication;"
						+ "\n If the authentication data are not valid, the command is not performed.");
		DEFAULT_SYSTEM_OUT
				.println("\n - All the commands have the same follow generic strutter:	\n              {method} {path} {parameter list}"
						+ "\n\nIn the list of parameters, the parameters must be separated by the '&' symbol and the words by the code '% 20'");
		DEFAULT_SYSTEM_OUT
				.println(" Example:"
						+ "\n POST  /consultants/  loginName=FilipaG&password=123456&name=Filipe%20Maia&priceHour=20");
		DEFAULT_SYSTEM_OUT
				.println("\nThe user must write the command in the console and press Enter to proceed.");

		DEFAULT_SYSTEM_OUT
				.println("\nTo learn the commands that are available in this application enter the OPTION command that displays "
						+ "a description of all available commands.");
		DEFAULT_SYSTEM_OUT
				.println("\nTo end and exit of this aplication, enter the END command.");
	}

	/**
	 * Ask for a command and execute it, till the END command is called.
	 * 
	 * @throws Exception
	 */
	public static void execute(CommandParser parser, UserRepository userRepo)
			throws Exception
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		DEFAULT_SYSTEM_OUT.println("*********************************");
		DEFAULT_SYSTEM_OUT.println("********** JAVA COMPANY *********");
		DEFAULT_SYSTEM_OUT.println("*********************************");

		DEFAULT_SYSTEM_OUT.println("\n Create Administrator");
		DEFAULT_SYSTEM_OUT.println("LoginName: Admin1");
		DEFAULT_SYSTEM_OUT.print("Insert New password:");
		String password = scanner.nextLine();

		while (password.length() <= 3)
		{
			DEFAULT_SYSTEM_OUT
					.println("Password must at least have 4 characters.\nInsert password:");
			password = scanner.nextLine();
		}
		userRepo.addAdmin("Admin1", password);

		do
		{
			DEFAULT_SYSTEM_OUT
					.println("\nInsert the command you want to execute:");
			String cmd = scanner.nextLine();
			switch (cmd)
			{

				case "HELP":
					helpCommand();
					break;

				case "EXIT":
					DEFAULT_SYSTEM_OUT
							.println("App now closing.\nThank you for choosing Java Company!");
					return;

				default:
					commandPrompt(parser, cmd);
			}

		} while (true);
	}

	/**
	 * Execute the command.
	 * 
	 * @param parser
	 *            The {@code CommandParser} to use for getting the
	 *            {@code Command} to execute.
	 * @param cmd
	 *            A {@code String} of the {@code Command} to execute.
	 * @throws Exception
	 */
	private static void commandPrompt(CommandParser parser, String cmd)
			throws Exception
	{
		try
		{
			Result results = parser.getCommand(cmd.split(" ")).call();
			results.showResults();
		} catch (CommandException e) // Excepções relacionadas com os comandos.
		{
			DEFAULT_SYSTEM_OUT.println(e.getMessage());
		} catch (CommandParserException e) // Excepções relacionadas com o
											// CommandParser.
		{
			DEFAULT_SYSTEM_OUT.println(e.getMessage());
		} catch (NullPointerException e)
		{
			DEFAULT_SYSTEM_OUT.println("Not found.");
		} catch (FileNotFoundException e) // Excepção relacionada com o Result.
		{
			DEFAULT_SYSTEM_OUT
					.println("Invalid output destination for results.");
		} catch (ClassNotFoundException e) // Excepção relacionada com o Result.
		{
			DEFAULT_SYSTEM_OUT.println("Invalid accept format for results.");
		} catch (Exception e) // Todas as restantes excepções.
		{
			DEFAULT_SYSTEM_OUT.println(e.getMessage());
			DEFAULT_SYSTEM_OUT.println(e.getClass().getName());
		}
	}

	public static void main(String[] args) throws Exception
	{
		CommandParser parser = new CommandParser();
		UserRepository userRepo = new InMemoryUserRepo();
		try
		{
			RegisterCommand(parser, userRepo, new InMemoryProjectRepo(),
					new InMemoryWorkerRepo());
		} catch (InvalidRegisterException e)
		{
			DEFAULT_SYSTEM_OUT.println(e.getMessage());
		}

		if (args.length == 0)
		{
			execute(parser, userRepo);
			DEFAULT_SYSTEM_OUT.close();
			return;
		}

		for (String cmd : args)
			commandPrompt(parser, cmd);
		DEFAULT_SYSTEM_OUT.close();
	}

}
