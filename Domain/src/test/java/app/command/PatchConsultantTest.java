package app.command;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import app.RepositoryConstructor;
import app.commands.PatchConsultant;
import app.repository.InMemoryUserRepo;
import app.repository.InMemoryWorkerRepo;

public class PatchConsultantTest {

	
	private Map<String, String> parameters;
	private InMemoryUserRepo uRepo = RepositoryConstructor.constructUserRepo();
	private InMemoryWorkerRepo wRepo;
	
	@Before
	public void constructNewProjectRepo()
	{
		wRepo = RepositoryConstructor.constructWorkerRepo();
		parameters = new HashMap<String, String>();
		parameters.put("loginName", "admin");
		parameters.put("loginPassword", "admin");
	}
	
	@Test
	public void shouldPatchTheNameOfTheConsultant() throws Exception 
	{
		parameters.put("cid", "3");
		parameters.put("name", "maia");
		new PatchConsultant(uRepo, wRepo, parameters).call();
		
		assertEquals(wRepo.getAWorkerByID(3).getName(), "maia");
	}

	@Test
	public void shouldPatchThePriceHourOfTheConsultant() throws Exception 
	{
		parameters.put("cid", "3");
		parameters.put("priceHour", "10");
		new PatchConsultant(uRepo, wRepo, parameters).call();
		
		assertEquals(wRepo.getAWorkerByID(3).getCostPerHour(), 10, 0.01);
	}

	@Test
	public void shouldNotPatchANegativePricePerHour() throws Exception
	{
		parameters.put("cid", "4");
		parameters.put("priceHour", "-10");
		new PatchConsultant(uRepo, wRepo, parameters).call();
		
		assertEquals(wRepo.getAWorkerByID(4).getCostPerHour(), 4, 0.01);
	}
}
