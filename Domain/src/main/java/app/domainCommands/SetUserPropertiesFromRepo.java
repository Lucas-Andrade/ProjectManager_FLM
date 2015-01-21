package app.domainCommands;

import app.AppElement;
import app.domainCommands.exceptions.IncorrectPasswordException;
import app.domainCommands.exceptions.NoSuchUsernameException;
import app.domainCommands.exceptions.PasswordLengthOutOfBoundsException;
import app.elements.IUser;
import app.repository.UserRepository;

public class SetUserPropertiesFromRepo implements Command{
	
	UserRepository uRepo;
	String username;
	String oldPassword;
	String newPassword;
	
	public SetUserPropertiesFromRepo(UserRepository uRepo, String username, String oldPassword,
			String newPassword){
		
		if (uRepo == null || username == null || oldPassword == null || newPassword == null){
			throw new IllegalArgumentException();
		}
		this.uRepo = uRepo;
		this.username = username;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}
	
	@Override
	public AppElement[] call() throws IncorrectPasswordException, NoSuchUsernameException, 
			PasswordLengthOutOfBoundsException {

		if (! uRepo.isPasswordCorrectForUser(username, oldPassword)){
			throw new IncorrectPasswordException("That password is not correct.");
		}

		IUser user = uRepo.getUserByUsername(username);
		if (user == null){
			throw new NoSuchUsernameException("That username does not exist.");
		}
		if (!user.setNewPassword(newPassword)){
			throw new PasswordLengthOutOfBoundsException("A password must have at least four characters.");
		}
		
		return new AppElement[]{user};
	}

}
