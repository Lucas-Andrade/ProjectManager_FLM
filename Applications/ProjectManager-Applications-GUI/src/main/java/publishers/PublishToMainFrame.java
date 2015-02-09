package publishers;

import guiElements.mainFrameAL.mainFrame.MainFrame;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

/**
 * Super class of all publishers that publish information about the expected
 * results (when it does not occur any errors of any kind), and will present
 * a {@code LoadingDialog} while lengthy work in background occurs.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public abstract class PublishToMainFrame extends PublishWithLoadingDialog implements ResultsPublisher {
	
	private JSplitPane splitPane = MainFrame.getSplitPane();
	private JPanel mainPanel = new JPanel();
	
	/**
	 * Constructs this {@code Publisher}, and sets the {@code mainPanel} to have a 
	 * {@code BorderLayout}. This panel will be later set as the right panel of the
	 * {@code MainFrame}.
	 */
	public PublishToMainFrame() {
		mainPanel.setLayout(new BorderLayout(0, 0));
	}
	
	public void publish(JComponent component) {
		mainPanel.add(new JScrollPane(component), BorderLayout.CENTER);
		splitPane.setRightComponent(mainPanel);
		splitPane.updateUI();
		disposeLoadingDialog();
	}
}
