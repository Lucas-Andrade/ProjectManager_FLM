package app.commands;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.repositoryHolders.RepositoryHolder;
import app.result.PatchConsultantResult;
import app.windows.commandWindowsAL.commandWindows.PatchConsultantFrame;

public class PatchConsultant extends BaseCommand{

	public PatchConsultant(JSplitPane pane, RepositoryHolder repositories, Authentication authentication) {
		super(pane, repositories, authentication);
	}
	
	@Override
	public void execute() {
		new PatchConsultantFrame(new PatchConsultantResult(pane, repositories)).setVisible(true);
	}

}