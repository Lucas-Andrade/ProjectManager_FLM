package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import app.domainCommands.Command;
import app.domainCommands.RemoveProjectToRepo;
import app.windows.PublishToErrorDialog;
import app.windows.PublishToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class RemoveProjectAL {

	private String pidString;
	private JTextField projectId;



	public RemoveProjectAL(JTextField projectId) {
		this.projectId = projectId;
	}

	
	
	public void actionPerformed(ActionEvent e){
		try{
			pidString = projectId.getText();
		
		}catch (NullPointerException | ArrayIndexOutOfBoundsException a){
			new ErrorDialog("Error").setVisible(true);
		}
		
		if(pidString.length() == 0 ){
			new ErrorDialog("Required parameter not present.").setVisible(true);
		}
		
		int resposta = JOptionPane.showConfirmDialog(null, "Do you want to delete this project?","Confirm", JOptionPane.OK_CANCEL_OPTION);
		try{
			if (resposta == JOptionPane.OK_OPTION){
				Command command = new RemoveProjectToRepo(MainFrame.getRepositories().getProjectsRepo(), pidString);
				new SwingWorkerCommand(command, new PublishToMainFrame(), new PublishToErrorDialog()).execute();
			}
		}catch(IllegalArgumentException iae){
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}	
	
}
	

