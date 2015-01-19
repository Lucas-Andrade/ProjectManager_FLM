package app.framesAndPanels.mainFrameActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.authentication.Authentication;
import app.framesAndPanels.commandWindowsActionListener.commandWindows.AuthenticationDialog;
import app.repositoryHolders.RepositoryHolder;

public abstract class MainFrameActionListner implements ActionListener
{

	protected RepositoryHolder repositories;
	protected Authentication authentication;

	public MainFrameActionListner(RepositoryHolder repositories, Authentication authentication)
	{
		this.repositories = repositories;
		this.authentication = authentication;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(authentication.isAuthenticated()){
			this.action();
		} else {
			new AuthenticationDialog(authentication, repositories).setVisible(true);
		}
	}
	
	abstract void action();

}