package app.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import commands.GetProjectWorkers;
import commands.GetSubproject;
import utils.AWorker;
import utils.Consultant;
import utils.Leader;
import utils.Local;
import utils.Project;
import utils.Team;
import app.AppElement;
import app.authentication.Authentication;
import app.elements.Message;
import app.framesAndPanels.DeleteProjectFrame;
import app.framesAndPanels.GetSubprojectsFrame;
import app.framesAndPanels.GetWorkersInProjectFrame;
import app.framesAndPanels.PostProjectFrame;
import app.repositoryHolders.RepositoryHolder;
import app.result.GetWorkersInProjectResult;
import app.result.PostProjectResult;

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