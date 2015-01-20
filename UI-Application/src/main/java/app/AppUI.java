package app;

import javax.swing.JSplitPane;

import app.authentication.Authentication;
import app.repositoryHolders.InMemoryRepositoryHolder;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class AppUI {
	
	public static void main(String[] args) {
		new MainFrame(new JSplitPane(), new InMemoryRepositoryHolder(), new Authentication()).setVisible(true);
	}
}