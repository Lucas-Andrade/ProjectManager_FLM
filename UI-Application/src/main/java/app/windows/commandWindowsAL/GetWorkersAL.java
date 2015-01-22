package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import app.domainCommands.Command;
import app.domainCommands.GetProjectWorkersFromRepo;
import app.windows.PublishToErrorDialog;
import app.windows.PublishToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class GetWorkersAL implements ActionListener {

	private JTextField projectField;
	private String workerOpt;
	private String pid;

	public GetWorkersAL(JTextField projectField, String worker) {
		this.projectField = projectField;
		this. workerOpt = worker;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			pid = projectField.getText();
		}catch (NullPointerException | ArrayIndexOutOfBoundsException a){
			new ErrorDialog("Error").setVisible(true);
		}
		
		if(pid.length() == 0){
			new ErrorDialog("At least one field was left blank.").setVisible(true);
		}

		try{
			Command command = new GetProjectWorkersFromRepo(MainFrame.getRepositories().getProjectsRepo(), pid, workerOpt );
			new SwingWorkerCommand(command, new PublishToMainFrame(), new PublishToErrorDialog()).execute();
		
		}catch(IllegalArgumentException iae){
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}

}
