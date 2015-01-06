package app.command;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import utils.Consultant;
import app.RepositoryConstructor;
import app.commands.PostConsultant;
import app.repository.InMemoryUserRepo;
import app.repository.InMemoryWorkerRepo;

public class PostConsultantTest {

	private Map<String, String> parameters;
	private InMemoryUserRepo uRepo = RepositoryConstructor.constructUserRepository();
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

}
