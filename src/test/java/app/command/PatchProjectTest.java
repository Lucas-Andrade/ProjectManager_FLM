package app.command;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import app.RepositoryConstructor;
import app.commands.PatchProject;
import app.repository.InMemoryProjectRepo;
import app.repository.InMemoryUserRepo;

public class PatchProjectTest {

	private static Map<String, String> parameters = new HashMap<String, String>();
	private InMemoryUserRepo uRepo = RepositoryConstructor.constructUserRepository();
	private InMemoryProjectRepo pRepo;
	
	@BeforeClass
	public static void putValidationParametersInTheParametersMap()
	{
		parameters.put("loginName", "admin");
		parameters.put("loginPassword", "admin");
	}
	
	@Before
	public void constructNewProjectRepo()
	{
		pRepo = RepositoryConstructor.constructProjectRepository();
	}
	
	@Test
	public void shouldPatchTheLocationsLatitude() throws Exception 
	{
		parameters.put("pid", "1");
		parameters.put("latitude", "23.4");
		new PatchProject(uRepo, pRepo, parameters).call();
		assertEquals(pRepo.getProjectById(1).getLocal().getLatitude(), 23.4, 0.0001);
	}
	
	@Test
	public void shouldPatchTheLocationsLongitude() throws Exception 
	{
		parameters.put("pid", "2");
		parameters.put("longitude", "54.7");
		new PatchProject(uRepo, pRepo, parameters).call();
		assertEquals(pRepo.getProjectById(2).getLocal().getLongitude(), 54.7, 0.0001);
	}
	
	@Test
	public void shouldPatchTheLocationsPrice() throws Exception 
	{
		parameters.put("pid", "3");
		parameters.put("price", "9416.3");
		new PatchProject(uRepo, pRepo, parameters).call();
		assertEquals(pRepo.getProjectById(3).getLocal().getCost(), 9416.3, 0.01);
	}
	
	@Test
	public void shouldPatchTheLocationName() throws Exception
	{
		parameters.put("pid", "3");
		parameters.put("name", "Randstad Technologies");
		new PatchProject(uRepo, pRepo, parameters).call();
		assertEquals(pRepo.getProjectById(3).getLocal().getName(), "Randstad Technologies");
	}
	
	@Test
	public void shouldNotPatchAnOutOfBoundsLatitude() throws Exception
	{
		parameters.put("pid", "4");
		parameters.put("latitude", "984564");
		new PatchProject(uRepo, pRepo, parameters).call();
		assertEquals(pRepo.getProjectById(4).getLocal().getLatitude(), 4, 0.0001);
	}

}
