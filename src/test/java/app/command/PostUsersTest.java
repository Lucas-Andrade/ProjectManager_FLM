package app.command;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import app.RepositoryConstructor;
import app.commands.PostUsers;
import app.elements.User;
import app.repository.InMemoryUserRepo;

public class PostUsersTest {

	private Map<String, String> parameters;
	private InMemoryUserRepo uRepo;
	
	@Before
	public void constructNewProjectRepo()
	{
		uRepo = RepositoryConstructor.constructUserRepo();
		parameters = new HashMap<String, String>();
		parameters.put("loginName", "admin");
		parameters.put("loginPassword", "admin");
	}
	
	@Test
	public void shouldPostTheCorrectUser() throws Exception 
	{
		parameters.put("username", "maianame");
		parameters.put("password", "1234");
		parameters.put("email", "maia@mail.com");
		parameters.put("fullname", "Filipe%20Maia");
		new PostUsers(uRepo, parameters).call();
		
		assertTrue(uRepo.getUserByUsername("maianame").equals(
				new User("maianame", "1234", "maia@mail.com", "Filipe%20Maia")));
	}
	
	@Test
	public void shouldNotPostAUserWithRepeatedUserName() throws Exception
	{
		parameters.put("username", "user1");
		parameters.put("password", "1234");
		parameters.put("email", "maia@mail.com");
		parameters.put("fullname", "Filipe%20Maia");
		new PostUsers(uRepo, parameters).call();
		
		assertTrue(uRepo.getUserByUsername("user1").equals(RepositoryConstructor.constructUser(1)));
	}
	
	@Test
	public void shouldNotAddAUserWithInvalidEmail() throws Exception
	{
		parameters.put("username", "maianame");
		parameters.put("password", "1234");
		parameters.put("email", "maiamail.com");
		parameters.put("fullname", "Filipe%20Maia");
		new PostUsers(uRepo, parameters).call();
		
		assertNull(uRepo.getUserByUsername("maianame"));
	}
	
	@Test
	public void shouldNotAddAUserWithAPasswordThatHasLessThan4Characters() throws Exception
	{
		parameters.put("username", "maianame");
		parameters.put("password", "123");
		parameters.put("email", "maia@mail.com");
		parameters.put("fullname", "Filipe%20Maia");
		new PostUsers(uRepo, parameters).call();
		
		assertNull(uRepo.getUserByUsername("maianame"));
	}

}
