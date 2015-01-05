package app.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.RepositoryConstructor;
import app.elements.Admin;
import app.elements.AppElement;
import app.elements.UserInterface;
import app.repository.InMemoryUserRepo;
/**
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class InMemoryUserRepoTest {

	private InMemoryUserRepo repo;
	private RepositoryConstructor constructor;
	
	@Before
	public void constructARepository()
	{
		repo = new InMemoryUserRepo();
		constructor = new RepositoryConstructor();
		
		repo = constructor.constructUserRepository();
	}
	
	@Test
	public void shouldGetTheCorrectUser()
	{
		UserInterface user1 = repo.getUserByUsername("user1");
		UserInterface user2 = repo.getUserByUsername("user2");
		
		UserInterface user1_ = constructor.constructUser(1);
		UserInterface user2_ = constructor.constructUser(2);
		
		assertTrue(user1_.equals(user1));
		assertTrue(user2_.equals(user2));
	}
	
	@Test
	public void thePasswordShouldBeCorrectForTheUser()
	{
		assertTrue(repo.isPasswordCorrectForUser("user1", "userPass1"));
		assertTrue(repo.isPasswordCorrectForUser("user2", "userPass2"));
		assertTrue(repo.isPasswordCorrectForUser("user3", "userPass3"));
		assertTrue(repo.isPasswordCorrectForUser("user4", "userPass4"));
		
		assertFalse(repo.isPasswordCorrectForUser("user1", "usewth"));
		assertFalse(repo.isPasswordCorrectForUser("user2", "htrsg"));
		assertFalse(repo.isPasswordCorrectForUser("user3", "qthyik"));
		assertFalse(repo.isPasswordCorrectForUser("user4", "yrjfvb"));
	}
	
	@Test
	public void shouldCorrectlyResetTheRepositoryLeavingOnlyTheAdmin()
	{
		repo.removeAll();
		assertEquals(1, repo.size());
		assertTrue(repo.getAll()[0] instanceof Admin);
	}
	
	@Test
	public void shouldNotAddAUserWithUsernameAlreadyUsed()
	{
		assertFalse(repo.addUser(constructor.constructUser(1)));
	}
	
	@Test
	public void shouldAddAUser()
	{
		assertTrue(repo.addUser(constructor.constructUser(25489)));
	}

	@Test
	public void shouldReturnTheWholeRepository()
	{
		AppElement[] rep = repo.getAll();
		StringBuilder builder = new StringBuilder();
		
		for(AppElement elem : rep)
			builder.append(elem.toString()).append("\n");
		
		assertEquals(repo.toString(), builder.toString());
		
//		System.out.println(repo.toString());
	}
	
	@Test
	public void visualTest()
	{
		System.out.println(repo.getJson().toString());
	}
}
