package app.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSplitPane;
import javax.swing.JTextField;

import app.repositoryHolders.RepositoryHolder;

public class DeleteProjectActionListener implements ActionListener{
	
	JSplitPane splitPane;
	RepositoryHolder repositories;
	JTextField[] textFields;
	
	public DeleteProjectActionListener(JTextField[] textFields, JSplitPane splitPane, RepositoryHolder repoHolder){
		this.splitPane = splitPane;
		this.repositories = repoHolder;
		this.textFields = textFields;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {	

	}

}
