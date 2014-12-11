package app.appTest;

import utils.Local;
import utils.Project;
import app.elements.User;
import app.repository.InMemoryProjectRepo;
import app.repository.InMemoryUserRepo;
import app.repository.InMemoryWorkerRepo;

public class RepositoryConstructor {

	public static InMemoryProjectRepo constructProjectRepository()
	{
		Project project1 = constructProject(1);
		Project project2 = constructProject(2);
		Project project3 = constructProject(3);
		Project project4 = constructProject(4);
		
		InMemoryProjectRepo repo = new InMemoryProjectRepo();
		
		repo.addProject(project1);
		repo.addProject(project2);
		repo.addProject(project3);
		repo.addProject(project4);
		
		return repo;
	}
	
	
	public static InMemoryUserRepo constructUserRepository()
	{
		User user1 = constructUser(1);
		User user2 = constructUser(2);
		User user3 = constructUser(3);
		User user4 = constructUser(4);
		
		InMemoryUserRepo repo = new InMemoryUserRepo();
		repo.reset();
		repo.addUser(user1);
		repo.addUser(user2);
		repo.addUser(user3);
		repo.addUser(user4);
		
		return repo;
	}
	
//	public static InMemoryWorkerRepo constructWorkerRepo()
//	{
//		
//	}
	
	public static User constructUser(int i)
	{
		return new User("user" + i, "userPass" + i, "user" + i + "@email.com", "User " + i);
	}
	
	public static Project constructProject(int i)
	{
		return new Project(constructLocal(i), i);
	}
	
	public static Local constructLocal(int i)
	{
		return new Local(i, i, "local" + i, i*2);
	}
}
