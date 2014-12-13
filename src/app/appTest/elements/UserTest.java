package app.appTest.elements;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.appTest.RepositoryConstructor;
import app.elements.User;

public class UserTest {

	private RepositoryConstructor constructor = new RepositoryConstructor();
	private User user1;
	private User user2;
	
	@Before
	public void constructUsers()
	{
		user1 = constructor.constructUser(1);
		user2 = constructor.constructUser(2);
	}
	
	@Test
	public void shouldNotBeEqual() {
		assertFalse(user1.equals(user2));
	}
	
	@Test
	public void shouldBeEqual()
	{
		assertTrue(user1.equals(constructor.constructUser(1)));
	}

}
