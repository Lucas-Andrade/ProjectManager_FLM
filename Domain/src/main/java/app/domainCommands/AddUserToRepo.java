package app.domainCommands;

import app.AppElement;
import app.domainCommands.exceptions.IllegalEmailException;
import app.domainCommands.exceptions.PasswordLengthOutOfBoundsException;
import app.domainCommands.exceptions.RepeatedUsernameException;
import app.elements.IUser;
import app.elements.User;
import app.repository.UserRepository;

public class AddUserToRepo implements Command{

	UserRepository uRepo;
	String username;
	String password; 
	String email;
	String fullName;
	
	public AddUserToRepo(UserRepository uRepo, String username, String password, 
			String email, String fullName){
		if (uRepo == null || username == null || password == null || email == null){
			throw new IllegalArgumentException();
		}
		this.uRepo = uRepo;
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
		
		setEmptyStringsToNull();
	}
	
	@Override
	public AppElement[] call() throws PasswordLengthOutOfBoundsException, 
			IllegalEmailException, RepeatedUsernameException {
		
		if(password.length() < User.MIN_CHAR_IN_PASS){
			throw new PasswordLengthOutOfBoundsException("A password must have at least four characters.");
		}
		validEmail();
		
		IUser user;
		if(fullName == null){
			user = new User(username, password, email);
		} else {
			user = new User(username, password, email, fullName);
		}
		
		if (! uRepo.addUser(user)){
			throw new RepeatedUsernameException("That username is already being used.");
		}
		
		return new AppElement[]{user};
	}

	
	/**
	 * Validates the Email. If the email is not valid throws an IllegalEmailException
	 * @throws IllegalEmailException  
	 * 
	 */
	private void validEmail() throws IllegalEmailException{
		if (!email.contains("@") || email.substring(email.indexOf("@") + 1, email.length()).contains("@")
				|| email.lastIndexOf(".") < email.lastIndexOf("@")){
			throw new IllegalEmailException("That email is not valid.");
		}
	}

	private void setEmptyStringsToNull() {
		if (fullName != null && "".equals(fullName)) {
			fullName = null;
		}
	}
}
