package app.windows;

import java.util.List;

import app.windows.mainFrameAL.mainFrame.LoadingDialog;

public abstract class PublishWithLoadingDialog implements Publisher{
	
	private boolean loadingPublishedFlag;
	private LoadingDialog loadingDialog;
	
	public PublishWithLoadingDialog(){
		loadingPublishedFlag = false;
	}
	
	public void publish(List<String> chunks){
		loadingPublishedFlag = true;
		loadingDialog = new LoadingDialog(chunks.get(0));
		loadingDialog.setVisible(true);
	}
	
	protected void disposeLoadingDialog() {
		if(loadingPublishedFlag) {
			loadingDialog.dispose();
		}
	}
}
