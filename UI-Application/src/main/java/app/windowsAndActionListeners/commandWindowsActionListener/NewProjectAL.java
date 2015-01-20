package app.windowsAndActionListeners.commandWindowsActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import app.commands.AddProjectToRepo;
import app.windowsAndActionListeners.mainFrameActionListener.mainFrame.ErrorDialog;
import app.windowsAndActionListeners.mainFrameActionListener.mainFrame.MainFrame;

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
			latitude = textfields[2].getText();
			longitude = textfields[3].getText();
		}catch (NullPointerException a){
			new ErrorDialog("Error").setVisible(true);
		}
		
		try{
			new SwingWorkerCommand(new AddProjectToRepo(MainFrame.repositories.getProjectsRepo(),name, price, latitude, longitude));
		}catch(NumberFormatException nfe){
			new ErrorDialog("Numbers were not introduced in one of the following fields: Price, Longitude of Latitude.").setVisible(true);
		}
		catch(IllegalArgumentException iae){
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}
	
}
