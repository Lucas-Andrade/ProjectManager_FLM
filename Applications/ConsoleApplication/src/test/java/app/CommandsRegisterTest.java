package app;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import parser.CommandParser;
import parserCommands.DeleteProjects;
import parserCommands.GetProjectWorkers;
import parserCommands.GetProjects;
import parserCommands.GetSubproject;
import parserCommands.GetUser;
import parserCommands.GetUsers;
import parserCommands.Option;
import parserCommands.PatchConsultant;
import parserCommands.PatchProject;
import parserCommands.PatchUser;
import parserCommands.PostConsultant;
import parserCommands.PostProjects;
import parserCommands.PostSubprojects;
import parserCommands.PostUsers;
import parserCommands.PostWorkerInProject;
import parserUtils.CommandParserException;
import utils.Leader;
import app.ConsoleCommandsRegister;
import app.RepositoryBuilder;
import app.repository.ProjectsRepository;
import app.repository.UserRepository;
import app.repository.WorkerRepository;

/**
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class CommandsRegisterTest {

	static CommandParser parser;
	static WorkerRepository wRepo;
	static UserRepository uRepo;
	static ProjectsRepository pRepo;
	
	@BeforeClass
	public static void registerCommandsAndStartRepositories()
			throws CommandParserException {
		parser = CommandParser.register(new ConsoleCommandsRegister(uRepo,
				pRepo, wRepo));
		wRepo = RepositoryBuilder.constructWorkerRepo();
		uRepo = RepositoryBuilder.constructUserRepo();
		pRepo = RepositoryBuilder.constructProjectRepo();
	}

	@Before
	public void setAManager() throws CommandParserException {
		pRepo.getProjectById(1).setManager(new Leader("str", 0, 0, 0, 0));
	}

	@After
	public void newRepos() throws CommandParserException {
		wRepo = RepositoryBuilder.constructWorkerRepo();
		uRepo = RepositoryBuilder.constructUserRepo();
		pRepo = RepositoryBuilder.constructProjectRepo();
	}

	@Test
	public void shouldReturnAGetProjectWorkersCommand()
			throws CommandParserException {
		assertTrue(parser.getCommand("GET", "/projects/1/manager") instanceof GetProjectWorkers);
	}

	@Test
	public void shouldReturnAGetSubprojectCommand()
			throws CommandParserException {
		assertTrue(parser.getCommand("GET", "/projects/3/subproject") instanceof GetSubproject);
	}

	@Test
	public void shouldReturnAGetUserCommand() throws CommandParserException {
		assertTrue(parser.getCommand("GET", "/users/user1") instanceof GetUser);
	}

	@Test
	public void shouldReturnAGetUsersCommand() throws CommandParserException {
		assertTrue(parser.getCommand("GET", "/users") instanceof GetUsers);
	}

	@Test
	public void shouldReturnAPostConsultantCommand()
			throws CommandParserException {
		assertTrue(parser.getCommand("POST", "/consultants",
				"name=maia&priceHour=1") instanceof PostConsultant);
	}

	@Test
	public void shouldReturnAPostConsultantCommandWithBonus()
			throws CommandParserException {
		assertTrue(parser.getCommand("POST", "/consultants",
				"name=maia&priceHour=1&bonus=3.2") instanceof PostConsultant);
	}

	@Test
	public void shouldReturnAPostProjectCommand() throws CommandParserException {
		assertTrue(parser.getCommand("POST", "/projects",
				"latitude=1.2&longitude=2.3&name=local&price=2.3") instanceof PostProjects);
	}

	@Test
	public void shouldReturnAPostSubprojectCommand()
			throws CommandParserException {
		assertTrue(parser.getCommand("POST", "/projects/1/subproject",
				"subPid=5") instanceof PostSubprojects);
	}

	@Test
	public void shouldReturnAPostUsersCommand() throws CommandParserException {
		assertTrue(parser
				.getCommand("POST", "/users",
						"username=maia&password=123&email=maia@gof.com&fullname=Filipe%20Maia") instanceof PostUsers);
	}

	@Test
	public void shouldReturnAPostWorkerInProjectCommand()
			throws CommandParserException {
		assertTrue(parser.getCommand("POST", "/projects/1/consultant", "cid=1") instanceof PostWorkerInProject);
	}

	@Test
	public void shouldReturnAGetProjectCommand() throws CommandParserException {
		assertTrue(parser.getCommand("GET", "/projects/2") instanceof GetProjects);
	}

	@Test
	public void shouldReturnAPatchProjectCommand()
			throws CommandParserException {
		assertTrue(parser.getCommand("PATCH", "/projects/2",
				"loginName=admin&loginPassword=admin&latitude=1.3") instanceof PatchProject);
	}

	@Test
	public void shouldReturnAPatchUserCommand() throws CommandParserException {
		assertTrue(parser
				.getCommand("PATCH", "/users/maia",
						"loginName=maia&loginPassword=1234&oldPassword=1234&newPassword=12345") instanceof PatchUser);
	}

	@Test
	public void shouldReturnAPatchConsultantCommand()
			throws CommandParserException {
		assertTrue(parser.getCommand("PATCH", "/consultants/1",
				"loginName=admin&loginPassword=admin&name=Filipe%20Maia") instanceof PatchConsultant);
	}

	@Test
	public void shouldReturnADeleteProjectCommand()
			throws CommandParserException {
		assertTrue(parser.getCommand("DELETE", "/projects/1") instanceof DeleteProjects);
	}

	@Test
	public void shouldReturnAOptionCommand() throws CommandParserException {
		assertTrue(parser.getCommand("OPTION", "/") instanceof Option);
	}

}
