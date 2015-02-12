package app;

import utils.Consultant;
import utils.Leader;
import utils.Local;
import utils.Project;
import app.elements.User;
import app.elements.mutable.ConsultantCreationDescriptor;
import app.elements.mutable.LeaderCreationDescriptor;
import app.elements.mutable.ProjectCreationDescriptor;
import app.elements.mutable.UserCreationDescriptor;
import app.repository.InMemoryProjectRepo;
import app.repository.InMemoryUserRepo;
import app.repository.InMemoryWorkerRepo;

/**
 * This class has several methods that return generic {@code InMemoryProjectRepo},
 * {@code InMemoryUserRepo}, {@code InMemoryWorkerRepo}, {@code Project}, {@code User},
 * {@code Consultant}, {@code Leader}, and {@code Local} objects. These objects are 
 * constructed within the methods, and values are added automatically to their fields.
 * 
 * This class is very important for the tests in this package, which use these methods
 * to construct objects to perform tests on. Naturally, the tests are dependent on
 * the values given to the fields of the objects constructed here. Therefore it is 
 * recommended not to perform any alterations in this code.
 * 
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 08/12/2014
 */
public class RepositoryConstructor {

	/**
	 * Constructs and returns a {@code InMemoryProjectRepo} with 5 projects in 
	 * it. Their {@code PID}s are {@code long} numbers ranging from 1 to 5. 
	 * The {@code Team} of the all the projects is empty and there is no 
	 * assigned {@code Leader}. The properties of the local are based
	 * on the value of the {@code PID}: the cost, longitude and latitude are the value 
	 * of the {@code PID}; and the name is "local" + the value of the {@code PID}.
	 * {@code Project} with {@code PID} = 3 has the {@code Project} with {@code PID} = 4
	 * as it's subproject. 
	 * 
	 * @return A {@code InMemoryProjectRepo} with 5 projects in it.
	 */
	public static InMemoryProjectRepo constructProjectRepo(){
		InMemoryProjectRepo repo = new InMemoryProjectRepo();
		repo.removeAll();

		for(int i = 1; i <= 5; i++){
			Project project = constructProject(i);
			repo.addProject(new ProjectCreationDescriptor(project.getLocal().getLatitude(), project.getLocal().getLongitude(), project.getLocal().getName(), project.getLocal().getCost()));
		}
		repo.getProjectById(3).addProject(repo.getProjectById(4));
		
		return repo;
	}
	
	/**
	 * Constructs and returns a {@code InMemoryUserRepo} with five {@code User}s in 
	 * it (and the {@code ImmutableAdmin}). The {@code User}s were constructed 
	 * using the method {@code constructUser(int i)} where {@code i} was varied 
	 * from 1 to 5. For more details on the properties of these {@code User}s 
	 * see app.RepositoryConstructor.constructUser
	 * @see AppMainFrame.RepositoryConstructor.constructUser
	 * 
	 * @return A {@code InMemoryUserRepo} with five {@code User}s.
	 */
	public static InMemoryUserRepo constructUserRepo(){
		InMemoryUserRepo repo = new InMemoryUserRepo();
		repo.removeAll();
		
		for(int i = 1; i <= 5; i++){
			User user = constructUser(i);
			repo.addUser(new UserCreationDescriptor(user.getLoginName(), user.getLoginPassword(), user.getEmail()));
		}
		
		return repo;
	}
	
