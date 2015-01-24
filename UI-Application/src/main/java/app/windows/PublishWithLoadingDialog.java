package app.windows;

import java.util.List;

import app.windows.mainFrameAL.mainFrame.LoadingDialog;

/**
 * Super class of all {@code Publishers} that use a {@code LoadingDialog}, while
 * lengthy work is occurring. The {@code LoadingDialog} is disposed when the
 * method {@code publish} is called (be it in an {@code ErrorPublisher}, or in a
 * {@code ResultsPublisher}).
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public abstract class PublishWithLoadingDialog implements Publisher {

	/**
	 * Flag that indicates whether a {@code LoadingDialog} has been created or not.
	 */
	private boolean loadingPublishedFlag;

	private LoadingDialog loadingDialog;

	/**
	 * Constructs the publisher, and sets the {@code loadingPublishedFlag} to
	 * false.
	 */
	public PublishWithLoadingDialog() {
		loadingPublishedFlag = false;
	}

	/**
	 * Constructs and sets visible a new {@code LoadingDialog}, where the text
	 * message will be the first element of {@code chunks}.
	 * 
	 * @param chunks
	 *            Contains the message to be presented in the
	 *            {@code LoadingDialog}
	 */
	public void publish(List<String> chunks) {
		loadingPublishedFlag = true;
		loadingDialog = new LoadingDialog(chunks.get(0));
		loadingDialog.setVisible(true);
	}

	protected void disposeLoadingDialog() {
		if (loadingPublishedFlag) {
			loadingDialog.dispose();
		}
	}
}
