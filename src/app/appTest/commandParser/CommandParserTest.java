package app.appTest.commandParser;

import static org.junit.Assert.*;

import org.junit.Test;

import utils.Leader;
import app.appTest.RepositoryConstructor;
import app.commandParser.CommandParser;
import app.commandParser.CommandParserException;
import app.commands.GetProjectWorkers;
import app.commands.GetSubproject;
import app.commands.GetUser;
import app.commands.GetUsers;
import app.commands.PostConsultant;
import app.commands.PostProject;
import app.commands.PostSubproject;
import app.commands.PostUsers;
import app.commands.PostWorkerInProject;
import app.repository.ProjectRepository;
import app.repository.UserRepository;
import app.repository.WorkerRepository;

public class CommandParserTest {

	CommandParser parser = new CommandParser();
	WorkerRepository wRepo = new RepositoryConstructor().constructWorkerRepo();
	UserRepository uRepo = new RepositoryConstructor().constructUserRepository();
	ProjectRepository pRepo = new RepositoryConstructor().constructProjectRepository();
	
	@Test
	public void shouldReturnAGetProjectWorkersCommand() throws CommandParserException
	{
		pRepo.getProjectById(1).setManager(new Leader("str", 0, 0, 0, 0));
		parser.registerCommand("GET", "/project/{pid}/{type}",	new GetProjectWorkers.Factory(pRepo));
		assertTrue(parser.getCommand("GET", "/project/1/manager") instanceof GetProjectWorkers);
	}
	
	@Test
	public void shouldReturnAGetSubprojectCommand() throws CommandParserException
	{
		parser.registerCommand("GET", "/project/{pid}/subproject", new GetSubproject.Factory(pRepo));
		assertTrue(parser.getCommand("GET", "/project/3/subproject") instanceof GetSubproject);
	}
	
	@Test
	public void shouldReturnAGetUserCommand() throws CommandParserException
	{
		parser.registerCommand("GET", "/users/{username}", new GetUser.Factory(uRepo));
		assertTrue(parser.getCommand("GET" , "/users/{username}") instanceof GetUser);
	}
	
	@Test
	public void shouldReturnAGetUsersCommand() throws CommandParserException {
		parser.registerCommand("GET", "/users",	new GetUsers.Factory(uRepo));
		assertTrue(parser.getCommand("GET" , "/users") instanceof GetUsers);
	}

	@Test
	public void shouldReturnAPostConsultantCommand() throws CommandParserException
	{
		parser.registerCommand("POST", "/consultant", new PostConsultant.Factory(uRepo, wRepo));
		assertTrue(parser.getCommand("POST", "/consultant", "name=maia&priceHour=1") instanceof PostConsultant);
	}
	
	@Test
	public void shouldReturnAPostConsultantCommandWithBonus() throws CommandParserException
	{
		parser.registerCommand("POST", "/consultant", new PostConsultant.Factory(uRepo, wRepo));
		assertTrue(parser.getCommand("POST", "/consultant", "name=maia&priceHour=1&bonus=3.2") instanceof PostConsultant);
	}
	
	@Test
	public void shouldReturnAPostProjectCommand() throws CommandParserException
	{
		parser.registerCommand("POST", "/project", new PostProject.Factory(uRepo, pRepo));
		assertTrue(parser.getCommand("POST", "/project", "latitude=1.2&longitude=2.3&name=local&price=2.3") instanceof PostProject);
	}
	
	@Test
	public void shouldReturnAPostSubprojectCommand() throws CommandParserException
	{
		parser.registerCommand("POST", "/project/{pid}/subproject", new PostSubproject.Factory(uRepo, pRepo));
		assertTrue(parser.getCommand("POST", "/project/1/subproject", "subPid=5") instanceof PostSubproject);
	}
	
	@Test
	public void shouldReturnAPostUsersCommand() throws CommandParserException
	{
		parser.registerCommand("POST", "/users", new PostUsers.Factory(uRepo));
		assertTrue(parser.getCommand("POST", "/users", "username=maia&password=123&email=maia@gof.com&fullname=Filipe%20Maia") instanceof PostUsers);
	}
	
	@Test
	public void shouldReturnAPostWorkerInProject() throws CommandParserException
	{
		parser.registerCommand("POST", "/project/{pid}/{type}",	new PostWorkerInProject.Factory(uRepo, pRepo, wRepo));
		assertTrue(parser.getCommand("POST", "/project/1/consultant", "cid=1") instanceof PostWorkerInProject);
	}
}
