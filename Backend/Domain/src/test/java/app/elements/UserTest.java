package app.elements;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.junit.Before;
import org.junit.Test;

import utils.Leader;
import app.AppElement;
import app.RepositoryConstructor;
import app.elements.User;
import app.repository.ProjectsRepository;
import app.repository.WorkerRepository;

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
		
		ProjectsRepository repo = RepositoryConstructor.constructProjectRepo();
		WorkerRepository wrepo = RepositoryConstructor.constructWorkerRepo();
		repo.getProjectById(4).addProject(repo.getProjectById(1));
		repo.getProjectById(4).addProject(repo.getProjectById(2));
		repo.getProjectById(4).addProject(repo.getProjectById(5));
		repo.getProjectById(4).addWorker(wrepo.getAWorkerByID(1));
		repo.getProjectById(4).addWorker(wrepo.getAWorkerByID(2));
		repo.getProjectById(4).addWorker(wrepo.getAWorkerByID(3));
		repo.getProjectById(3).addWorker(wrepo.getAWorkerByID(1));
		repo.getProjectById(2).addWorker(wrepo.getAWorkerByID(3));
		repo.getProjectById(4).setManager((Leader)wrepo.getAWorkerByID(5));
		
		System.out.println(toString(repo.getAll()));
	}

	private static String toString(AppElement[] result) {
		
		String toReturn;
		if(result.length == 1) {
			return result[0].getJson().toString();
		} else {
			JSONArray array = new JSONArray();
			for(int i = 0; i < result.length; i++){
				array.put(result[i].getJson());
			}
			toReturn = array.toString();
		}
		return toReturn;
	}
}
