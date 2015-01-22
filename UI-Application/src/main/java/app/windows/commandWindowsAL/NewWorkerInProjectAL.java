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
import app.windows.mainFrameAL.mainFrame.WorkerID;

public class NewWorkerInProjectAL implements ActionListener {

	private JTextField textFields;
	private WorkerID workerID;
	private String worker;
	private String pid;
	private String cid;

	public NewWorkerInProjectAL(JTextField textFields, WorkerID workerId) {
		this.textFields = textFields;
		this.workerID = workerId;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			pid = textFields.getText();
			cid = workerID.getIDField().getText();
			worker = workerID.getSelected();
		}catch (NullPointerException | ArrayIndexOutOfBoundsException a){
			new ErrorDialog("Error").setVisible(true);
			return;
		}
		
		if(pid.length() == 0 || cid.length() == 0 || worker.length() == 0 ){
			new ErrorDialog("Required parameter not present.").setVisible(true);
			return;
		}
		try{
			Command command = new AddWorkerToProjectInRepo(MainFrame.getRepositories().getProjectsRepo(),MainFrame.getRepositories().getWorkersRepo(), pid, cid, worker);
			new SwingWorkerCommand(command, new PublishToMainFrame(), new PublishToErrorDialog()).execute();
		
		} catch(IllegalArgumentException iae){
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}

}
