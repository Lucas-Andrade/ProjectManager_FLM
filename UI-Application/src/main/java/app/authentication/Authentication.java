package app.authentication;

import app.elements.IUser;

public class Authentication implements IAuthentication {
	
	private static IUser authenticatedUser;
	private static boolean isAuthenticated;
	
	public Authentication(){
		isAuthenticated = false;
	}
	
	@Override
	public boolean isAuthenticated(){
		return isAuthenticated;
	}
	
	@Override
	public void authenticate(){
		isAuthenticated = true;
	}
	
	public IUser getAuthenticatedUser(){
		return authenticatedUser;
	}
	
	public void setAuthenticatedUser(IUser user) {
		authenticatedUser = user;
		authenticate();
	}
}
