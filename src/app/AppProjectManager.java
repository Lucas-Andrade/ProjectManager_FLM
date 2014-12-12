package app;

import java.io.FileNotFoundException;
import java.util.Scanner;

import app.commandParser.CommandParser;
import app.commandParser.CommandParserException;
import app.commandParser.InvalidRegisterException;
import app.commands.GetProjectWorkers;
import app.commands.GetSubproject;
import app.commands.GetUser;
import app.commands.GetUsers;
import app.commands.PostConsultant;
import app.commands.PostProject;
import app.commands.PostSubproject;
import app.commands.PostUsers;
import app.commands.PostWorkerInProject;
import app.repository.InMemoryProjectRepo;
import app.repository.InMemoryUserRepo;
import app.repository.InMemoryWorkerRepo;
import app.repository.ProjectRepository;
import app.repository.UserRepository;
import app.repository.WorkerRepository;
/**
 * Class for Project Manager. Create a container of projects, another of users and other of workers.
 * 
 * <p>
  * Commands:
 * <li>POST /users {parameter list}  : add a user to the User Repository
 * <li>POST /project  {parameter list}  : add a Project to the Project repository.
 * <li>POST /consultant  {parameter list}  : add a consultant to the Worker Repository.
 * <li>POST /project/{pid}/{type}  {parameter list} : add a consultant or Manager to a project/subproject.
 * <li>POST /project/{pid}/subproject  {parameter list} : add a subproject to a project/subproject.
 * <li>GET /users {parameter list}  : Return the information of all users in the User Repository
 * <li>GET /users/{username}  : Return the information of the user with the  specify {@code username} of the User Repository
 * <li>GET /project/{pid}/{type} : Return the information of all consultants or of the Manager of a project with the  specify {@code ProjectId}.
 * <li>GET /project/{pid}/subproject : Return the information of all subprojects of a project with the  specify {@code ProjectId}..
 * <li>HELP: terminates the application.
 * <li>END: terminates the application.
 * 
 * <p>
 * Public methods:
 * 
 * <li>execute: Ask for a command and execute it, till the END_APP command is called.
 * <li>main: run the app.
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
//		Só utilizadores registados podem utilizar os comandos POST;
//		O LoginName e a password do utilizador devem ser inseridos junto com a lista de parâmetros do Comando POST que pretende utilizar
//		Se os dados de autenticação não forem válidos, o comando não é efectuado.
//		Na lista de parâmetros, os parâmetros devem ser separados pelo símbolo "&" e as palavras pelo código "%20".
//		All the commands have the same follow generic strutur:
//		{method} {path} {parameter list}
//		Example:
//			•	POST  /consultant/  loginName=FilipaG&password=123456&name=Filipe%20Maia&priceHour=20

	// Comandos disponíveis:
//		Post Users ->  POST /users {parameter list}
//		Post Consultant ->  POST /consultant  {parameter list}
//		Post Project ->  POST /project  {parameter list}
//		Post Consultant/Manager in Project -> POST /project/{pid}/{type}  {parameter list}
//		Post Subproject in Project	->	POST /project/{pid}/subproject  {parameter list}
//		Get Users  ->  GET  /users  {parameter list}
//		Get User  -> GET  /users/{username}  {parameter list}
//		Get Consultant/Manager in Project  -> GET  /project/{pid}/{type}  {parameter list}
//		Get Subproject in Project  ->  GET  /project/{pid}/subproject  {parameter list}
		
		
		

	}
	
	
	/**
	 * Ask for a command and execute it, till the END_APP command is called.
	 * @throws CommandParserException 
	 */
	public void execute() throws CommandParserException 
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
		
		System.out.println("Insert the command you want to execute:");
		do
		{
			switch (scanner.next()) {

				case "HELP":
					scanner.nextLine();
					break;
		
				case "END":
					scanner.nextLine();
					return;
				default:
					parser.getCommand(scanner.nextLine());
			}

		} while (true);
		
	}	
	
		
	
	
	public static void main(String[] args) throws InvalidRegisterException, FileNotFoundException {
		
		App app = new App();
		app.execute();
		
		
	}
}
