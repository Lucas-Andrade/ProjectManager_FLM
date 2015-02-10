package publishers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import guiElements.mainFrameAL.mainFrame.ErrorDialog;

/**
 * Super class of all the publishers that publish information
 * to an {@code ErrorDialog}.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 *
 */
public class PublishToErrorDialog extends PublishWithLoadingDialog implements ErrorPublisher{

	/**
	 * Will set visible a new instance of {@code ErrorDialog} with the
	 * {@code String} passed as parameter presented as it's message.
	 * 
	 * @param message
	 * 			The message to be presented int the {@code ErrorDialog}.
	 */
	@Override
	public void publish(String message) {
		ErrorDialog dialog = new ErrorDialog(message);
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
		disposeLoadingDialog();
	}

	/**
	 * Parses a message in the JSON format into plain text, and then publishes it into an {@code ErrorDialog}
	 * 
	 * @param message
	 * 			The message to be presented int the {@code ErrorDialog}, in JSON format. 
	 * 			This should be a simple {"Message":"The message that will be presented in the ErrorDialog"}.
	 */
	@Override
	public void publishJsonFormatString(String message) {
		String finalMessage;
		try {
			JsonElement element = new JsonParser().parse(message);
			JsonObject object = element.getAsJsonObject();
			finalMessage = object.get("Message").getAsString();
		} catch(JsonParseException | IllegalStateException | ClassCastException | UnsupportedOperationException e) {
			finalMessage = "Could not present the error message.";
		}
		publish(finalMessage);
	}

}
