package app.commands;

import java.util.Map;

import app.elements.DatabaseElement;

public abstract class BaseCommandResultsOutputMethod extends BaseCommand
{

	public BaseCommandResultsOutputMethod(Map<String, String> parameters)
	{
		super(parameters);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected DatabaseElement internalCall() throws Exception
	{
		// TODO Auto-generated method stub

	}

	abstract protected DatabaseElement internalCallAfterDefiningTheOutputMethodForResults()
			throws Exception;

	@Override
	protected String[] getMandatoryParameters()
	{
		// TODO Auto-generated method stub

	}

}
