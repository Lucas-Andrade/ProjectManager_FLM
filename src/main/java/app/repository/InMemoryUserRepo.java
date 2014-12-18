package app.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONObject;

import app.elements.Admin;
import app.elements.DatabaseElement;
import app.elements.ImmutableAdmin;
import app.elements.User;
import app.elements.UserInterface;

/**
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class InMemoryUserRepo extends InMemoryRepo<User> implements
		UserRepository
{

	/**
	 * {@code Map} that stores the {@code User}s of this repository. The
	 * {@code Key} stores the Username and the {@code Value} stores the
	 * correspondig {@code User}.
	 */
	Map<String, UserInterface> users = new HashMap<String, UserInterface>();

	/**
	 * Contructor for {@code InMemoryUserRepo}. Also adds an {@link Admin}
	 * {@link User} to the Repository.
	 */
	public InMemoryUserRepo()
	{
		users.put("admin", new ImmutableAdmin());
	}

	/**
	 * @see UserRepository#getUserByUsername(String)
	 */
	@Override
	public UserInterface getUserByUsername(String loginName)
	{
		return users.get(loginName);
	}

	/**
	 * @see Repository#getAll()
	 * 
	 * @return An array of {@link UserInterface} with all the Users in the
	 *         repository.
	 */
	@Override
	public DatabaseElement[] getAll()
	{
		UserInterface[] userArray = new UserInterface[users.size()];

		Set<Entry<String, UserInterface>> usersSet = users.entrySet();
		int index = 0;

		for (Entry<String, UserInterface> user : usersSet)
		{
			userArray[index] = user.getValue();
			index++;
		}

		return userArray;
	}

	/**
	 * @see Object#toString()
	 */
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		for (DatabaseElement user : getAll())
		{
			builder.append(user.toString()).append("\n");
		}

		return builder.toString();
	}

	
	/**
	 * This method represent the worker's information in HTLM format 
	 */
	public String toHtml()
	{
		StringBuilder builder = new StringBuilder();		
		builder.append("<html>");
		
		for (DatabaseElement user : getAll())
		{
			builder.append(user.toHtml()).append("\n");
		}
		builder.append("</html>");
		
		return builder.toString();
	}
	
	
	/**
	 * This method represent the worker's information in Json's format 
	 */
	public String toJson()
	{
		//instancia um novo JSONObject 
		JSONObject allUsers = new JSONObject(); 
		
		StringBuilder builder = new StringBuilder();		
		
		for (DatabaseElement user : getAll())
		{
			builder.append(user.toJson()).append("\n");
		}
		
		//serializa para uma string e imprime 
		String json_string = allUsers.toString();
		
		return json_string;
	}
	
	
	
	
	/**
	 * @see UserRepository#isPasswordCorrectForUser(String, String)
	 */
	@Override
	public boolean isPasswordCorrectForUser(String username, String userPassword)
	{
		UserInterface user = users.get(username);

		if (user == null)
			return false;

		if (user.getLoginPassword().equals(userPassword))
			return true;
		else
			return false;
	}

	/**
	 * Removes all Users from the Repository, then adds an
	 * {@link ImmutableAdmin} {@code User} to the empty Rempository.
	 * 
	 * @see Repository#removeAll()
	 */
	@Override
	public void removeAll()
	{
		users.clear();
		users.put("admin", new ImmutableAdmin());
	}

	/**
	 * @see UserRepository#addUser(User)
	 */
	@Override
	public boolean addUser(User user)
	{

		String username = user.getLoginName();

		if (users.containsKey(username))
		{
			return false;
		} else
		{
			users.put(username, user);
			return true;
		}
	}

	/**
	 * @see Repository#size()
	 */
	public int size()
	{
		return users.size();
	}

	/**
	 * @see UserRepository#addAdmin(String, String)
	 */
	@Override
	public boolean addAdmin(String username, String password)
	{
		return addUser(new Admin(username, password));
	}

}
