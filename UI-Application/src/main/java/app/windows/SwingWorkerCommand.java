package app.windows;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import app.AppElement;
import app.domainCommands.Command;
import app.domainCommands.exceptions.AddedExistingSubproject;

public class SwingWorkerCommand extends SwingWorker<AppElement[], String>{

	Command command;
	ResultsPublisher publisher;
	ErrorPublisher errorPublisher;
	
	public SwingWorkerCommand(Command command, ResultsPublisher publisher, ErrorPublisher errorPublisher) {
		this.command = command;
		this.publisher = publisher;
	}

	@Override
	protected AppElement[] doInBackground() throws Exception {
		
		publish("Status: Applying changes to database.");
		AppElement[] toReturn = null;
		try{
			toReturn = command.call();
		} catch(AddedExistingSubproject e) {
			errorPublisher.publish("noob");
		}
		
		return toReturn;
	}
	
	@Override
	protected void process(List<String> chunks) {
		publisher.publish(chunks);
	}
	
	@Override
	protected void done(){
		try {
			publisher.publish(get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			//TODO -> uma janela de erro com "Could not get the results." 
		
		
	}

}
