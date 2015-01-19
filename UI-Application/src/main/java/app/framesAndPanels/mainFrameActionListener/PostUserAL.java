package app.framesAndPanels.mainFrameActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.framesAndPanels.commandWindowsActionListener.commandWindows.AuthenticationDialog;
import app.framesAndPanels.commandWindowsActionListener.commandWindows.PostProjectFrame;
import app.framesAndPanels.commandWindowsActionListener.commandWindows.PostUserFrame;
import app.repositoryHolders.RepositoryHolder;

public class PostUserAL extends MainFrameActionListener
{

	public PostUserAL(RepositoryHolder repositories,
			Authentication authentication)
	{
		super(repositories, authentication);
	}

	@Override
	void action()
	{
		new PostUserFrame().setVisible(true);
	}

}