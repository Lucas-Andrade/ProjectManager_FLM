package app.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import app.elements.Admin;
import app.elements.DatabaseElements;
import app.elements.User;
import app.elements.UserInterface;

public class InMemoryUserRepo extends InMemoryRepo<User> implements UserRepository {
	
	Map<String, UserInterface> users = new HashMap<String, UserInterface>();
	
	@Override
	public UserInterface getUserByUsername(String loginName)
	{
		for (DatabaseElements user : getDatabaseElements())
			if (((User) user).getLoginName().equals(loginName))
				return (UserInterface) user;

		return null;
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
		}
		
		return userArray;
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
	public void reset() {
		users.clear();
		UserInterface admin = new Admin();
		users.put("admin", admin);
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
	
	
	
}
