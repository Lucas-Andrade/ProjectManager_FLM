package app.command;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import parser_commands.PatchUser;
import app.RepositoryConstructor;
import app.repository.InMemoryUserRepo;

public class PatchUserTest {

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
	public void shouldPatchPassword() throws Exception 
	{
		parameters.put("username", "user3");
		parameters.put("oldPassword", "userPass3");
		parameters.put("newPassword", "p943HT+43miD");
		new PatchUser(uRepo, parameters).call();
		assertEquals(uRepo.getUserByUsername("user3").getLoginPassword(), "p943HT+43miD");
	}
	
	@Test
	public void shouldNotPatchAPasswordWithLessThan4Characters() throws Exception
	{
		parameters.put("username", "user2");
		parameters.put("oldPassword", "userPass2");
		parameters.put("newPassword", "123");
		new PatchUser(uRepo, parameters).call();
		assertEquals(uRepo.getUserByUsername("user2").getLoginPassword(), "userPass2");
	}

}
