package app.windows;

import app.windows.mainFrameAL.mainFrame.ErrorDialog;

/**
 * Super class of all the publishers that publish information
 * to an {@code ErrorDialog}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class PublishToErrorDialog extends ErrorPublisher{

	/**
	 * Will set visible a new instance of {@code ErrorDialog} with the
	 * {@code String} passed as parameter presented as it's message.
	 * 
	 * @param message
	 * 			The message to be presented int the {@code ErrorDialog}.
	 */
	@Override
	public void publish(String message) {
		new ErrorDialog(message).setVisible(true);
		disposeLoadingDialog();
	}

}
