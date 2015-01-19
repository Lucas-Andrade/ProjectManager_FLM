package app.framesAndPanels.mainFrameActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.framesAndPanels.commandWindowsActionListener.commandWindows.AuthenticationDialog;
import app.framesAndPanels.commandWindowsActionListener.commandWindows.PostProjectFrame;
import app.framesAndPanels.commandWindowsActionListener.commandWindows.PostWorkerFrame;
import app.repositoryHolders.RepositoryHolder;

public class PostWorkerAL extends MainFrameActionListener
{

	public PostWorkerAL(RepositoryHolder repositories,
			Authentication authentication)
	{
		super(repositories, authentication);
	}

	@Override
	void action()
	{
		new PostWorkerFrame().setVisible(true);
	}

}