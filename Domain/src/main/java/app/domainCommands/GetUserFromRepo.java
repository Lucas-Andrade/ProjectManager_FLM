package app.domainCommands;

import app.AppElement;
import app.domainCommands.exceptions.NoSuchUsernameException;
import app.repository.UserRepository;

public class GetUserFromRepo implements Command{

	UserRepository uRepo;
	String username;
	
	public GetUserFromRepo(UserRepository uRepo, String username){
		if (uRepo == null || username == null){
			throw new IllegalArgumentException();
		}
		this.uRepo = uRepo;
		this.username = username;
	}
	
	@Override
	public AppElement[] call() throws NoSuchUsernameException {
		
		AppElement[] user = new AppElement[]{uRepo.getUserByUsername(username)};
		if(user[0] == null){
			throw new NoSuchUsernameException();
		}
		return user;
	}

}
