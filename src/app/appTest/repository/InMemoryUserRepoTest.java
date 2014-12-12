package app.appTest.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.appTest.RepositoryConstructor;
import app.repository.InMemoryProjectRepo;

public class InMemoryUserRepoTest {

	private InMemoryProjectRepo repo;
	private RepositoryConstructor constructor;
	
	@Before
	public void constructARepository()
	{
		repo = new InMemoryProjectRepo();
//		repo.removeAll();
		constructor = new RepositoryConstructor();
		
		repo = constructor.constructProjectRepository();
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
