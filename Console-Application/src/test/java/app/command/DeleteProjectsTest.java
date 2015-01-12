package app.command;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import commands.DeleteProjects;

import app.RepositoryConstructor;
import app.repository.InMemoryProjectRepo;
import app.repository.InMemoryUserRepo;

public class DeleteProjectsTest {
	
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
	public void shouldDeleteTheProject3And4() throws Exception 
	{
		parameters.put("pid", "3");
		new DeleteProjects(uRepo, pRepo, parameters).call();
		assertNull(pRepo.getProjectById(3));
		assertNull(pRepo.getProjectById(4));
		assertTrue(pRepo.getProjectById(1) != null);
		assertTrue(pRepo.getProjectById(2) != null);
		assertTrue(pRepo.getProjectById(5) != null);
	}
	
	@Test
	public void shouldDeleteAllProjects() throws Exception 
	{
		parameters.put("pid", "3");
		pRepo.getProjectById(3).addProject(pRepo.getProjectById(1));
		pRepo.getProjectById(1).addProject(pRepo.getProjectById(2));
		pRepo.getProjectById(2).addProject(pRepo.getProjectById(5));
		
		new DeleteProjects(uRepo, pRepo, parameters).call();
		assertNull(pRepo.getProjectById(1));
		assertNull(pRepo.getProjectById(2));
		assertNull(pRepo.getProjectById(3));
		assertNull(pRepo.getProjectById(4));
		assertNull(pRepo.getProjectById(5));
	}
	
}

