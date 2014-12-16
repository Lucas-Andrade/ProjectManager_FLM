package test.java.app.repository;

import static org.junit.Assert.*;
import main.java.app.elements.DatabaseElement;
import main.java.app.repository.InMemoryWorkerRepo;
import main.java.utils.Consultant;
import main.java.utils.Leader;

import org.junit.Before;
import org.junit.Test;

import test.java.app.RepositoryConstructor;

/**
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
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
		repo.addConsultant(constructor.constructConsultant(5));
		repo.addManager(constructor.constructLeader(1));
		
		assertFalse(repo.addConsultant(constructor.constructConsultant(5)));
		assertFalse(repo.addManager(constructor.constructLeader(1)));
	}
	
	@Test
	public void shouldAddTheNewUser()
	{
		assertTrue(repo.addConsultant(constructor.constructConsultant(52365)));
	}
	
	@Test
	public void shouldGetTheCorrectConsultant()
	{
		Consultant cons = constructor.constructConsultant(52365);
		repo.addConsultant(cons);
		
		Consultant cons2 = repo.getConsultantByID(52365);
		
		assertEquals(cons, cons2);
	}
	
	@Test
	public void shouldGetTheCorrectManager()
	{
		Leader lead = constructor.constructLeader(52365);
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
		DatabaseElement[] rep = repo.getAll();
		StringBuilder builder = new StringBuilder();
		
		for(DatabaseElement elem : rep)
			builder.append(elem.toString()).append("\n");
		
		assertEquals(repo.toString(), builder.toString());
		
		System.out.println(repo.toString());
	}

}