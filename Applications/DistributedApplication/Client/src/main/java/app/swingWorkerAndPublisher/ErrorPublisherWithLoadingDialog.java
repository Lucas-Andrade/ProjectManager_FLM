package app.swingWorkerAndPublisher;

import guiElements.mainFrameAL.mainFrame.ErrorDialog;

/**
 * Abstract class of all the {@code Publisher} classes that will publish
 * information about an encountered error. This allows for the errors to be 
 * presented in a different manner than the results, and will present
 * a {@code LoadingDialog} while lengthy work in background occurs.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class ErrorPublisherWithLoadingDialog extends PublishWithLoadingDialog implements ErrorPublisher{

	@Override
	public void publish(String message) {
		ErrorDialog dialog = new ErrorDialog(message);
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
		disposeLoadingDialog();
	}
	
}
