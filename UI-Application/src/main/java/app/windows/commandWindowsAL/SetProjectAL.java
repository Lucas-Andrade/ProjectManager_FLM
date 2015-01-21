package app.windows.commandWindowsAL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import app.domainCommands.Command;
import app.domainCommands.SetProjectPropertiesFromRepo;
import app.windows.PublishToErrorDialog;
import app.windows.PublishToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class SetProjectAL implements ActionListener {

	
	private JTextField[] textFields;
	private String price;
	private String latitude;
	private String longitude;
	private String localName;
	private String pidString;

	public SetProjectAL(JTextField[] textFields) {
		this.textFields = textFields;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			pidString = textFields[0].getText();
			localName = textFields[1].getText();
			price = textFields[2].getText();
			latitude = textFields[4].getText();
			longitude = textFields[3].getText();
		
		}catch (NullPointerException | ArrayIndexOutOfBoundsException a){
			new ErrorDialog("Error").setVisible(true);
		}
		
		if(localName.length() == 0 || price.length() == 0 || latitude.length() == 0 || longitude.length() == 0){
			new ErrorDialog("At least one field was left blank.").setVisible(true);
		}
		
		try{
			Command command = new SetProjectPropertiesFromRepo(MainFrame.getRepositories().getProjectsRepo(), pidString, longitude, latitude, price, localName);
			new SwingWorkerCommand(command, new PublishToMainFrame(), new PublishToErrorDialog()).execute();
			JOptionPane.showInternalConfirmDialog(null, "Success");
		}catch(NumberFormatException nfe){
			new ErrorDialog("Numbers were not introduced in one of the following fields: Price, Longitude of Latitude.").setVisible(true);
		}
		catch(IllegalArgumentException iae){
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}
	
}
