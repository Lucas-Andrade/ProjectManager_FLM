package app.command;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import utils.Consultant;
import utils.Leader;
import app.RepositoryConstructor;
import app.commands.PostConsultant;
import app.repository.InMemoryUserRepo;
import app.repository.InMemoryWorkerRepo;

public class PostConsultantTest {

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
	public void shouldPostCorrectConsultant() throws Exception 
	{
		parameters.put("name", "maia");
		parameters.put("priceHour", "10");
		new PostConsultant(uRepo, wRepo, parameters).call();
		assertEquals(wRepo.getConsultantByID(7), new Consultant("maia", 10, 0, 7));
	}
	
	@Test
	public void shouldNotPostAConsultantWithNegativePriceHour() throws Exception 
	{
		parameters.put("name", "maia");
		parameters.put("priceHour", "-10");
		new PostConsultant(uRepo, wRepo, parameters).call();
		assertNull(wRepo.getConsultantByID(7));
	}
	
	@Test
	public void shouldPostTheCorrectManager() throws Exception
	{
		parameters.put("name", "maia");
		parameters.put("priceHour", "10");
		parameters.put("bonus", "2");
		new PostConsultant(uRepo, wRepo, parameters).call();
		assertEquals(wRepo.getManagerByID(7), new Leader("maia", 10, 0, 2, 7));
	}
	
	@Test
	public void shouldNotPostAManagerWithNegativePriceHour() throws Exception 
	{
		parameters.put("name", "maia");
		parameters.put("priceHour", "-10");
		parameters.put("bonus", "2");
		new PostConsultant(uRepo, wRepo, parameters).call();
		assertNull(wRepo.getManagerByID(7));
	}
	
	@Test
	public void shouldNotPostAManagerWithNegativeBonus() throws Exception 
	{
		parameters.put("name", "maia");
		parameters.put("priceHour", "10");
		parameters.put("bonus", "-2");
		new PostConsultant(uRepo, wRepo, parameters).call();
		assertNull(wRepo.getManagerByID(7));
	}

}
