package app.elements;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.RepositoryConstructor;
import app.elements.User;

/**
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class UserTest {

	private User user1;
	private User user2;
	
	@Before
	public void constructUsers(){
		user1 = RepositoryConstructor.constructUser(1);
		user2 = RepositoryConstructor.constructUser(2);
	}
	
	@Test
	public void shouldNotBeEqual() {
		assertFalse(user1.equals(user2));
	}
	
	@Test
	public void shouldBeEqual(){
		assertTrue(user1.equals(RepositoryConstructor.constructUser(1)));
	}
}
