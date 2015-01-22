package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import app.domainCommands.AddConsultantToRepo;
import app.domainCommands.Command;
import app.windows.PublishTeamToMainFrame;
import app.windows.PublishToErrorDialog;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class NewConsultantAL implements ActionListener{
	
	private JTextField[] textfields;
	private String name;
	private String priceHour;
	private String bonus;

	public NewConsultantAL (JTextField[] textFields){
	this.textfields = textFields;
	
	}
	
	public void actionPerformed(ActionEvent e) {

		try{
			name = textfields[0].getText();
			priceHour = textfields[1].getText();
			bonus = textfields[2].getText();
			
		}catch (NullPointerException | ArrayIndexOutOfBoundsException a){
			new ErrorDialog("Error").setVisible(true);
			return;
		}
		
		if(name.length() == 0 || priceHour.length() == 0){
			new ErrorDialog("At least one required field was left blank.").setVisible(true);
			return;
		}
		
		try{
			Command command = new AddConsultantToRepo(MainFrame.getRepositories().getWorkersRepo(), name, priceHour, bonus);
			new SwingWorkerCommand(command, new PublishTeamToMainFrame(), new PublishToErrorDialog()).execute();
		
		}catch(IllegalArgumentException iae){
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}
}

