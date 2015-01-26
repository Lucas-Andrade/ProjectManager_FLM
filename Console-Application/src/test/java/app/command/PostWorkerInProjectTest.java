package app.command;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import parserCommands.PostWorkerInProject;
import app.RepositoryBuilder;
import app.repository.InMemoryProjectRepo;
import app.repository.InMemoryUserRepo;
import app.repository.InMemoryWorkerRepo;

public class PostWorkerInProjectTest {

	private Map<String, String> parameters;
	private InMemoryUserRepo uRepo = RepositoryBuilder.constructUserRepo();
	private InMemoryProjectRepo pRepo;
	private InMemoryWorkerRepo wRepo;

	@Before
	public void constructNewProjectRepo()
	{
		pRepo = RepositoryBuilder.constructProjectRepo();
		wRepo = RepositoryBuilder.constructWorkerRepo();
		parameters = new HashMap<String, String>();
		parameters.put("loginName", "admin");
		parameters.put("loginPassword", "admin");
	}
	
	@Test
	public void shouldShouldPostTheWorkerToTheProject() throws Exception 
	{
		parameters.put("pid", "3");
		parameters.put("cid", "1");
		parameters.put("type", "consultant");
		new PostWorkerInProject(uRepo, pRepo, wRepo, parameters).call();
		
		assertTrue(pRepo.getProjectById(3).getTeam().iterator().next()
				.equals(RepositoryBuilder.constructConsultant(1)));
	}
	
	@Test
	public void shouldShouldPostTheManagerToTheProject() throws Exception 
	{
		parameters.put("pid", "4");
		parameters.put("cid", "5");
		parameters.put("type", "manager");
		new PostWorkerInProject(uRepo, pRepo, wRepo, parameters).call();
		
		assertTrue(pRepo.getProjectById(4).getManager()
				.equals(RepositoryBuilder.constructLeader(5)));
	}
	
	@Test
	public void shouldReplaceTheManagerIfTheProjectAlreadyHasOne() throws Exception
	{
		pRepo.getProjectById(1).setManager(wRepo.getManagerByID(6));
		parameters.put("pid", "1");
		parameters.put("cid", "5");
		parameters.put("type", "manager");
		new PostWorkerInProject(uRepo, pRepo, wRepo, parameters).call();
		
		assertTrue(pRepo.getProjectById(1).getManager()
				.equals(RepositoryBuilder.constructLeader(5)));
	}
	
	@Test 
	public void cannotPostAWorkerOfUnexistingType() throws Exception
	{
		parameters.put("pid", "3");
		parameters.put("cid", "1");
		parameters.put("type", "rgsrth");
		new PostWorkerInProject(uRepo, pRepo, wRepo, parameters).call();
		
		assertEquals(pRepo.getProjectById(3).getTeam().size(), 0);
		assertNull(pRepo.getProjectById(1).getManager());
	}
	
	@Test
	public void cannotPostAConsultantAsManager() throws Exception
	{
		parameters.put("pid", "3");
		parameters.put("cid", "1");
		parameters.put("type", "manager");
		new PostWorkerInProject(uRepo, pRepo, wRepo, parameters).call();
		
		assertEquals(pRepo.getProjectById(3).getTeam().size(), 0);
		assertNull(pRepo.getProjectById(1).getManager());
	}
	
	@Test
	public void cannotPostAManagerAsConsultant() throws Exception
	{
		parameters.put("pid", "3");
		parameters.put("cid", "5");
		parameters.put("type", "consultant");
		new PostWorkerInProject(uRepo, pRepo, wRepo, parameters).call();
		
		assertEquals(pRepo.getProjectById(3).getTeam().size(), 0);
		assertNull(pRepo.getProjectById(1).getManager());
	}

}
