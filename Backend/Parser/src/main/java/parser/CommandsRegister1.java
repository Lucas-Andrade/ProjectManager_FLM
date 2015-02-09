package parser;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import parserUtils.CommandFactory;

public class CommandsRegister1 implements CommandParser.CommandsRegister {

	private final Map<String, CommandFactory> cmdsMap;
	private final Map<String, String> pathsMap;

	public CommandsRegister1() {
		this.cmdsMap = new HashMap<String, CommandFactory>();
		this.pathsMap = new HashMap<String, String>();
	}

	public void registerCommand(String method, String path, CommandFactory cmdFactory){
		cmdsMap.put(method, cmdFactory);
		pathsMap.put(method, path);
	}
	
	public Iterator<String> getPaths() {
		return pathsMap.values().iterator();
	}

	public Iterator<CommandFactory> getFactories() {
		return cmdsMap.values().iterator();
	}

	public Iterator<String> getMethods() {
		return cmdsMap.keySet().iterator();
	}

}
