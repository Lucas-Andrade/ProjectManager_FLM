package app.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONObject;

import app.AppElement;
import app.elements.Admin;
import app.elements.ImmutableAdmin;
import app.elements.User;
import app.elements.IUser;

/**
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class InMemoryUserRepo extends InMemoryRepo<User> implements
		UserRepository {

	/**
	 * {@code Map} that stores the {@code User}s of this repository. The
	 * {@code Key} stores the Username and the {@code Value} stores the
	 * correspondig {@code User}.
	 */
	Map<String, IUser> users = new HashMap<String, IUser>();

	/**
	 * Contructor for {@code InMemoryUserRepo}. Also adds an {@link ImmutableAdmin}
	 * {@link User} to the Repository.
	 */
	public InMemoryUserRepo(){
		users.put("admin", new ImmutableAdmin());
	}

	/**
	 * @see UserRepository#getUserByUsername(String)
	 */
	@Override
	public IUser getUserByUsername(String loginName){
		return users.get(loginName);
	}

	/**
	 * @see Repository#getAll()
	 * 
	 * @return An array of {@link IUser} with all the Users in the
	 *         repository.
	 */
	@Override
	public IUser[] getAll(){
		IUser[] userArray = new IUser[users.size()];

		Set<Entry<String, IUser>> usersSet = users.entrySet();
		int index = 0;

		for (Entry<String, IUser> user : usersSet){
			userArray[index] = user.getValue();
			index++;
		}

		return userArray;
	}

	/**
	 * @see Object#toString()
	 */
	public String toString(){
		StringBuilder builder = new StringBuilder();

		for (AppElement user : getAll()){
			builder.append(user.toString()).append("\n");
		}

		return builder.toString();
	}
	
	/**
	 * @see UserRepository#isPasswordCorrectForUser(String, String)
	 */
	@Override
	public boolean isPasswordCorrectForUser(String username, String userPassword){
		IUser user = users.get(username);

		if (user == null){
			return false;
		}
		if (user.getLoginPassword().equals(userPassword)){
			return true;
		}
			return false;
	}

	/**
	 * Removes all Users from the Repository, then adds an
	 * {@link ImmutableAdmin} {@code User} to the empty Rempository.
	 * 
	 * @see Repository#removeAll()
	 */
	@Override
	public void removeAll(){
		users.clear();
		users.put("admin", new ImmutableAdmin());
	}

	/**
	 * @see UserRepository#addUser(User)
	 */
	@Override
	public boolean addUser(IUser user){

		String username = user.getLoginName();

		if (users.containsKey(username)){
			return false;
		} else{
			users.put(username, user);
			return true;
		}
	}

	/**
	 * @see Repository#size()
	 */
	public int size(){
		return users.size();
	}

	/**
	 * @see UserRepository#addAdmin(String, String)
	 */
	@Override
	public boolean addAdmin(String username, String password){
		return addUser(new Admin(username, password));
	}

	@Override
	public JSONObject getJson() {
		AppElement[] allElements = getAll();
		JSONObject json = new JSONObject();
		for (AppElement ele : allElements){
			json.accumulate("All users", ele.getJson());
		}
		return json;
	}
}
