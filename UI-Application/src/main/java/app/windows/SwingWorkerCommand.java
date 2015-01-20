package app.windows;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import app.AppElement;
import app.domainCommands.Command;

public class SwingWorkerCommand extends SwingWorker<AppElement[], String>{

	Command command;
	ResultsPublisher publisher;
	
	public SwingWorkerCommand(Command command, ResultsPublisher publisher) {
		this.command = command;
		this.publisher = publisher;
	}

	@Override
	protected AppElement[] doInBackground() throws Exception {
		
		publish("Applying changes to database.");
		return command.call();
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
