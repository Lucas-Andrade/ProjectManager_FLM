package app.framesAndPanels;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

import app.repositoryHolders.InMemoryRepositoryHolder;
import app.result.CommandResult;
import app.result.PostProjectResult;

import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;


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


			DeleteProjectFrame dialog = new DeleteProjectFrame(new PostProjectResult(new JSplitPane(), new InMemoryRepositoryHolder()));
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

	private ProjectID projectId;

	/**
	 * Create the dialog.
	 */

	public DeleteProjectFrame(CommandResult result) {
		super(result);
		GridBagLayout gridBagLayout = (GridBagLayout) getMainDialogPanel().getLayout();
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 100, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 32, 0, 0, 0, 0, 0};
		
		this.setImage("images/project-delete.png");
		this.setTitle("Delete Project");
		this.setTitleLabel("Delete Project");
		this.setHelpTip("Deletes the project with the specify Id and all its subprojects");
		
			projectId = new ProjectID();
			GridBagConstraints gbc_lblProjectID = new GridBagConstraints();
			gbc_lblProjectID.gridwidth = 4;
			gbc_lblProjectID.anchor = GridBagConstraints.SOUTHWEST;
			gbc_lblProjectID.insets = new Insets(0, 0, 5, 5);
			gbc_lblProjectID.gridx = 2;
			gbc_lblProjectID.gridy = 4;
			getMainDialogPanel().add(projectId, gbc_lblProjectID);
	}

	
	//Para n me esquecer...
//	public void actionPerformed(ActionEvent e){
//		if (e.getSource() == okButton){
//		//se pressionado o botao1, pedimos confirmação do usuário
//		int resposta = JOptionPane.showConfirmDialog(null, "Do you want to delete this project?",
//		"Confirm", JOptionPane.OK_CANCEL_OPTION);
//		//se a resposta for sim, encerra a aplicação
//		if (resposta == JOptionPane.OK_OPTION){
//		System.exit(0);
//		}
//	}	
//		addItem(Object) Adiciona o objeto como um novo item
//		getSelectedItem() Retorna o objeto que está selecionado	
//	}
}
