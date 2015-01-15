package app.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import utils.Local;
import utils.Project;
import app.repositoryHolders.RepositoryHolder;

public class PostProjectActionListener implements ActionListener{
	
	JSplitPane splitPane;
	RepositoryHolder repositories;
	JTextField[] textFields;
	
	public PostProjectActionListener(JTextField[] textFields, JSplitPane splitPane, RepositoryHolder repoHolder){
		this.splitPane = splitPane;
		this.repositories = repoHolder;
		this.textFields = textFields;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {	
		
	}
	
}
