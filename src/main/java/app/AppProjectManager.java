package main.java.app;

import java.io.IOException;
import java.util.Scanner;

import main.java.app.commandParser.CommandParser;
import main.java.app.commandParser.CommandParserException;
import main.java.app.commandParser.DuplicateArgumentsException;
import main.java.app.commandParser.InvalidCommandArgumentsException;
import main.java.app.commandParser.InvalidRegisterException;
import main.java.app.commandParser.UnknownCommandException;
import main.java.app.commands.GetProject;
import main.java.app.commands.GetProjectWorkers;
import main.java.app.commands.GetSubproject;
import main.java.app.commands.GetUser;
import main.java.app.commands.GetUsers;
import main.java.app.commands.PostConsultant;
import main.java.app.commands.PostProject;
import main.java.app.commands.PostSubproject;
import main.java.app.commands.PostUsers;
import main.java.app.commands.PostWorkerInProject;
import main.java.app.commands.exceptions.CommandException;
import main.java.app.commands.exceptions.InvalidParameterValueException;
import main.java.app.commands.exceptions.MandatoryParameterNotPresentException;
import main.java.app.repository.InMemoryProjectRepo;
import main.java.app.repository.InMemoryUserRepo;
import main.java.app.repository.InMemoryWorkerRepo;
import main.java.app.repository.ProjectRepository;
import main.java.app.repository.UserRepository;
import main.java.app.repository.WorkerRepository;
import main.java.app.resultsOutputMethods.ResultOutputAsStringToStream;
import main.java.app.resultsOutputMethods.ResultOutputMethodToStream;

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
 * <li>POST /project {parameter list} : add a Project to the Project repository.
 * <li>POST /consultant {parameter list} : add a consultant to the Worker
 * Repository.
 * <li>POST /project/{pid}/{type} {parameter list} : add a consultant or Manager
 * to a project/subproject.
 * <li>POST /project/{pid}/subproject {parameter list} : add a subproject to a
 * project/subproject.
 * <li>GET /users : Return the information of all users in the
 * User Repository
 * <li>GET /users/{username} : Return the information of the user with the
 * specify {@code username} of the User Repository
 * <li>GET /project/{pid}/{type} : Return the information of all consultants or
 * of the Manager of a project with the specify {@code ProjectId}.
 * <li>GET /project/{pid} : Return the information of the project with the
 * specify {@code ProjectId}.
 * <li>GET /project/{pid}/subproject : Return the information of all subprojects
 * of a project with the specify {@code ProjectId}.
 * <li>HELP: terminates the application.
 * <li>END: terminates the application.
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
 * {parameter list} Example: POST /consultant/
 * loginName=FilipaG&password=123456&name=Filipe%20Maia&priceHour=20
 * 
 * <p>
 * Public methods:
 * 
 * <li>execute: Ask for a command and execute it, till the END_APP command is
 * called.
 * <li>main: run the app.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class AppProjectManager
{
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
			UserRepository userRepo, ProjectRepository projectRepo,
			WorkerRepository workersRepo) throws InvalidRegisterException
	{

		parser.registerCommand("POST", "/users",
				new PostUsers.Factory(userRepo));
		parser.registerCommand("POST", "/consultant",
				new PostConsultant.Factory(userRepo, workersRepo));
		parser.registerCommand("POST", "/project", new PostProject.Factory(
				userRepo, projectRepo));
		parser.registerCommand("POST", "/project/{" + PostWorkerInProject.PID
				+ "}/{" + PostWorkerInProject.WTYPE + "}",
				new PostWorkerInProject.Factory(userRepo, projectRepo, workersRepo));
		parser.registerCommand("POST", "/project/{" + PostSubproject.PID
				+ "}/subproject", new PostSubproject.Factory(userRepo,
				projectRepo));
		parser.registerCommand("GET", "/users", new GetUsers.Factory(userRepo));
		parser.registerCommand("GET", "/users/{" + GetUser.USERNAME + "}",
				new GetUser.Factory(userRepo));
		parser.registerCommand("GET", "/project/{" + GetProjectWorkers.PID
				+ "}/{" + GetProjectWorkers.WTYPE + "}",
				new GetProjectWorkers.Factory(projectRepo));
		parser.registerCommand("GET", "/project/{" + GetSubproject.PID
				+ "}/subproject", new GetSubproject.Factory(projectRepo));
		parser.registerCommand("GET", "/project/{" + GetProject.PID
				+ "}", new GetProject.Factory(projectRepo));
	}

	/**
	 * This method has all information that the user has to know to correctly
	 * use this application: The actually available commands and the
	 * implementation notes
	 */
	public static void helpCommand()
	{
		System.out.println("IMPLEMENTATION NOTES:");
		System.out
				.println(" - Only registered users can use POST commands;"
						+ "\n    Before start to use the commands, the user has to create the administrator data.");

		System.out
				.println("     The user's LoginName and Password must be introduce with the parameters list of"
						+ "\n    the POST Commands to authentication;"
						+ "\n If the authentication data are not valid, the command is not performed.");
		System.out
				.println("\n - All the commands have the same follow generic strutter:	\n              {method} {path} {parameter list}"
						+ "\n\nIn the list of parameters, the parameters must be separated by the '&' symbol and the words by the code '% 20'");
		System.out
				.println(" Example:"
						+ "\n POST  /consultant/  loginName=FilipaG&password=123456&name=Filipe%20Maia&priceHour=20");

		System.out
				.println("\nAVAIABLE COMMANDS:"
						+ "\n  POST /users {parameter list}  :  Add a user to the User Repository "
						+ "\n  POST /project  {parameter list} : Add a Project to the Project repository"
						+ "\n  POST /consultant  {parameter list}  :  Add a consultant to the Worker Repository"
						+ "\n  POST /project/{pid}/{type}  {parameter list} : add a consultant or Manager to a project/subproject"
						+ "\n  POST /project/{pid}/subproject  {parameter list} : add a subproject to a project/subproject"
						+ "\n  GET /users {parameter list}  : Return the information of all users in the User Repository"
						+ "\n  GET /users/{username}  : Return the information of the user with the  specify {@code username} of the User Repository"
						+ "\n  GET /project/{pid}/{type} : Return the information of all consultants or of the Manager of a project with the  specify {@code ProjectId}"
						+ "\n  GET /project/{pid}/subproject : Return the information of all subprojects of a project with the  specify {@code ProjectId}"
						+ "\n  HELP: terminates the application"
						+ "\n  END: terminates the application");

		System.out
				.println("\nThe user must write the command in the console and press Enter to proceed.");
	}

	/**
	 * The variable that defines the kind of OutputStream we will use in this
	 * application
	 */
	private ResultOutputMethodToStream out = new ResultOutputAsStringToStream(
			System.out);

	/**
	 * Ask for a command and execute it, till the END command is called.
	 * 
	 * @throws CommandParserException
	 * @throws IOException
	 * @throws CommandException
	 */
	public void execute() throws CommandParserException, CommandException,
			IOException
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		CommandParser parser = new CommandParser();
		ProjectRepository projectRepo = new InMemoryProjectRepo();
		UserRepository userRepo = new InMemoryUserRepo();
		WorkerRepository workersRepo = new InMemoryWorkerRepo();
		try
		{
			RegisterCommand(parser, userRepo, projectRepo, workersRepo);
		} catch (InvalidRegisterException e)
		{
			System.out
					.println("Invalid Registry! Verify RegisterCommand method.");
		}

		System.out.println("*********************************");
		System.out.println("********** JAVA COMPANY *********");
		System.out.println("*********************************");

		System.out.println("\n Create Administrator");
		System.out.println("LoginName: Admin1");
		System.out.print("Insert New password:");
		String password = scanner.nextLine();
		
		while(password.length() <= 3)
		{
			System.out.println("Password must at least have 4 characters.\nInsert password:");
			password = scanner.nextLine();
		}
		userRepo.addAdmin("Admin1", password);

		do
		{
			System.out.println("\nInsert the command you want to execute:");
			String a = scanner.nextLine();
			switch (a)
			{

				case "HELP":
					helpCommand();
					break;

				case "END":
					System.out.println("App now closing.\nThank you for choosing Java Company!");
					return;
				default:
					try
					{
						parser.getCommand(a.split(" ")).execute(out);
					} catch (UnknownCommandException e)
					{
						System.out.println("Args must have 2 or 3 elements.");
					} catch (InvalidCommandArgumentsException e)
					{
						System.out
								.println("Inserted a parameter without value.");
					} catch (DuplicateArgumentsException e)
					{
						System.out
								.println("Duplicate parameters entered.");
					} catch (MandatoryParameterNotPresentException e)
					{
						System.out
								.println("Not all required parameters were entered.");
					} catch (InvalidParameterValueException e)
					{
						System.out
								.println("Inserted a parameter with an invalid value.");
					} catch (CommandException e)
					{
						System.out.println("Invalid Command.");
					} catch (IOException e)
					{
						System.out.println("Invalid output Stream.");
					} catch(NullPointerException e)
					{
						System.out.println("Not found.");
					}
			}

		} while (true);

	}

	public static void main(String[] args) throws CommandParserException,
			CommandException, IOException
	{

		AppProjectManager app = new AppProjectManager();
		app.execute();

	}
}
