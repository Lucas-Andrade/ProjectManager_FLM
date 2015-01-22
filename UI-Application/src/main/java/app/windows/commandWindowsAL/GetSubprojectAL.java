package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import app.domainCommands.Command;
import app.domainCommands.GetSubprojectsFromRepo;
import app.windows.PublishToErrorDialog;
import app.windows.PublishToGetPanel;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class GetSubprojectAL implements ActionListener {

	private JTextField projectId;
	private String pidString;
	



	public GetSubprojectAL(JTextField projectId) {
		this.projectId = projectId; // TODO Auto-generated constructor stub
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			pidString = projectId.getText();
		}catch (NullPointerException | ArrayIndexOutOfBoundsException a){
			new ErrorDialog("Error").setVisible(true);
			return;
		}
		
		if(pidString.length() == 0 ){
			new ErrorDialog("At least one field was left blank.").setVisible(true);
			return;
		}
		
		try{
			Command command = new GetSubprojectsFromRepo(MainFrame.getRepositories().getProjectsRepo(), pidString);
			new SwingWorkerCommand(command, new PublishToGetPanel(), new PublishToErrorDialog()).execute();
		
		}catch(IllegalArgumentException iae){
			
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
		

	}

}
