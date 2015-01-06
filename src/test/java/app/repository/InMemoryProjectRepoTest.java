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
	
	@Before
	public void constructARepository()
	{
		repo = RepositoryConstructor.constructProjectRepository();
	}
	
	@Test
	public void shouldNotAddARepeatedElement() {
		Project proj = RepositoryConstructor.constructProject((int) repo.getNextPID());
		System.out.println(repo.getNextPID());
		assertTrue(repo.addProject(proj));
		assertFalse(repo.addProject(proj));
	}
	
	@Test
	public void cannotRemoveAnElementThatIsNotThere()
	{
		System.out.println(repo.getNextPID());
		Project proj = RepositoryConstructor.constructProject(repo.getNextPID());
		assertFalse(repo.removeProject(proj));
	}
	
	@Test
	public void shouldRemoveTheElement()
	{
		Project proj = RepositoryConstructor.constructProject((int) repo.getNextPID());
		
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
		assertFalse(repo.addProject(RepositoryConstructor.constructProject(2)));
	}
	
	@Test
	public void shouldReturnTheWholeRepository()
	{
		AppElement[] rep = repo.getAll();
		StringBuilder builder = new StringBuilder();
		
		for(AppElement elem : rep)
			builder.append(elem.toString()).append("\n");
		
		assertEquals(repo.toString(), builder.toString());
	}
}