	/**
	 * Constructs and returns a {@code InMemoryWorkerRepo} with four 
	 * {@code Consultant}s and two {@code Leader}s. 
	 * 
	 * The {@code Consultant}s were constructed using the method
	 * {@code constructConsultant(long i)} where {@code i} was the value of the 
	 * {@code CID} of the {@code Consultants} (from 1 to 4). For
	 * more details on the properties of these {@code Consultants}s see
	 * app.RepositoryConstructor.constructConsultant.
	 * The {@code Leader}s were constructed using the method
	 * {@code constructLeader(long i)} where {@code i} was the value of the 
	 * {@code CID} of the {@code Consultants} (from 5 to 6). For
	 * more details on the properties of these {@code Leader}s see
	 * app.RepositoryConstructor.constructConsultant
	 * 
	 * @see AppMainFrame.RepositoryConstructor.constructLeader
	 * @see AppMainFrame.RepositoryConstructor.constructConsultant
	 * 
	 * @return A {@code InMemoryWorkerRepo} with a total of six {@code AWorker}s
	 */
	public static InMemoryWorkerRepo constructWorkerRepo(){
		InMemoryWorkerRepo repo = new InMemoryWorkerRepo();
		repo.removeAll();
		
		for(int i = 1; i <= 4; i++){ //the value of i represents the value of the CID being used
			Consultant cons = constructConsultant(i);
			repo.addConsultant(new ConsultantCreationDescriptor(cons.getName(), cons.getCostPerHour(), cons.getWorkerHours()));
		}
		for(int i = 5; i <= 6; i++){ //the value of i represents the value of the CID being used
			Leader mana = constructLeader(i);
			repo.addManager(new LeaderCreationDescriptor(mana.getName(), mana.getCostPerHour(), mana.getWorkerHours(), mana.getBonus()));
		}
		
		return repo;
	}
	
	/**
	 * Constructs and returns a {@code Consultant} with {@code CID} {@code i}.
	 * The name of the {@code Consultant} is "worker" + the value of the {@code CID}.
	 * The {@code Consultant}'s {@code CostPerHour} is the value of the {@code CID}, and 
	 * the number of {@code hoursWorked} is zero.
	 * 
	 * @param i - the {@code CID} of the {@code Consultant}
	 * @return A {@code Consultant} with {@code CID} {@code i}
	 */
	public static Consultant constructConsultant(long i){
		return new Consultant("worker" + i, i, 0, i);
	}
	
	/**
	 * Constructs and returns a {@code Leader} with {@code CID} {@code i}.
	 * The name of the {@code Leader} is "worker" + the value of the {@code CID}.
	 * The {@code Leader}'s {@code CostPerHour} and {@code bonus} is the value of 
	 * the {@code CID}, and  the number of {@code hoursWorked} is zero.
	 * 
	 * @param i - the {@code CID} of the {@code Leader}
	 * @return A {@code Leader} with {@code CID} i
	 */
	public static Leader constructLeader(long i){
		return new Leader("worker" + i, i, 0, i, i);
	}
	
	/**
	 * Constructs and returns a {@code User}.
	 * The {@code username} of the {@code User} is "user" + the value of the parameter {@code i}.
	 * The {@code password} of the {@code User} is "userPass" + the value of the parameter {@code i}.
	 * The {@code email} of the {@code User} is "userPass" + the value of the parameter {@code i}
	 * + "@email.com".
	 * The {@code fullName} of the {@code User} is "User " + the value of the parameter {@code i}.
	 * 
	 * @param i
	 * 
	 * @return A {@code User}
	 */
	public static User constructUser(int i){
		return new User("user" + i, "userPass" + i, "user" + i + "@email.com");
	}
	
	/**
	 * Constructs and returns a {@code Project} with {@code PID} {@code i}.
	 * The {@code Local} is constructed using the method {@code constructLocal}
	 * and passing {@code i} to it as parameter. For more details on the properties 
	 * of the {@code Local} see app.RepositoryConstructor.constructLocal
	 * @see AppMainFrame.RepositoryConstructor.constructLocal
	 * 
	 * @param i
	 * @return A {@code Project} with {@code PID} i.
	 */
	public static Project constructProject(long i){
		return new Project(constructLocal((int)i), i);
	}
	
	/**
	 * Constructs and returns a {@code Local}.
	 * The {@code cost}, {@code latitude} and {@code longitude} of the {@code Local}
	 * have the value {@code i} passed as parameter. The name of the local is
	 * "local" + {@code i}.
	 * 
	 * @param i
	 * @return A {@code Local}.
	 */
	public static Local constructLocal(int i){
		return new Local(i, i, "local" + i, i);
	}
}
