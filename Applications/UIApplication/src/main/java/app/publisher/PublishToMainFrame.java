package app.publisher;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;

import app.AppElement;
import app.windows.mainFrameAL.mainFrame.MainFrame;

/**
 * Allows to publish information about {@code AppElement}s into the right panel
 * of the {@code MainFrame}. This class presents that information as a {@code JTree}.
 * 
 * The conversion method used to parse the {@code AppElement}s into nodes in the
 * {@code JTree} is general enough to work with all kinds of {@code AppElement}s.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class PublishToMainFrame extends ResultsPublisherWithLoadingDialog{
	
	protected JSplitPane splitPane = MainFrame.getSplitPane();
	protected JPanel mainPanel = new JPanel();
	
	/**
	 * Constructs this {@code Publisher}, and sets the {@code mainPanel} to have a 
	 * {@code BorderLayout}. This panel will be later set as the right panel of the
	 * {@code MainFrame}.
	 */
	public PublishToMainFrame() {
		mainPanel.setLayout(new BorderLayout(0, 0));
	}
	
	/**
	 * Constructs a {@code JTree} using the method {@code TreeBuilder#getTree}. This
	 * {@code JTree} will be added to the {@code mainPanel} as its {@code CENTER} 
	 * component. Then, the {@code mainPanel} is added to the {@code MainFrame}, and
	 * the {@code LoadingDialog} is disposed of, if any was set visible.
	 * 
	 * @see ResultsPublisherWithLoadingDialog#publish(AppElement[])
	 */
	@Override
	public void publish(AppElement[] appElements) {
		JTree tree = TreeBuilder.getTree(appElements);
		
		mainPanel.add(new JScrollPane(tree), BorderLayout.CENTER);
		splitPane.setRightComponent(mainPanel);
		splitPane.updateUI();
		disposeLoadingDialog();
	}
}
