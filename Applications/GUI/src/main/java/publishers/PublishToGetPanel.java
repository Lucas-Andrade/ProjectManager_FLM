package publishers;

import guiElements.mainFrameAL.FrameAndPanelHolder;

import javax.swing.JComponent;
import javax.swing.JScrollPane;

/**
 * Super class of all publishers that publish information about the results
 * (when it does not occur any errors of any kind) to the last used
 * {@code GetPanel}, and will present a {@code LoadingDialog} while lengthy work
 * in background occurs.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public abstract class PublishToGetPanel extends PublishWithLoadingDialog
		implements ResultsPublisher {
	
	/**
	 * Publishes the {@code JComponent} passed as parameter to the last used
	 * {@code MainGetPanel}. Also disposes the {@code LoadingDialog} if
	 * any was set visible.
	 * 
	 * @param component
	 */
	protected void publish(JComponent component) {
		FrameAndPanelHolder.getLastPanel().setResults(new JScrollPane(component));
		disposeLoadingDialog();
	}

}
