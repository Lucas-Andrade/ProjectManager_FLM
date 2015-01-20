package app.windows.commandWindowsAL.commandWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import app.domainCommands.AddProjectToRepo;
import app.domainCommands.Command;
import app.windows.PublishToMainFrame;
import app.windows.SwingWorkerCommand;
import app.windows.mainFrameAL.mainFrame.ErrorDialog;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class SetProjectAL implements ActionListener {

	
	private JTextField[] textFields;
	private String name;
	private String price;
	private String latitude;
	private String longitude;

	public SetProjectAL(JTextField[] textFields) {
		this.textFields = textFields;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			name = textFields[0].getText();
			price = textFields[1].getText();
			latitude = textFields[2].getText();
			longitude = textFields[3].getText();
		}catch (NullPointerException | ArrayIndexOutOfBoundsException a){
			new ErrorDialog("Error").setVisible(true);
		}
		
		if(name.length() == 0 || price.length() == 0 || latitude.length() == 0 || longitude.length() == 0){
			new ErrorDialog("At least one field was left blank.").setVisible(true);
		}
		
		try{
			Command command = new AddProjectToRepo(MainFrame.getRepositories().getProjectsRepo(), latitude, longitude, name, price);
			new SwingWorkerCommand(command, new PublishToMainFrame()).execute();
			JOptionPane.showInternalConfirmDialog(null, "Sucsses");
		}catch(NumberFormatException nfe){
			new ErrorDialog("Numbers were not introduced in one of the following fields: Price, Longitude of Latitude.").setVisible(true);
		}
		catch(IllegalArgumentException iae){
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}
	
}
