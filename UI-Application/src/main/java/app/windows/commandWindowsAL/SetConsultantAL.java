package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import app.domainCommands.Command;
import app.domainCommands.SetConsultantPropertiesFromRepo;
import app.windows.PublishToErrorDialog;
import app.windows.PublishToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class SetConsultantAL implements ActionListener {

	private String consultantId;
	private String consultantName;
	private String priceHour;
	private JTextField[] textFields;

	public SetConsultantAL(JTextField[] textFields) {
		this.textFields = textFields;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			consultantId = textFields[0].getText();
			consultantName = textFields[1].getText();
			priceHour = textFields[2].getText();
			
		
		}catch (NullPointerException | ArrayIndexOutOfBoundsException a){
			new ErrorDialog("Error").setVisible(true);
			return;
		}
		
		if(consultantId.length() == 0 ){
			new ErrorDialog("The Consultant ID field was left blank.").setVisible(true);
			return;
		}else if(consultantName.length() == 0 && priceHour.length() == 0){
			new ErrorDialog("The Consultant ID field was left blank.").setVisible(true);
			return;
		}
		
		try{
			Command command = new SetConsultantPropertiesFromRepo(MainFrame.getRepositories().getWorkersRepo(), consultantId, consultantName, priceHour);
			new SwingWorkerCommand(command, new PublishToMainFrame(), new PublishToErrorDialog()).execute();
		}catch(IllegalArgumentException iae){
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}
	
}
