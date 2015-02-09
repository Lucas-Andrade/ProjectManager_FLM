package publishers;

import guiElements.Authentication;
import guiElements.mainFrameAL.mainFrame.ErrorDialog;

/**
 * A publisher class with the express purpose of not publishing.
 * Instead, the {@code Authentication} class is updated.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 05/02/2015
 *
 */
public class InternalAuthenticationPublish extends PublishWithLoadingDialog implements ResultsPublisher{

	/**
	 * Will not publish. 
	 */
	@Override
	public void publish(String element) {
		String[] array = element.split(":");
		
		if (array[0] == "Error") {
			new ErrorDialog("The username or password you entered is wrong.").setVisible(true);
			disposeLoadingDialog();
			return;
		}
		
		try {
			Authentication.authenticatePossible();
		} catch(IllegalStateException e) {
			new ErrorDialog("Could not authenticate.").setVisible(true);
		}
		
		disposeLoadingDialog();
	}

}
