package resultsAndOutputMethods;

import static org.junit.Assert.*;

import org.junit.Test;

import utils.Consultant;
import utils.Leader;
import utils.Local;
import utils.Project;
import utils.Team;
import app.RepositoryConstructor;
import app.elements.User;
import app.repository.InMemoryProjectRepo;
import app.resultsAndOutputMethods.outputFormat.ToTextHtml;

public class ToHtmlTest {

	@Test
	public void visualTest()
	{

		Local local = new Local(1, 1, "local", 10.5);
		Leader leader = new Leader("name", 2.5, 1, 2.1, (long) 2);

		Consultant worker1 = new Consultant("worker1", 1.9, 3, (long) 3);
		Consultant worker2 = new Consultant("worker2", 1.9, 2, (long) 4);
		Consultant worker3 = new Consultant("worker3", 1.9, 2, (long) 333);

		Project proj1 = new Project("Proj1", local, leader, new Team(),
				(long) 11);
		Project proj2 = new Project("Proj2", local, leader, new Team(),
				(long) 22);
		Project proj3 = new Project("Proj3", local, leader, new Team(),
				(long) 33);
		Project proj4 = new Project("Proj4", local, leader, new Team(),
				(long) 44);

		proj1.addWorker(worker1);
		proj1.addWorker(worker2);
		proj2.addWorker(worker1);
		proj3.addWorker(worker1);
		proj3.addWorker(worker2);
		proj3.addWorker(worker3);
		proj4.addWorker(worker2);

		proj1.addProject(proj2);
		proj2.addProject(proj3);
		proj1.addProject(proj4);
		
		InMemoryProjectRepo repo = new InMemoryProjectRepo();
		repo.addProject(proj1);
		repo.addProject(proj2);
		repo.addProject(proj3);
		repo.addProject(proj4);
		
		System.out.println(new ToTextHtml().parse(proj1.getJson()));
		System.out.println("\n\n");
		System.out.println(new ToTextHtml().parse(new User("username", "password", "email", "fullname").getJson()));
		System.out.println("\n\n");
		System.out.println(new ToTextHtml().parse(worker1.getJson()));
		System.out.println("\n\n");
		System.out.println(new ToTextHtml().parse(repo.getJson()));
		System.out.println("\n\n");
		System.out.println(new ToTextHtml().parse(new RepositoryConstructor().constructWorkerRepo().getJson()));
		System.out.println("\n\n");
		System.out.println(new ToTextHtml().parse(new RepositoryConstructor().constructUserRepo().getJson()));
	}

}
