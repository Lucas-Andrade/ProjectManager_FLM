package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import app.domainCommands.AddWorkerToProjectInRepo;
import app.domainCommands.Command;
import app.windows.PublishToErrorDialog;
import app.windows.PublishToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class NewWorkerInProjectAL implements ActionListener {

	private JTextField[] textFields;
	private String worker;
	private String pid;
	private String cid;

	public NewWorkerInProjectAL(JTextField[] textFields, String worker) {
		this.textFields = textFields;
		this.worker = worker;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			pid = textFields[0].getText();
			cid = textFields[1].getText();
			
		}catch (NullPointerException | ArrayIndexOutOfBoundsException a){
			new ErrorDialog("Error").setVisible(true);
		}
		
		if(pid.length() == 0 || cid.length() == 0 || worker.length() == 0 ){
			new ErrorDialog("Required parameter not present.").setVisible(true);
		}
		try{
			Command command = new AddWorkerToProjectInRepo(MainFrame.getRepositories().getProjectsRepo(),MainFrame.getRepositories().getWorkersRepo(), pid, cid, worker);
			new SwingWorkerCommand(command, new PublishToMainFrame(), new PublishToErrorDialog());
		
		}catch(IllegalArgumentException iae){
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}

}
