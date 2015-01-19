package app.commands;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.framesAndPanels.commandWindowsActionListener.commandWindows.GetWorkersInProjectFrame;
import app.repositoryHolders.RepositoryHolder;
import app.result.GetWorkersInProjectResult;

public class GetWorkersInProject extends BaseCommand
{

	public GetWorkersInProject(JSplitPane pane, RepositoryHolder repositories,
			Authentication authentication)
	{
		super(pane, repositories, authentication);
	}

	@Override
	public void execute()
	{
		new GetWorkersInProjectFrame(new GetWorkersInProjectResult(pane, repositories)).setVisible(true);
	}

}