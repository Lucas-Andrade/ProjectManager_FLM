package app.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utils.Consultant;
import utils.Leader;
import app.AppElement;
import app.RepositoryConstructor;
import app.repository.InMemoryWorkerRepo;

/**
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class InMemoryWorkerRepoTest {

	private InMemoryWorkerRepo repo;
	
	@Before
	public void constructARepository()
	{
		repo = new InMemoryWorkerRepo();
		repo.removeAll();
		repo = RepositoryConstructor.constructWorkerRepo();
	}
	
	@Test
	public void shouldNotAddAWorkerWithRepeatedCID()
	{
		repo.addConsultant(RepositoryConstructor.constructConsultant(5));
		repo.addManager(RepositoryConstructor.constructLeader(1));
		
		assertFalse(repo.addConsultant(RepositoryConstructor.constructConsultant(5)));
		assertFalse(repo.addManager(RepositoryConstructor.constructLeader(1)));
	}
	
	@Test
	public void shouldAddTheNewUser()
	{
		assertTrue(repo.addConsultant(RepositoryConstructor.constructConsultant(52365)));
	}
	
	@Test
	public void shouldGetTheCorrectConsultant()
	{
		Consultant cons = RepositoryConstructor.constructConsultant(52365);
		repo.addConsultant(cons);
		
		Consultant cons2 = repo.getConsultantByID(52365);
		
		assertEquals(cons, cons2);
	}
	
	@Test
	public void shouldGetTheCorrectManager()
	{
		Leader lead = RepositoryConstructor.constructLeader(52365);
		repo.addConsultant(lead);
		
		Leader lead2 = repo.getManagerByID(52365);
		
		assertEquals(lead, lead2);
	}
	
	@Test
	public void cannotReturnAConsultantThatIsNotThere()
	{
		assertNull(repo.getConsultantByID(52365));
	}
	
	@Test
	public void cannotReturnAManagerThatIsNotThere()
	{
		assertNull(repo.getManagerByID(52365));
	}
	
	@Test
	public void shouldRemoveAllWorkers()
	{
		repo.removeAll();
		assertEquals(0, repo.size());
	}
	
	@Test
	public void shouldReturnTheWholeRepository() {
		AppElement[] rep = repo.getAll();
		StringBuilder builder = new StringBuilder();
		
		for(AppElement elem : rep)
			builder.append(elem.toString()).append("\n");
		
		assertEquals(repo.toString(), builder.toString());
		
//		System.out.println(repo.toString());
	}


//	@Test
//	public void visualTest()
//	{
//		System.out.println(repo.getJson().toString());
//	}
}
