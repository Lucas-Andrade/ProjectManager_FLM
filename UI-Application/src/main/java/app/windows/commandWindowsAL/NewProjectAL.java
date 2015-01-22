package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import app.domainCommands.AddProjectToRepo;
import app.domainCommands.Command;
import app.windows.PublishToErrorDialog;
import app.windows.PublishToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class NewProjectAL implements ActionListener {

	private JTextField[] textfields;
	private String name;
	private String price;
	private String latitude;
	private String longitude;

	public NewProjectAL(JTextField[] textFields2){
		this.textfields = textFields2;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		try{
			name = textfields[0].getText();
			price = textfields[1].getText();
			latitude = textfields[3].getText();
			longitude = textfields[2].getText();
		}catch (NullPointerException | ArrayIndexOutOfBoundsException a){
			new ErrorDialog("Error").setVisible(true);
		}
		
		if(name.length() == 0 || price.length() == 0 || latitude.length() == 0 || longitude.length() == 0){
			new ErrorDialog("At least one field was left blank.").setVisible(true);
		}
		
		try{
			Command command = new AddProjectToRepo(MainFrame.getRepositories().getProjectsRepo(), latitude, longitude, name, price);
			new SwingWorkerCommand(command, new PublishToMainFrame(), new PublishToErrorDialog()).execute();
		}catch(NumberFormatException nfe){
			new ErrorDialog("Numbers were not introduced in one of the following fields: Price, Longitude of Latitude.").setVisible(true);
		}
		catch(IllegalArgumentException iae){
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}
	
}
