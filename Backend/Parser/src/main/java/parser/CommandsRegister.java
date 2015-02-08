package parser;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import parserUtils.CommandFactory;

public class CommandsRegister {

	private final Map<String, CommandFactory> cmdsMap;
	private final Map<String, String> pathsMap;

	public CommandsRegister() {
		this.cmdsMap = new HashMap<String, CommandFactory>();
		this.pathsMap = new HashMap<String, String>();
	}

	public void registerCommand(String method, String path, CommandFactory cmdFactory){
		cmdsMap.put(method, cmdFactory);
		pathsMap.put(method, path);
	}
	
	public Collection<String> getPaths() {
		return pathsMap.values();
	}

	public Collection<CommandFactory> getFactories() {
		return cmdsMap.values();
	}

	public Set<String> getMethods() {
		return cmdsMap.keySet();
	}

}
