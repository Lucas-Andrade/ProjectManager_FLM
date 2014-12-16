package app.commands;

import java.io.IOException;
import java.util.Map;

import app.commands.exceptions.CommandException;
import app.resultsOutputMethods.ResultOutputMethod;

public abstract class BaseGetCommand extends BaseCommand {

	public BaseGetCommand(Map<String, String> parameters) {
		super(parameters);
		// TODO Auto-generated constructor stub
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
