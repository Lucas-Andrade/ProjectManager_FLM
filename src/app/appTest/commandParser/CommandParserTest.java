package app.appTest.commandParser;

import static org.junit.Assert.*;
import org.junit.Test;
import utils.Leader;
import app.appTest.RepositoryConstructor;
import app.commandParser.CommandParser;
import app.commandParser.CommandParserException;
import app.commands.GetProjectWorkers;
import app.commands.GetSubproject;
import app.commands.GetUsers;
import app.commands.PostSubproject;
import app.repository.InMemoryProjectRepo;

public class CommandParserTest {

	CommandParser parser = new CommandParser();
	
	@Test
	public void shouldReturnAGetProjectWorkersCommand() throws CommandParserException
	{
		InMemoryProjectRepo repo = new RepositoryConstructor().constructProjectRepository();
		repo.getProjectById(1).setManager(new Leader("str", 0, 0, 0, 0));
		parser.registerCommand("GET", "/project/{pid}/{type}",	new GetProjectWorkers.Factory(repo));
		assertTrue(parser.getCommand("GET", "/project/1/manager") instanceof GetProjectWorkers);
	}
	
	@Test
	public void shouldReturnAGetSubprojectCommand() throws CommandParserException
	{
		RepositoryConstructor constructor = new RepositoryConstructor();
		parser.registerCommand("GET", "/project/{pid}/subproject", new PostSubproject.Factory(
				constructor.constructUserRepository(), constructor.constructProjectRepository()));
		assertTrue(parser.getCommand("GET", "/project/3/subproject") instanceof GetSubproject);
	}
	
	@Test
	public void shouldReturnAGetUserCommand() throws CommandParserException {
		parser.registerCommand("GET", "/users",	new GetUsers.Factory(new RepositoryConstructor().constructUserRepository()));
		assertTrue(parser.getCommand("GET" , "/users") instanceof GetUsers);
	}

}
