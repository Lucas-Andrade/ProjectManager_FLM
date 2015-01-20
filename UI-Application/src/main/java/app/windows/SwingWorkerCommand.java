package app.windows;

import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import app.AppElement;
import app.domainCommands.Command;

public class SwingWorkerCommand extends SwingWorker<AppElement[], AppElement[]>{

	Command command;
	ResultsPublisher publisher;
	
	public SwingWorkerCommand(Command command, ResultsPublisher publisher) {
		this.command = command;
		this.publisher = publisher;
	}

	@Override
	protected AppElement[] doInBackground() throws Exception {
		
		return command.call();
	}
	
	@Override
	protected void done(){
		try {
			publisher.publish(get());
		} catch (InterruptedException | ExecutionException e) {
			//TODO -> uma janela de erro com "Could not get the results." 
		}
	}

}
