package app.swingWorkerAndPublisher;

import guiElements.mainFrameAL.mainFrame.ErrorDialog;

/**
 * Super class of all publishers that publish information about the expected
 * results (when it does not occur any errors of any kind), and will present
 * a {@code LoadingDialog} while lengthy work in background occurs.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public abstract class ResultsPublisherWithLoadingDialog extends PublishWithLoadingDialog implements ResultsPublisher {
	
	/**
	 * Checks if the {@code result} obtained from the server is an error message. If it is, an
	 * {@code ErrorDialog} is displayed, with the text introduced in the {@code errorMessage}
	 * 
	 * @param result
	 * 		The result obtained by the server
	 * @param errorMessage
	 * 		The message to be displayed if the result was an error message
	 * 
	 * @return true if the result obtained from the server was an error message, and false if it was not
	 */
	protected boolean resultWasError(String result, String errorMessage) {
		String[] array = result.split(":");
		if (array[0] == "Error") {
			new ErrorDialog(errorMessage).setVisible(true);
			disposeLoadingDialog();
			return true;
		} else {
			return false;
		}
	}
}
