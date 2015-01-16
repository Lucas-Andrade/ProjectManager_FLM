package app.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import commands.GetSubproject;
import utils.Local;
import utils.Project;
import app.AppElement;
import app.authentication.Authentication;
import app.elements.Message;
import app.framesAndPanels.DeleteProjectFrame;
import app.framesAndPanels.GetSubprojectsFrame;
import app.framesAndPanels.PostProjectFrame;
import app.repositoryHolders.RepositoryHolder;
import app.result.AppSwingWorker;
import app.result.GetSubprojectsResult;
import app.result.PostProjectResult;

public class GetSubprojects extends BaseCommand{

	public GetSubprojects(JSplitPane pane, RepositoryHolder repositories, Authentication authentication) {
		super(pane, repositories, authentication);
	}

	@Override
	public void execute() {
		new GetSubprojectsFrame(new GetSubprojectsResult(pane, repositories)).setVisible(true);
	}

}