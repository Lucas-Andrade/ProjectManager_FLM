package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import app.domainCommands.Command;
import app.domainCommands.GetSubprojectsFromRepo;
import app.windows.PublishToErrorDialog;
import app.windows.PublishToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;
import app.windows.mainFrameAL.mainFrame.ProjectID;

public class GetSubprojectAL implements ActionListener {

	private ProjectID projectId;
	private String pidString;
	



	public GetSubprojectAL(ProjectID projectId) {
		this.projectId = projectId; // TODO Auto-generated constructor stub
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		pidString = projectId.getSelectedItem();
		
		
		try{
			Command command = new GetSubprojectsFromRepo(MainFrame.getRepositories().getProjectsRepo(), pidString);
			new SwingWorkerCommand(command, new PublishToMainFrame(), new PublishToErrorDialog()).execute();
			JOptionPane.showInternalConfirmDialog(null, "Success");
		}catch(NumberFormatException nfe){
			new ErrorDialog("Numbers were not introduced in one of the following fields: Price, Longitude of Latitude.").setVisible(true);
		}
		catch(IllegalArgumentException iae){
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
		// TODO Auto-generated method stub

	}

}
