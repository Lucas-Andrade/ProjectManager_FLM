package app;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.framesAndPanels.mainFrameActionListener.mainFrame.MainFrame;
import app.repositoryHolders.InMemoryRepositoryHolder;

public class AppUI {
	
	public static void main(String[] args) {
		new MainFrame(new JSplitPane(), new InMemoryRepositoryHolder(), new Authentication()).setVisible(true);
	}
}