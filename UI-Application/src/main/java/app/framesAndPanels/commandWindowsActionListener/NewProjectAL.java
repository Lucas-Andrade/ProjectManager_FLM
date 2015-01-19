package app.framesAndPanels.commandWindowsActionListener;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import app.framesAndPanels.mainFrameActionListener.mainFrame.ErrorDialog;
import app.result.WarningMessagePanel;

public class NewProjectAL implements ActionListener {

	private JTextField[] textfields;

	public NewProjectAL(JTextField[] textFields2){
		this.textfields = textFields2;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String[] parameters = new String[4];
		try{
			parameters[0] = textfields[0].getText();
			parameters[1] = textfields[1].getText();
			parameters[2] = textfields[2].getText();
			parameters[3] = textfields[3].getText();
		}catch (NullPointerException a){
			new ErrorDialog("Error").setVisible(true);
		}
		
		try{
			new SwingWorkerCommand(new NewProject, parameters);
		}catch(NumberFormatException nfe){
			new ErrorDialog("Numbers were not introduced in one of the following fields: Price, Longitude of Latitude.").setVisible(true);
		}
		catch(IllegalArgumentException iae){
			new ErrorDialog("Invalid or null Argument.\n" + iae.getMessage());
		}
	}
	
}
