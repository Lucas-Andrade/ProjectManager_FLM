package app.appTest.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utils.Project;
import app.appTest.RepositoryConstructor;
import app.repository.InMemoryProjectRepo;

public class InMemoryProjectRepoTest {

	private InMemoryProjectRepo repo = new InMemoryProjectRepo();
	private RepositoryConstructor constructor = new RepositoryConstructor();
	
	@Before
	public void constructARepository()
	{
		repo.removeAll();
		repo = constructor.constructProjectRepository();
	}
	
	@Test
	public void shouldNotAddARepeatedElement() {
		Project proj = constructor.constructProject(3);
		
		assertTrue(repo.addProject(proj));
		assertFalse(repo.addProject(proj));
	}
	
	@Test
	public void cannotRemoveAnElementThatIsNotThere()
	{
		Project proj = constructor.constructProject(3);
		assertFalse(repo.removeName(proj));
	}
	
	@Test
	public void shouldRemoveTheElement()
	{
		Project proj = constructor.constructProject(3);
		
		assertTrue(repo.addProject(proj));
		assertTrue(repo.removeName(proj));
		assertFalse(repo.removeName(proj));
	}
	
	@Test
	public void shouldRemoveAllElements()
	{
		repo.removeAll();
		assertEquals(0, repo.getProjects().size());
	}
	
	@Test
	public void shouldReturnTheCorrectElement()
	{
		Project proj1 = repo.getProjectById(1);
		Project proj2 = repo.getProjectById(2);
		
		assertEquals(1.0, proj1.getCost(), 0.01);
		assertEquals("local1", proj1.getLocal().getName());
		
		assertEquals(2.0, proj2.getCost(), 0.01);
		assertEquals("local2", proj2.getLocal().getName());
	}
	
	@Test
	public void cannotReturnAnElementIfThePIDIsInvalid()
	{
		assertNull(repo.getProjectById(55426));
	}
	
	@Test
	public void visualTest()
	{
		System.out.println(repo.toString());
	}
}
