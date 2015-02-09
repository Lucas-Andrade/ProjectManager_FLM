/**
 * 
 */
package app;

import java.util.Iterator;

import parser.CommandParser.CommandsRegister;
import parserUtils.CommandFactory;

/**
 * @author fm
 *
 */
public class ConsoleCommandsRegister implements CommandsRegister {

	/**
	 * 
	 */
	public ConsoleCommandsRegister() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see parser.CommandParser.CommandsRegister#getMethods()
	 */
	@Override
	public Iterator<String> getMethods() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see parser.CommandParser.CommandsRegister#getPaths()
	 */
	@Override
	public Iterator<String> getPaths() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see parser.CommandParser.CommandsRegister#getFactories()
	 */
	@Override
	public Iterator<CommandFactory> getFactories() {
		// TODO Auto-generated method stub
		return null;
	}

}
