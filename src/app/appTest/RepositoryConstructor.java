package app.appTest;

import utils.Consultant;
import utils.Leader;
import utils.Local;
import utils.Project;
import app.elements.User;
import app.repository.InMemoryProjectRepo;
import app.repository.InMemoryUserRepo;
import app.repository.InMemoryWorkerRepo;

public class RepositoryConstructor {

	public InMemoryProjectRepo constructProjectRepository()
	{
		Project project1 = constructProject(1);
		Project project2 = constructProject(2);
		Project project3 = constructProject(3);
		Project project4 = constructProject(4);
		Project project5 = constructProject(5);
		
		project4.addProject(project5);
		
		InMemoryProjectRepo repo = new InMemoryProjectRepo();
		
		repo.addProject(project1);
		repo.addProject(project2);
		repo.addProject(project3);
		repo.addProject(project4);
		repo.addProject(project5);
		
		
		
		return repo;
	}
	
	
	public InMemoryUserRepo constructUserRepository()
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
	
	public InMemoryWorkerRepo constructWorkerRepo()
	{
		Consultant cons1 = constructConsultant(1);
		Consultant cons2 = constructConsultant(2);
		Consultant cons3 = constructConsultant(3);
		Consultant cons4 = constructConsultant(4);
		Leader mana5 = constructLeader(5);
		Leader mana6 = constructLeader(6);
		
		InMemoryWorkerRepo repo = new InMemoryWorkerRepo();
		repo.addConsultant(cons1);
		repo.addConsultant(cons2);
		repo.addConsultant(cons3);
		repo.addConsultant(cons4);
		repo.addManager(mana5);
		repo.addManager(mana6);
		
		return repo;
	}
	
	public Consultant constructConsultant(int i)
	{
		return new Consultant("worker" + i, i, 0, i);
	}
	
	public Leader constructLeader(int i)
	{
		return new Leader("worker" + i, i, 0, i, i);
	}
	
	public User constructUser(int i)
	{
		return new User("user" + i, "userPass" + i, "user" + i + "@email.com", "User " + i);
	}
	
	public Project constructProject(int i)
	{
		return new Project(constructLocal(i), i);
	}
	
	public Local constructLocal(int i)
	{
		return new Local(i, i, "local" + i, i);
	}
}
