package app.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import app.elements.Admin;
import app.elements.DatabaseElement;
import app.elements.ImmutableAdmin;
import app.elements.User;
import app.elements.UserInterface;

public class InMemoryUserRepo extends InMemoryRepo<User> implements UserRepository {
	
	Map<String, UserInterface> users = new HashMap<String, UserInterface>();
	
	public InMemoryUserRepo()
	{
		users.put("admin",  new ImmutableAdmin());
	}
	
	@Override
	public UserInterface getUserByUsername(String loginName)
	{
		return users.get(loginName);
	}

	@Override
	public UserInterface[] getAllUsers() 
	{
		UserInterface[] userArray = new UserInterface[users.size()];
		
		Set<Entry<String, UserInterface>> usersSet = users.entrySet();
		int index = 0;
		
		for(Entry<String, UserInterface> user : usersSet)
		{
			userArray[index] = user.getValue();
			index++;
		}
		
		return userArray;
	}
	
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		for(UserInterface user : getAllUsers())
		{
			builder.append(user.toString()).append("\n");
		}
		
		return builder.toString();
	}

	@Override
	public boolean isPasswordCorrectForUser(String username, String userPassword) 
	{
		UserInterface user = users.get(username);
		
		if(user == null)
			return false;
		
		if(user.getLoginPassword().equals(userPassword))
			return true;
		else
			return false;
	}

	@Override
	public void removeAll() {
		users.clear();
		users.put("admin",  new ImmutableAdmin());
	}

	@Override
	public boolean addUser(User user) {
		
		String username = user.getLoginName();
		
		if(users.containsKey(username))
		{
			return false;
		}
		else
		{
			users.put(username, user);
			return true;
		}
	}
	
	public int size()
	{
		return users.size();
	}
	
	@Override
	public boolean addAdmin(String username, String password)
	{
		return addUser(new Admin(username, password));
	}

	@Override
	public DatabaseElement[] getAll() {
		return (DatabaseElement[]) getAllUsers();
	}
}
