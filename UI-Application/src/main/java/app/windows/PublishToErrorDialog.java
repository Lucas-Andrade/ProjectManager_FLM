package app.windows;

import app.windows.mainFrameAL.mainFrame.ErrorDialog;

public class PublishToErrorDialog implements ErrorPublisher{

	@Override
	public void publish(String message) {
		new ErrorDialog(message).setVisible(true);
	}

}
