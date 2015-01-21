package app.domainCommands;

import app.AppElement;
import app.repository.UserRepository;

public class GetAllUsersFromRepo implements Command{
	
	UserRepository uRepo;
	
	public GetAllUsersFromRepo(UserRepository uRepo){
		if (uRepo == null){
			throw new IllegalArgumentException();
		}
		this.uRepo = uRepo;
	}
	
	@Override
	public AppElement[] call() {
		return uRepo.getAll();
	}

}
