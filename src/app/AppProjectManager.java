package app;

import java.io.FileNotFoundException;
import java.util.Scanner;

import app.commandParser.CommandParser;
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
 * Class for Project Manager. Create a container of projects (named MainProject) how store all the
 * Main projects existent in the Company.
 * 
 * <p>
 * Commands:
 * <li>ADD_PROJECT: add a Main Project.* 
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

		// Comandos disponúveis:
		

	}
	
	
	/**
	 * Ask for a command and execute it, till the END_APP command is called.
	 */
	public void execute() 
	{
		Scanner scanner = new Scanner(System.in);
		
		CommandParser parser = new CommandParser();
		ProjectRepository projectRepo = new InMemoryProjectRepo();
		UserRepository userRepo = new InMemoryUserRepo();
		WorkerRepository workersRepo = new InMemoryWorkerRepo();

		RegisterCommand(parser, userRepo, projectRepo, workersRepo);

		System.out.println("*********************************");
		System.out.println("********** JAVA COMPANY *********");
		System.out.println("*********************************");
		
		System.out.println("Insert ");
		do
		{
			for (String element : commands)
				System.out.println("->" + element);

			switch (scanner.next()) {

				case "":
					scanner.nextLine();
	
					break;
				case 2:
					scanner.nextLine();
					addSubProject();
					break;
		
				case "end":
					scanner.nextLine();
					return;
				default:
					System.out.println("Invalid Command. Please Insert a Valid Command");
			}

		} while (true);

	}	
	
		
	
	
	public static void main(String[] args) throws InvalidRegisterException, FileNotFoundException {
		
		App app = new App();
		app.execute();
		
		
	}
}
