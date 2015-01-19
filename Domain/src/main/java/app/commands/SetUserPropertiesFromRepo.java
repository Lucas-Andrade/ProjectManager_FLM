package app.commands;

import app.AppElement;
import app.commands.exceptions.IncorrectPasswordException;
import app.commands.exceptions.NoSuchUsernameException;
import app.commands.exceptions.PasswordLengthOutOfBoundsException;
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
			throw new IncorrectPasswordException();
		}

		IUser user = uRepo.getUserByUsername(username);
		if (user == null){
			throw new NoSuchUsernameException();
		}
		if (!user.setNewPassword(newPassword)){
			throw new PasswordLengthOutOfBoundsException();
		}
		
		return new AppElement[]{user};
	}

}
