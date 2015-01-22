package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.JTextField;

import app.domainCommands.Command;
import app.domainCommands.GetProjectWorkersFromRepo;
import app.windows.PublishTeamToGetPanel;
import app.windows.PublishToErrorDialog;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class GetWorkersAL implements ActionListener {

	private JTextField projectField;
	private String workerOpt;
	private String pid;
	private JRadioButton button;

	public GetWorkersAL(JTextField projectField, JRadioButton button) {
		this.projectField = projectField;
		this.button = button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			pid = projectField.getText();
			workerOpt = button.isSelected() ? "consultant" : "manager";
		}catch (NullPointerException | ArrayIndexOutOfBoundsException a){
			new ErrorDialog("Error").setVisible(true);
			return;
		}

		try{
			Command command = new GetProjectWorkersFromRepo(MainFrame.getRepositories().getProjectsRepo(), pid, workerOpt );
			new SwingWorkerCommand(command, new PublishTeamToGetPanel(), new PublishToErrorDialog()).execute();
		
		}catch(IllegalArgumentException iae){
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}

}
