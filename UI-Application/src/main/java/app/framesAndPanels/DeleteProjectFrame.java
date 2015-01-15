package app.framesAndPanels;

import java.awt.event.ActionListener;

import javax.swing.JDialog;


public class DeleteProjectFrame extends MainDialogFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {


			DeleteProjectFrame dialog = new DeleteProjectFrame(null);
			dialog.setImage("images/project-delete.png");
			dialog.setTitle("Delete Project");
			dialog.setTitleLabel("Delete Project");
			dialog.setHelpTip("Deletes the project with the specify Id and all its subprojects");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	public DeleteProjectFrame(ActionListener okActionListener) {
		
		super(okActionListener);
		
		this.setImage("images/project-delete.png");
		this.setTitle("Delete Project");
		this.setTitleLabel("Delete Project");
		this.setHelpTip("Deletes the project with the specify Id and all its subprojects");
	
	}

}
