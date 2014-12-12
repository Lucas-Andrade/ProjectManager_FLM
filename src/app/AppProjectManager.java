package app;

import java.io.IOException;
import java.util.Scanner;

import app.commandParser.CommandParser;
import app.commandParser.CommandParserException;
import app.commandParser.InvalidRegisterException;
import app.commands.GetProjectWorkers;
import app.commands.GetUser;
import app.commands.GetUsers;
import app.commands.PostConsultant;
import app.commands.PostProject;
import app.commands.PostSubproject;
import app.commands.PostUsers;
import app.commands.PostWorkerInProject;
import app.commands.exceptions.CommandException;
import app.repository.InMemoryProjectRepo;
import app.repository.InMemoryUserRepo;
import app.repository.InMemoryWorkerRepo;
import app.repository.ProjectRepository;
import app.repository.UserRepository;
import app.repository.WorkerRepository;
import app.resultsOutputMethods.ResultOutputAsStringToStream;
import app.resultsOutputMethods.ResultOutputMethodToStream;

/**
 * Class for Project Management.
 * 
 * Create a container of Projects, another of Users and other of Workers. 
 * 
 * This application returns the results of the executed command in the console, but
 * can be modified to print to another Output Stream.
 * 
 * <p>AVAIABLE COMMANDS:
 * <li>POST /users {parameter list} : add a user to the User Repository
 * <li>POST /project {parameter list} : add a Project to the Project repository.
 * <li>POST /consultant {parameter list} : add a consultant to the Worker
 * Repository.
 * <li>POST /project/{pid}/{type} {parameter list} : add a consultant or Manager
 * to a project/subproject.
 * <li>POST /project/{pid}/subproject {parameter list} : add a subproject to a
 * project/subproject.
 * <li>GET /users {parameter list} : Return the information of all users in the
 * User Repository
 * <li>GET /users/{username} : Return the information of the user with the
 * specify {@code username} of the User Repository
 * <li>GET /project/{pid}/{type} : Return the information of all consultants or
 * of the Manager of a project with the specify {@code ProjectId}.
 * <li>GET /project/{pid}/subproject : Return the information of all subprojects
 * of a project with the specify {@code ProjectId}.
 * <li>HELP: terminates the application.
 * <li>END: terminates the application.
 * 
 * <p> IMPLEMENTATION NOTES:
 * <li>Only registered users can use POST commands;
 * <li> Before start to use the commands, the user has to create the administrator data.
 * This operation allows the inclusion of other users to users repository. 
 * <li>The user's LoginName and Password must be introduce with the parameters
 * list of the POST Commands to authentication;
 * <li>All the commands have the same follow generic strutter: {method} {path} {parameter list} 
 * Example: POST /consultant/ loginName=FilipaG&password=123456&name=Filipe%20Maia&priceHour=20
 * 
 * <p> Public methods:
 * 
 * <li>execute: Ask for a command and execute it, till the END_APP command is
 * called.
 * <li>main: run the app.
 * 
 * @author Filipa Gonçalves., Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class AppProjectManager 
{
	public static void RegisterCommand(CommandParser parser, UserRepository userRepo, 
			ProjectRepository projectRepo, WorkerRepository workersRepo) throws InvalidRegisterException {
		
		parser.registerCommand("POST", "/users", new PostUsers.Factory(userRepo));
		parser.registerCommand("POST", "/consultant", new PostConsultant.Factory(userRepo, workersRepo));
		parser.registerCommand("POST", "/project", new PostProject.Factory(userRepo, projectRepo));
		parser.registerCommand("POST", "/project/{pid}/{type}",	new PostWorkerInProject.Factory(userRepo, projectRepo, workersRepo));
		parser.registerCommand("POST", "/project/{pid}/subproject", new PostSubproject.Factory(userRepo, projectRepo));
		parser.registerCommand("GET", "/users",	new GetUsers.Factory(userRepo));
		parser.registerCommand("GET", "/users/{username}", new GetUser.Factory(userRepo));
		parser.registerCommand("GET", "/project/{pid}/{type}",	new GetProjectWorkers.Factory(projectRepo));
		parser.registerCommand("GET", "/project/{pid}/subproject", new PostSubproject.Factory(userRepo, projectRepo));

	}
	
	
	public static void helpCommand()
	{
		System.out.println("IMPLEMENTATION NOTES:");
		System.out.println(" - Only registered users can use POST commands;"
				+ "\n    Before start to use the commands, the user has to create the administrator data.");
			 
		System.out.println("     The user's LoginName and Password must be introduce with the parameters list of"
				+"\n    the POST Commands to authentication;"
				+"\n If the authentication data are not valid, the command is not performed.");
		System.out.println("\n - All the commands have the same follow generic strutter:	\n              {method} {path} {parameter list}"
			+"\n\nIn the list of parameters, the parameters must be separated by the '&' symbol and the words by the code '% 20'");					
		System.out.println(" Example:" +
							"\n POST  /consultant/  loginName=FilipaG&password=123456&name=Filipe%20Maia&priceHour=20");
		
		
		System.out.println("\nAVAIABLE COMMANDS:"  
				+"\n  POST /users {parameter list}  :  Add a user to the User Repository "
				+"\n  POST /project  {parameter list} : Add a Project to the Project repository"
		        +"\n  POST /consultant  {parameter list}  :  Add a consultant to the Worker Repository" 
		        +"\n  POST /project/{pid}/{type}  {parameter list} : add a consultant or Manager to a project/subproject"
		        +"\n  POST /project/{pid}/subproject  {parameter list} : add a subproject to a project/subproject"
		        +"\n  GET /users {parameter list}  : Return the information of all users in the User Repository"
		        +"\n  GET /users/{username}  : Return the information of the user with the  specify {@code username} of the User Repository"
		        +"\n  GET /project/{pid}/{type} : Return the information of all consultants or of the Manager of a project with the  specify {@code ProjectId}"
		        +"\n  GET /project/{pid}/subproject : Return the information of all subprojects of a project with the  specify {@code ProjectId}"
		        +"\n  HELP: terminates the application"
		        +"\n  END: terminates the application");

		System.out.println("\nThe user must write the command in the console and press Enter to proceed.");
	}

	
	private ResultOutputMethodToStream out = new ResultOutputAsStringToStream(System.out);
	
	
	/**
	 * Ask for a command and execute it, till the END_APP command is called.
	 * @throws CommandParserException 
	 * @throws IOException 
	 * @throws CommandException 
	 */
	public void execute() throws CommandParserException, CommandException, IOException 
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		
		CommandParser parser = new CommandParser();
		ProjectRepository projectRepo = new InMemoryProjectRepo();
		UserRepository userRepo = new InMemoryUserRepo();
		WorkerRepository workersRepo = new InMemoryWorkerRepo();

		RegisterCommand(parser, userRepo, projectRepo, workersRepo);

		System.out.println("*********************************");
		System.out.println("********** JAVA COMPANY *********");
		System.out.println("*********************************");
		
		
		System.out.println("\n Create Administrator");
		System.out.println("LoginName: Admin1");
		System.out.print("Insert New password:");
		String password = scanner.nextLine();
		userRepo.addAdmin("Admin1", password);
		
		System.out.println("\nInsert the command you want to execute:");
		do
		{
			String a = scanner.nextLine();
			switch (a) {

				case "HELP":
					helpCommand();
					scanner.nextLine();
					break;
		
				case "END":
					scanner.nextLine();
					return;
				default:
					parser.getCommand(a.split(" ")).execute(out);
					scanner.nextLine();
					
			}

		} while (true);
		
	}	
	
		
	
	
	public static void main(String[] args) throws CommandParserException, CommandException, IOException {
		
		AppProjectManager app = new AppProjectManager();
		app.execute();
		
		
	}
}
