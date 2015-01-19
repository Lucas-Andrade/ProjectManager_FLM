package app.framesAndPanels.mainFrameActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.authentication.Authentication;
import app.framesAndPanels.commandWindowsActionListener.commandWindows.AuthenticationDialog;
import app.repositoryHolders.RepositoryHolder;

/**
 * Class responsible for implementing the {@link ActionListener}s
 * for the {@link MainFrame}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public abstract class MainFrameActionListener implements ActionListener
{

	protected RepositoryHolder repositories;
	protected Authentication authentication;

	public MainFrameActionListener(RepositoryHolder repositories, Authentication authentication)
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