package app.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utils.Consultant;
import utils.Leader;
import app.AppElement;
import app.RepositoryConstructor;
import app.elements.mutable.ConsultantCreationDescriptor;
import app.elements.mutable.LeaderCreationDescriptor;
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
	public void shouldAddTheNewWorker()
	{
		Consultant cons = RepositoryConstructor.constructConsultant(52365);
		assertNotNull(repo.addConsultant(new ConsultantCreationDescriptor(cons.getName(), cons.getCostPerHour(), cons.getWorkerHours())));
	}
	
	@Test
	public void shouldGetTheCorrectConsultant()
	{
		Consultant cons = RepositoryConstructor.constructConsultant(1);
		repo.addConsultant(new ConsultantCreationDescriptor(cons.getName(), cons.getCostPerHour(), cons.getWorkerHours()));
		
		Consultant cons2 = repo.getConsultantByID(1);
		
		assertEquals(cons, cons2);
	}
	
	@Test
	public void shouldGetTheCorrectManager()
	{
		Leader lead = RepositoryConstructor.constructLeader(5);
		repo.addManager(new LeaderCreationDescriptor(lead.getName(), lead.getCostPerHour(), lead.getWorkerHours(), lead.getBonus()));
		
		Leader lead2 = repo.getManagerByID(5);
		
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
