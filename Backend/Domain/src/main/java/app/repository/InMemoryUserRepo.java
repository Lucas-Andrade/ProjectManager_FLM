package app.repository;

import java.util.Collections;
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
import app.elements.mutable.UserCreationDescriptor;

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
	private static final Map<String, IUser> users = Collections.synchronizedMap(new HashMap<String, IUser>());

	/**
	 * The lock to be used in instructions where
	 * {@code this#NEXT_PID_TO_BE_USED} cannot be modified by concurrent
	 * threads.
	 */
	private final Object pidLock;

	/**
	 * The lock to be used when {@code this#NEXT_PID_TO_BE_USED} is reset, by
	 * all instructions that associate a Project to a PID in the repository,
	 * thus preventing the possibility of a Project being added to this
	 * repository with a PID higher than the {@code this#NEXT_PID_TO_BE_USED}.
	 */
	private final Object pidResetLock;

	/**
	 * Constructor for {@code InMemoryUserRepo}. Also adds an {@link ImmutableAdmin}
	 * {@link User} to the Repository.
	 */
	public InMemoryUserRepo(){
		users.put("admin", new Admin("admin", "admin"));
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
	 * {@link ImmutableAdmin} {@code User} to the empty Repository.
	 * 
	 * @see Repository#removeAll()
	 */
	@Override
	public void removeAll(){
		users.clear();
		users.put("admin", new ImmutableAdmin());
	}

	/**
	 * @throws Exception 
	 * @see UserRepository#addUser(UserCreationDescriptor)
	 */
	@Override
	public boolean addUser(UserCreationDescriptor userCreationDescriptor) {
		String username = userCreationDescriptor.getLoginName();		
		IUser user = userCreationDescriptor.build();
			
		if (user != null && users.putIfAbsent(username, user)==null){
			return true;
		}
			
		return false;
	}
	
	/**
	 * @see Repository#size()
	 */
	public int size(){
		return users.size();
	}

	/**
	 * Returns all the users in from the repository in a JSONObject.
	 */
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
