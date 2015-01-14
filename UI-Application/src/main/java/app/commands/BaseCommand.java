package app.commands;

import java.awt.event.ActionEvent;

import javax.swing.JSplitPane;

import app.repositoryHolders.RepositoryHolder;

public abstract class BaseCommand implements Command{
	
	protected JSplitPane pane;
	protected RepositoryHolder repositories;
	protected Authentication authentication;
	
	public BaseCommand(JSplitPane pane, RepositoryHolder repositories, Authentication authentication) {
		this.pane = pane;
		this.repositories = repositories;
		this.authentication = authentication;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(authentication.isAuthenticated()){
			this.execute();
		} else {
			authentication.authenticate();
		}
	}
}
