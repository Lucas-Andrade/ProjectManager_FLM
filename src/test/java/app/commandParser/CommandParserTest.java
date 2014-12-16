package test.java.app.commandParser;

import static org.junit.Assert.*;
import main.java.app.commandParser.CommandParser;
import main.java.app.commandParser.CommandParserException;
import main.java.app.commandParser.InvalidRegisterException;
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
import main.java.app.repository.ProjectRepository;
import main.java.app.repository.UserRepository;
import main.java.app.repository.WorkerRepository;
import main.java.utils.Leader;

import org.junit.Before;
import org.junit.Test;

import test.java.app.RepositoryConstructor;

/**
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class CommandParserTest {

	CommandParser parser = new CommandParser();
	WorkerRepository wRepo = new RepositoryConstructor().constructWorkerRepo();
	UserRepository uRepo = new RepositoryConstructor().constructUserRepository();
	ProjectRepository pRepo = new RepositoryConstructor().constructProjectRepository();
	
	@Before
	public void registerCommands() throws InvalidRegisterException
	{
		pRepo.getProjectById(1).setManager(new Leader("str", 0, 0, 0, 0));
		parser.registerCommand("GET", "/project/{pid}/{type}",	new GetProjectWorkers.Factory(pRepo));
		parser.registerCommand("GET", "/project/{pid}/subproject", new GetSubproject.Factory(pRepo));
		parser.registerCommand("GET", "/users/{username}", new GetUser.Factory(uRepo));
		parser.registerCommand("GET", "/users",	new GetUsers.Factory(uRepo));
		parser.registerCommand("POST", "/consultant", new PostConsultant.Factory(uRepo, wRepo));
		parser.registerCommand("POST", "/consultant", new PostConsultant.Factory(uRepo, wRepo));
		parser.registerCommand("POST", "/project", new PostProject.Factory(uRepo, pRepo));
		parser.registerCommand("POST", "/project/{pid}/subproject", new PostSubproject.Factory(uRepo, pRepo));
		parser.registerCommand("POST", "/users", new PostUsers.Factory(uRepo));
		parser.registerCommand("POST", "/project/{" + PostWorkerInProject.PID
				+ "}/{" + PostWorkerInProject.WTYPE + "}",	new PostWorkerInProject.Factory(uRepo, pRepo, wRepo));
		parser.registerCommand("GET", "/project/{" + GetProject.PID	+ "}", new GetProject.Factory(pRepo));
	}
	
	@Test
	public void shouldReturnAGetProjectWorkersCommand() throws CommandParserException
	{
		assertTrue(parser.getCommand("GET", "/project/1/manager") instanceof GetProjectWorkers);
	}
	
	@Test
	public void shouldReturnAGetSubprojectCommand() throws CommandParserException
	{
		assertTrue(parser.getCommand("GET", "/project/3/subproject") instanceof GetSubproject);
	}
	
	@Test
	public void shouldReturnAGetUserCommand() throws CommandParserException
	{
		assertTrue(parser.getCommand("GET" , "/users/user1") instanceof GetUser);
	}
	
	@Test
	public void shouldReturnAGetUsersCommand() throws CommandParserException {
		assertTrue(parser.getCommand("GET" , "/users") instanceof GetUsers);
	}

	@Test
	public void shouldReturnAPostConsultantCommand() throws CommandParserException
	{
		assertTrue(parser.getCommand("POST", "/consultant", "name=maia&priceHour=1") instanceof PostConsultant);
	}
	
	@Test
	public void shouldReturnAPostConsultantCommandWithBonus() throws CommandParserException
	{
		assertTrue(parser.getCommand("POST", "/consultant", "name=maia&priceHour=1&bonus=3.2") instanceof PostConsultant);
	}
	
	@Test
	public void shouldReturnAPostProjectCommand() throws CommandParserException
	{
		assertTrue(parser.getCommand("POST", "/project", "latitude=1.2&longitude=2.3&name=local&price=2.3") instanceof PostProject);
	}
	
	@Test
	public void shouldReturnAPostSubprojectCommand() throws CommandParserException
	{
		assertTrue(parser.getCommand("POST", "/project/1/subproject", "subPid=5") instanceof PostSubproject);
	}
	
	@Test
	public void shouldReturnAPostUsersCommand() throws CommandParserException
	{
		assertTrue(parser.getCommand("POST", "/users", "username=maia&password=123&email=maia@gof.com&fullname=Filipe%20Maia") instanceof PostUsers);
	}
	
	@Test
	public void shouldReturnAPostWorkerInProjectCommand() throws CommandParserException
	{
		assertTrue(parser.getCommand("POST", "/project/1/consultant", "cid=1") instanceof PostWorkerInProject);
	}
	
	@Test
	public void shouldReturnAGetProjectCommand() throws CommandParserException
	{
		assertTrue(parser.getCommand("GET", "/project/2") instanceof GetProject);
	}
}
