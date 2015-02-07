package app.swingWorkerAndPublisher;

/**
 * Super class of all publishers that publish information about the expected
 * results (when it does not occur any errors of any kind), and will present
 * a {@code LoadingDialog} while lengthy work in background occurs.
 * 
 * @author Filipa Gonçalves, Filipe Maia, Lucas Andrade.
 * @since 19/01/2015
 */
public abstract class ResultsPublisherWithLoadingDialog extends PublishWithLoadingDialog implements ResultsPublisher {

	protected String getPlainText(String result) {
		return null;
	}
}
