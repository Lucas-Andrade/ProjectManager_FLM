package app;

import app.repositoryHolders.InMemoryRepositoryHolder;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class AppUI {
	
	public static void main(String[] args) {
		new MainFrame(new InMemoryRepositoryHolder()).setVisible(true);
	}
}