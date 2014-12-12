package app.appTest.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.appTest.RepositoryConstructor;
import app.repository.InMemoryUserRepo;
import app.repository.InMemoryWorkerRepo;

public class InMemoryWorkerRepoTest {

	private InMemoryWorkerRepo repo;
	private RepositoryConstructor constructor;
	
	@Before
	public void constructARepository()
	{
		repo = new InMemoryWorkerRepo();
		repo.removeAll();
		constructor = new RepositoryConstructor();
		
		repo = constructor.constructWorkerRepo();
	}
	
	@Test
	public void shouldNotAddAWorkerWithRepeatedCID()
	{
		assertFalse(repo.addConsultant(constructor.constructConsultant(5)));
		assertFalse(repo.addManager(constructor.constructLeader(1)));
	}
	
	
	
//	@Test
//	public void visualTest() {
//		System.out.println(repo.toString());
//	}

}
