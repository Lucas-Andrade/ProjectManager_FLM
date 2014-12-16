package app.commands;

import java.io.IOException;
import java.util.Map;

import app.commands.exceptions.CommandException;
import app.repository.UserRepository;
import app.resultsOutputMethods.ResultOutputMethod;

public abstract class BasePatchCommand extends BaseCommand{

	
	public BasePatchCommand(Map<String, String> parameters) {
		super(parameters);
	}

	@Override
	protected void internalExecute(ResultOutputMethod out)
			throws CommandException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String[] getMandatoryParameters() {
		// TODO Auto-generated method stub
		return null;
	}

}
