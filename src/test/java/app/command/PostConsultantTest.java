package app.command;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

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
	public void shouldDeleteTheProject3And4() throws Exception 
	{
		parameters.put("pid", "3");
		new PostConsultant(uRepo, wRepo, parameters).call();

	}

}
