package App.model.Projects;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class ProjectManagerTest {

	private Project project;

	@Before
	public void Arrange() {

		// Arrange
		double costHour = 10;
		double hour = 5;
		double bonus = 50;
		
		Leader manager = new Leader("Filipa", costHour, hour, bonus); // 50
		Leader leader = new Leader("Jose", costHour, hour, bonus); // 100
		Team team = new Team("team", leader);

		Local local = new Local("RT", "Lisboa", 300); // 300
		project = new Project("Randstad", local, manager, team);

		Consultant consultant1 = new Consultant("Gonçalo", 1, 2); // 2

		// Act
		project.addWorker(consultant1);
	}

	@After
	public void ClearUsedNames() {
		NameTester.removeAll();
	}
	
	@Test
	public void gestTotalCost_test() {

		// Assert
		assertTrue(project.getCost() == 502);
	}

	@Test
	public void shouldNotAcepetTheSameConsultant_test() {

		// Assert
		assertFalse(project.addWorker(new Consultant("Gonçalo", 4, 6)));
	}

	@Test
	public void getTotalCost_withNewProject_test() {

		// Arrange
		Local local = new Local("RT", "Porto", 500); // 500
		Leader manager = new Leader("Filipa", 1, 3, 0); // 3
		Leader leader_1 = new Leader("Jose", 10, 10, 100); // 200
		Team team = new Team("team", leader_1);

		Project subproject = new Project("subproject", local, manager, team);

		// Act
		project.addProject(subproject);

		// Assert
		assertTrue(project.getCost() == 1205);
	}

	@Test
	public void shouldNotAcepetSubProjectsWithSameName_test() {

		// Arrange
		Local local = new Local("RT", "Porto", 500);
		Leader manager = new Leader("Filipa", 1, 3, 0);
		Leader leader = new Leader("Jose", 10, 10, 100);
		Team team = new Team("team", leader);
		// Subproject_0
		Project subproject = new Project("subproject", local, manager, team);

		Local local1 = new Local("TR", "Lisboa", 900);
		Leader manager1 = new Leader("Filipa_1", 2, 3, 0);
		Leader leader1 = new Leader("Jose_1", 100, 100, 50);
		Team team1 = new Team("team_1", leader1);
		// Subproject_1
		Project subproject1 = new Project("subproject", local1, manager1, team1);

		// Act
		project.addProject(subproject);

		// Assert
		assertFalse(project.addProject(subproject1));
	}

	@Test
	public void findConsultantByName_test() {

		// Arrange
		Consultant consultant1 = new Consultant("Paulo", 20, 10);

		// Act
		project.addWorker(consultant1);

		// Assert
		assertTrue(project.getWorkerByName(consultant1.getName()).getName().equals("Paulo"));
	}

	@Test
	public void findSubProjectByName_test() {

		// Arrange
		Leader leader_1 = new Leader("Luis", 10, 8, 200);
		Team team1 = new Team("GRUPO2", leader_1);

		Leader manager1 = new Leader("Lara", 10, 8, 0);
		Local local1 = new Local("Saldanha", "Lisboa", 20);

		Project subProj = new Project("RT_Challenge", local1, manager1, team1);
		Project subProj_1 = new Project("RT_Challenge1", local1, manager1, team1);
		Project subProj_2 = new Project("RT_Challenge2", local1, manager1, team1);

		// Act
		project.addProject(subProj);
		project.addProject(subProj_1);
		subProj_1.addProject(subProj_2);

		// Assert
		assertEquals(project.getSubProjectByName(subProj_2.getName()), subProj_2);
	}

	@Test
	public void shouldRemoveASubProject_test() {

		// Arrange
		Local local = new Local("RT", "Porto", 500);
		Leader manager = new Leader("Filipa", 1, 3, 0);
		Leader leader = new Leader("Jose", 10, 10, 100);
		Team team = new Team("team", leader);
		// Subproject
		Project subproject = new Project("subproject", local, manager, team);

		// Act
		project.addProject(subproject);

		// Assert
		assertNotNull(project.getSubProjectByName("subproject"));
		assertTrue(project.removeProject("subproject"));
		assertNull(project.getSubProjectByName("subproject"));
	}

	@Test
	public void shouldNotAcepetConsultantsWithSameName_test() {

		Consultant c1 = new Consultant("Filipa", 10, 5);
		Consultant c2 = new Consultant("Filipa", 5, 10);

		// Act
		project.addWorker(c1);

		// Assert
		assertFalse(project.addWorker(c2));
	}

	@Test
	public void shouldRemoveAConsultants_test() {

		Consultant g = new Consultant("Gonçalo", 10, 10);

		// Act
		project.addWorker(g);

		// Assert
		assertNotNull(project.getWorkerByName("Gonçalo"));
		assertTrue(project.removeAWorker("Gonçalo"));
		assertNull(project.getWorkerByName("Gonçalo"));
	}

	@Test
	public void shoulSortByNameSubProjects_test() {

		// Arrange
		Local local = new Local("RT", "Porto", 500);
		Leader manager = new Leader("Filipa", 1, 3, 0);
		Leader leader = new Leader("Jose", 10, 10, 100);
		Team team = new Team("team", leader);
		// Subproject_0
		Project subproject = new Project("Randstad", local, manager, team);

		// Subproject_1
		Project subproject1 = new Project("Challenge", local, manager, team);

		// Subproject_2
		Project subproject2 = new Project("projectManager", local, manager, team);

		// Act
		project.addProject(subproject);
		project.addProject(subproject1);
		project.addProject(subproject2);
		Iterator<Project> projectsItr = project.getContainerProject().iterator();

		// Assert
		assertEquals(projectsItr.next().getName(), subproject1.getName());
		assertEquals(projectsItr.next().getName(), subproject2.getName());
		assertEquals(projectsItr.next().getName(), subproject.getName());
	}

	@Test
	public void shoulSortByConsultsCostPerHourInATeam_test() {

		// Arrange
		Consultant consultant_2 = new Consultant("Estiveira", 6, 10);
		Consultant consultant_3 = new Consultant("Daniel", 2, 10);
		Consultant consultant_4 = new Consultant("Pedro", 4, 10);
		Consultant consultant_5 = new Consultant("asg", 13, 10);

		// Act
		project.addWorker(consultant_2);
		project.addWorker(consultant_3);
		project.addWorker(consultant_4);
		project.addWorker(consultant_5);
		Iterator<AWorker> teamItr = project.getTeam().iterator();

		// Assert
		assertEquals(teamItr.next().getName(), "Gonçalo");
		assertEquals(teamItr.next().getName(), "Daniel");
		assertEquals(teamItr.next().getName(), "Pedro");
		assertEquals(teamItr.next().getName(), "Estiveira");
		assertEquals(teamItr.next().getName(), "Jose");
		assertEquals(teamItr.next().getName(), "asg");

	}
}
