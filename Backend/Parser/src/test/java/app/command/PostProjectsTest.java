package app.command;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import parserCommands.PostProjects;
import utils.Local;
import utils.Project;
import app.RepositoryBuilder;
import app.repository.InMemoryProjectRepo;
import app.repository.InMemoryUserRepo;

public class PostProjectsTest {

	private Map<String, String> parameters;
	private InMemoryUserRepo uRepo = RepositoryBuilder.constructUserRepo();
	private InMemoryProjectRepo pRepo;
	
	@Before
	public void constructNewProjectRepo()
	{
		pRepo = RepositoryBuilder.constructProjectRepo();
		parameters = new HashMap<String, String>();
		parameters.put("loginName", "admin");
		parameters.put("loginPassword", "admin");
	}
	
	@Test
	public void shouldPostTheCorrectProject() throws Exception 
	{
		parameters.put("latitude", "50");
		parameters.put("longitude", "100");
		parameters.put("price", "520.1");
		parameters.put("name", "Randstad");
		new PostProjects(uRepo, pRepo, parameters).call();
		
		assertEquals(pRepo.getProjectById(6), 
				new Project(new Local(50, 100, "Randstad", 520.1), 6));
	}
	
	@Test
	public void shouldNotPostProjectWithPriceOutOfBounds() throws Exception
	{
		parameters.put("latitude", "50");
		parameters.put("longitude", "100");
		parameters.put("price", "-10");
		parameters.put("name", "Randstad");
		new PostProjects(uRepo, pRepo, parameters).call();
		
		assertNull(pRepo.getProjectById(6));
	}

	@Test
	public void shouldNotPostProjectWithLatitudeOutOfBounds() throws Exception
	{
		parameters.put("latitude", "518198");
		parameters.put("longitude", "100");
		parameters.put("price", "520.1");
		parameters.put("name", "Randstad");
		new PostProjects(uRepo, pRepo, parameters).call();
		
		assertNull(pRepo.getProjectById(6));
	}
	
	@Test
	public void shouldNotPostProjectWithLongitudeOutOfBounds() throws Exception
	{
		parameters.put("latitude", "50");
		parameters.put("longitude", "9952951");
		parameters.put("price", "520.1");
		parameters.put("name", "Randstad");
		new PostProjects(uRepo, pRepo, parameters).call();
		
		assertNull(pRepo.getProjectById(6));
	}
}
