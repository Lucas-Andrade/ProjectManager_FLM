package app.command;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import utils.Project;
import app.RepositoryConstructor;
import app.commands.PostSubprojects;
import app.repository.InMemoryProjectRepo;
import app.repository.InMemoryUserRepo;

public class PostSubprojectsTest {

	private Map<String, String> parameters;
	private InMemoryUserRepo uRepo = RepositoryConstructor.constructUserRepo();
	private InMemoryProjectRepo pRepo;
	
	@Before
	public void constructNewProjectRepo()
	{
		pRepo = RepositoryConstructor.constructProjectRepo();
		parameters = new HashMap<String, String>();
		parameters.put("loginName", "admin");
		parameters.put("loginPassword", "admin");
	}
	
	@Test
	public void shouldPostSubproject2ToProject1() throws Exception 
	{
		parameters.put("pid", "1");
		parameters.put("subPid", "2");
		new PostSubprojects(uRepo, pRepo, parameters).call();
		
		Collection<Project> subprojects = pRepo.getProjectById(1).getContainerProject();
		assertEquals(subprojects.size(), 1);
		assertEquals(subprojects.iterator().next(), RepositoryConstructor.constructProject(2));
	}
	
	@Test
	public void shouldNotAddAProjectThatIsAlreadyASubprojectOfAnother() throws Exception
	{
		parameters.put("pid", "1");
		parameters.put("subPid", "4");
		new PostSubprojects(uRepo, pRepo, parameters).call();
		
		Collection<Project> subprojects = pRepo.getProjectById(1).getContainerProject();
		assertEquals(subprojects.size(), 0);
	}
}
