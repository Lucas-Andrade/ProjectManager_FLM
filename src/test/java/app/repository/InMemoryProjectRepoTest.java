package app.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utils.Project;
import app.RepositoryConstructor;
import app.elements.AppElement;
import app.repository.InMemoryProjectRepo;
/**
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class InMemoryProjectRepoTest {

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
	public void shouldNotAddARepeatedElement() {
		Project proj = constructor.constructProject((int) repo.getNextPID());
		
		assertTrue(repo.addProject(proj));
		assertFalse(repo.addProject(proj));
	}
	
	@Test
	public void cannotRemoveAnElementThatIsNotThere()
	{
		Project proj = constructor.constructProject((int) repo.getNextPID());
		assertFalse(repo.removeProject(proj));
	}
	
	@Test
	public void shouldRemoveTheElement()
	{
		Project proj = constructor.constructProject((int) repo.getNextPID());
		
		assertTrue(repo.addProject(proj));
		assertTrue(repo.removeProject(proj));
		assertFalse(repo.removeProject(proj));
	}
	
	@Test
	public void shouldRemoveAllElements()
	{
		repo.removeAll();
		assertEquals(0, repo.size());
	}
	
	@Test
	public void shouldReturnTheCorrectElement()
	{
		Project proj2 = repo.getProjectById(2);
		Project proj3 = repo.getProjectById(3);
		
		assertEquals(2.0, proj2.getCost(), 0.01);
		assertEquals("local2", proj2.getLocal().getName());
		
		assertEquals(3.0, proj3.getCost(), 0.01);
		assertEquals("local3", proj3.getLocal().getName());
	}
	
	@Test
	public void cannotReturnAnElementIfThePIDIsInvalid()
	{
		assertNull(repo.getProjectById(55426));
	}
	
	@Test
	public void shouldNotAddProjectWithTheSamePID()
	{
		assertFalse(repo.addProject(constructor.constructProject(2)));
	}
	
	@Test
	public void shouldReturnTheWholeRepository()
	{
		AppElement[] rep = repo.getAll();
		StringBuilder builder = new StringBuilder();
		
		for(AppElement elem : rep)
			builder.append(elem.toString()).append("\n");
		
		assertEquals(repo.toString(), builder.toString());
		
		System.out.println(repo.toString());
	}
	
	@Test
	public void visualTest()
	{
		System.out.println(repo.getJson().toString());
	}
}
