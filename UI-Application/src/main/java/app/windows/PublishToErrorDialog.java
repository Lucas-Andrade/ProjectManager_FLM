package app.windows;

import app.windows.mainFrameAL.mainFrame.ErrorDialog;

public class PublishToErrorDialog extends ErrorPublisher{

	@Override
	public void publish(String message) {
		new ErrorDialog(message).setVisible(true);
		disposeLoadingDialog();
	}

}
