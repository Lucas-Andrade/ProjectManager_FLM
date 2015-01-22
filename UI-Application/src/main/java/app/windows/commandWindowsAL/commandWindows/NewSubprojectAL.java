package app.windows.commandWindowsAL.commandWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import app.domainCommands.AddSubprojectToRepo;
import app.domainCommands.Command;
import app.windows.PublishToErrorDialog;
import app.windows.PublishToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class NewSubprojectAL implements ActionListener {

	private JTextField[] textfields;
	private String pid;
	private String subprojectId;

	public NewSubprojectAL(JTextField[] textFields) {
		this.textfields = textFields;
		
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			try{
				pid = textfields[0].getText();
				subprojectId = textfields[1].getText();
				
			}catch (NullPointerException | ArrayIndexOutOfBoundsException a){
				new ErrorDialog("Error").setVisible(true);
			}
			
			if(pid.length() == 0 || subprojectId.length() == 0 ){
				new ErrorDialog("At least one field was left blank.").setVisible(true);
			}
			
			try{
				Command command = new AddSubprojectToRepo(MainFrame.getRepositories().getProjectsRepo(),pid, subprojectId);
				new SwingWorkerCommand(command, new PublishToMainFrame(), new PublishToErrorDialog()).execute();
			
			}catch(IllegalArgumentException iae){
				new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
			}
		}
		
	}
