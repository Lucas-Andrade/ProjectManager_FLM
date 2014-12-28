package utils;

import org.junit.Test;
import utils.Consultant;
import utils.Leader;
import utils.Local;
import utils.Project;
import utils.Team;

public class ProjectTest
{

	@Test
	public void testToString()
	{

		Local local = new Local(1, 1, "local", 10.5);
		Leader leader = new Leader("name", 2.5, 1, 2.1, (long) 2);

		Consultant worker1 = new Consultant("worker1", 1.9, 3, (long) 3);
		Consultant worker2 = new Consultant("worker2", 1.9, 2, (long) 4);

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
		proj4.addWorker(worker2);

		proj1.addProject(proj2);
		proj2.addProject(proj3);
		proj1.addProject(proj4);

//		System.out.println(proj1.toString());
		System.out.println(proj1.getJson());
	}

}
