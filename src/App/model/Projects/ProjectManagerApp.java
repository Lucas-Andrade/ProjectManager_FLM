package App.model.Projects;

import App.commandParser.CommandParser;
import App.commandParser.InvalidRegisterException;
import App.commands.GetProjectWorkers;
import App.commands.GetSubproject;
import App.commands.GetUser;
import App.commands.GetUsers;
import App.commands.PostConsultant;
import App.commands.PostProject;
import App.commands.PostSubproject;
import App.commands.PostUsers;
import App.commands.PostWorkerInProject;





public class ProjectManagerApp {

	public static void main(String[] args) throws InvalidRegisterException {
		CommandParser parser = new CommandParser();
		UsersRepository productRepo = new InMemoryProjectsRepository();
		

		parser.registerCommand("GET", "/projects/{pid}/{type}", new GetProjectWorkers.Factory(repository));
		parser.registerCommand("GET", "/projects/{pid}/subprojects", new GetSubproject.Factory(repository));
		parser.registerCommand("GET", "/users/{username}", new GetUser.Factory());
		parser.registerCommand("GET", "/users", new GetUsers());
		parser.registerCommand("POST", "/projects/{pid}/subprojects ", new PostSubproject.Factory());
		parser.registerCommand("POST", "/projects/{pid}/{type}", new PostWorkerInProject.Factory());
		parser.registerCommand("POST", "/consultants", new PostConsultant.Factory());
		parser.registerCommand("POST", "/projects", new PostProject.Factory());
		parser.registerCommand("POST", "/users", new PostUsers.Factory());

	}

}
