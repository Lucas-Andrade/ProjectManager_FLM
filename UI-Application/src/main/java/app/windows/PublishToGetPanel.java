package app.windows;

import javax.swing.JScrollPane;
import javax.swing.JTree;

import app.AppElement;
import app.windows.commandWindowsAL.commandWindows.MainGetPanel;

public class PublishToGetPanel extends ResultsPublisher{

	@Override
	public void publish(AppElement[] appElements) {
		
		JTree tree = TreeBuilder.getTree(appElements);
		MainGetPanel.setResults(new JScrollPane(tree));
		disposeLoadingDialog();
	}
}
