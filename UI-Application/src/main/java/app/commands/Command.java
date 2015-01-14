package app.commands;

import java.awt.event.ActionListener;

public interface Command extends ActionListener{
	public abstract void execute();
}
