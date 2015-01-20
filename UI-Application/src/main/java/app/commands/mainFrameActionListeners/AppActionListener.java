package app.commands.mainFrameActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.authentication.Authentication;
import app.domainCommands.Command;
import app.repositoryHolders.RepositoryHolder;
import app.windowsAndActionListeners.AuthenticationDialog;

public abstract class AppActionListener implements ActionListener{
	
	Command command;
	Authentication authentication;
	RepositoryHolder repositories;
	
	public AppActionListener(Authentication authentication, RepositoryHolder repositories, Command command){
		
		this.command = command;
		this.repositories = repositories;
		this.authentication = authentication;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(authentication.isAuthenticated()){
			command.execute();
		} else {
			new AuthenticationDialog(authentication, repositories).setVisible(true);
		}
	}
}
