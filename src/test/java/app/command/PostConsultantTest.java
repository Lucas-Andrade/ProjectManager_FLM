package app.command;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import utils.Local;
import utils.Project;
import app.RepositoryConstructor;
import app.commands.DeleteProjects;
import app.commands.PostProjects;
import app.repository.InMemoryProjectRepo;
import app.repository.InMemoryUserRepo;

public class PostConsultantTest {

	private Map<String, String> parameters;
	private InMemoryUserRepo uRepo = RepositoryConstructor.constructUserRepository();
	private InMemoryProjectRepo pRepo;
	
	@Before
	public void constructNewProjectRepo()
	{
		pRepo = RepositoryConstructor.constructProjectRepository();
		parameters = new HashMap<String, String>();
		parameters.put("loginName", "admin");
		parameters.put("loginPassword", "admin");
	}
	


}
