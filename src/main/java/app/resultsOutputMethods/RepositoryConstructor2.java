package app.resultsOutputMethods;

import utils.Consultant;
import utils.Leader;
import utils.Local;
import utils.Project;
import app.elements.User;
import app.repository.InMemoryProjectRepo;
import app.repository.InMemoryUserRepo;
import app.repository.InMemoryWorkerRepo;

/**
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class RepositoryConstructor2 {

	public InMemoryProjectRepo constructProjectRepository()
	{
		InMemoryProjectRepo repo = new InMemoryProjectRepo();
		
		Project project1 = constructProject((int) repo.getNextPID());
		repo.addProject(project1);
		Project project2 = constructProject((int) repo.getNextPID());
		repo.addProject(project2);
		Project project3 = constructProject((int) repo.getNextPID());
		repo.addProject(project3);
		Project project4 = constructProject((int) repo.getNextPID());
		repo.addProject(project4);
		
		project3.addProject(project4);
		
		Project project5 = constructProject((int) repo.getNextPID());
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
		repo.removeAll();
		repo.addUser(user1);
		repo.addUser(user2);
		repo.addUser(user3);
		repo.addUser(user4);
		
		return repo;
	}
	
	public InMemoryWorkerRepo constructWorkerRepo()
	{
		InMemoryWorkerRepo repo = new InMemoryWorkerRepo();
		
		Consultant cons1 = constructConsultant((int)repo.nextCID());
		repo.addConsultant(cons1);
		Consultant cons2 = constructConsultant((int)repo.nextCID());
		repo.addConsultant(cons2);
		Consultant cons3 = constructConsultant((int)repo.nextCID());
		repo.addConsultant(cons3);
		Consultant cons4 = constructConsultant((int)repo.nextCID());
		repo.addConsultant(cons4);
		Leader mana5 = constructLeader((int)repo.nextCID());
		repo.addManager(mana5);
		Leader mana6 = constructLeader((int)repo.nextCID());
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