package publishers;

import guiElements.mainFrameAL.mainFrame.LoadingDialog;
import guiElements.mainFrameAL.mainFrame.MainFrame;

import java.util.List;

/**
 * Super class of all publishers that use a {@code LoadingDialog}, while
 * lengthy work is occurring. The {@code LoadingDialog} is disposed when the
 * method {@code publish} is called (be it in an {@code ErrorPublisher}, or in a
 * {@code ResultsPublisher}).
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public abstract class PublishWithLoadingDialog {

	/**
	 * Flag that indicates whether a {@code LoadingDialog} can be created or not.
	 */
	private static boolean shouldCreateWindow;

	/**
	 * The {@code LoadingDialog} to be presented.
	 */
	private static LoadingDialog loadingDialog;

	/**
	 * Constructs the publisher, sets the {@code loadingPublishedFlag} to
	 * {@code false} and instantiates a new {@code LoadingDialog}.
	 */
	public PublishWithLoadingDialog() {
		shouldCreateWindow = true;
		loadingDialog = new LoadingDialog();
	}

	/**
	 * Sets visible a new {@code LoadingDialog}, where the text
	 * message will be the first element of {@code chunks}.
	 * 
	 * @param chunks
	 *            Contains the message to be presented in the
	 *            {@code LoadingDialog}
	 */
	public void publish(List<String> chunks) {
		if (shouldCreateWindow){
			loadingDialog.setMessage(chunks.get(0));
			loadingDialog.setVisible(true);
			MainFrame.setWaitCursor();
		}
	}

	/**
	 * Disposes of the {@code LoadingDialog}.
	 */
	protected void disposeLoadingDialog() {
		shouldCreateWindow = false;
		loadingDialog.dispose();
		MainFrame.setNormalCursor();
	}
}
