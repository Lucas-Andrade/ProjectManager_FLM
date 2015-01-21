package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import app.domainCommands.Command;
import app.domainCommands.RemoveProjectToRepo;
import app.windows.PublishToErrorDialog;
import app.windows.PublishToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class RemoveProjectAL {

	private String pidString;
	private JTextField projectId;



	public RemoveProjectAL(JTextField projectId) {
		this.projectId = projectId;
	}

	
	
	public void actionPerformed(ActionEvent e){
		pidString = projectId.getText();
		int resposta = JOptionPane.showConfirmDialog(null, "Do you want to delete this project?","Confirm", JOptionPane.OK_CANCEL_OPTION);
		//se a resposta for sim, encerra a aplicação
		if (resposta == JOptionPane.OK_OPTION){
			Command command = new RemoveProjectToRepo(MainFrame.getRepositories().getProjectsRepo(), pidString);
			new SwingWorkerCommand(command, new PublishToMainFrame(), new PublishToErrorDialog()).execute();
			System.exit(0);
		}	
	}	
	
}
	

