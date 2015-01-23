package app.windows;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;

import app.AppElement;
import app.windows.mainFrameAL.mainFrame.MainFrame;

public class PublishToMainFrame extends ResultsPublisher{
	
	protected JSplitPane splitPane = MainFrame.getSplitPane();
	protected JPanel mainPanel = new JPanel();
	
	public PublishToMainFrame() {
		mainPanel.setLayout(new BorderLayout(0, 0));
	}
	
	@Override
	public void publish(AppElement[] appElements) {
		JTree tree = TreeBuilder.getTree(appElements);
		
		mainPanel.add(new JScrollPane(tree), BorderLayout.CENTER);
		splitPane.setRightComponent(mainPanel);
		splitPane.updateUI();
		disposeLoadingDialog();
	}
}
