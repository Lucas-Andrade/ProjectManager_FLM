package App.commandParser;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import App.commands.Command;
import App.commands.CommandFactory;

/**
 * Class whose instances are responsible for translating command strings into
 * their {@link App.commands.Command} instance counterparts.
 * 
 * Implementation notes: (TODO)
 */
public class CommandParser {

	/**
	 * The registry root
	 */
	private final Node root = new Node("/");

	/**
	 * Class whose instances represent the parser tree nodes
	 */
	private static class Node {

		/**
		 * If {@code Node.content} starts with "{" and ends with "}", then this {@code Node}
		 * is a placeholder. Also {@see Node.isPlaceHolderNode}.
		 */
		public final String content;
		
		public final Map<String, Node> fixedChilds;
		public Node placeholderChild;              // nó placeholder
		
		public CommandFactory factory;

		// nós com produção de comando -> através da factory
		public Node(String content) 
		{
			this.content = content;
			this.fixedChilds = new HashMap<>();
		}

		/**
		 * Verify if the current node has a placeholder child
		 * @param currentContent
		 * @return
		 */
		public boolean hasPlaceholderChild(String currentContent) 
		{
			return placeholderChild != null	&& !placeholderChild.content.equals(currentContent);
		}

		/**
		 * Adds a fixed child node to the current node 
		 * @param currentContent
		 * @return
		 */
		public Node addFixedChild(String currentContent) {
			Node n;
			fixedChilds.put(currentContent, n = new Node(currentContent));
			return n;
		}

		/**
		 * Get the child fixed node with the key in {@code currentConcent}
		 * @param currentContent the key name of the child fixed node  
		 * @return the child fixed node with the key name if its available, otherwise create it and returns it
		 */
		public Node getAndCreateIfDoesNotExitFixedNode(String currentContent) {
			Node node = fixedChilds.get(currentContent);
			if (node == null)
				node = addFixedChild(currentContent);
			return node;
		}

		/**
		 * Get the child placeholder node with the key in {@code currentConcent}
		 * @param currentContent the child placeholder node with the key name 
		 * @return the child placeholder node if exists, otherwise create it and returns it
		 */
		public Node getAndCreateIfDoesNotExitPlacehoderNode(
				String currentContent) {
			if (placeholderChild == null) {
				placeholderChild = new Node(currentContent);
			}
			return placeholderChild;
		}

	/**
	 * Returns the child with the specified content or {@code null} if not
	 * found. 
	 * Implementation details: This method first tries to find the
	 * value in fixedNodes. If it finds it, returns that node. if it does
	 * not, returns the placeholderChild, that may be null;
	 * 
	 * @param currentContent      the current content to find
	 * @return the node
	 */
		public Node getChildForValue(String content) {
			Node n = fixedChilds.get(content);
			if (n == null) {
				n = placeholderChild;
			}
			return n;
		}
	}

	/**
	 * Checks whether the given content is relative to a placeholder node.
	 * 
	 * @param currentContent    The content to be evaluated
	 * @return <code>true</code> if it is a placeholder node content,
	 *         <code>false</code> otherwise
	 */
	private boolean isPlaceHolderNode(String currentContent) {
		return currentContent.startsWith("{") && currentContent.endsWith("}");
	}

	
	
	
	/**
	 * Method used to register an association between the string and its
	 * associated command factory.
	 * 
	 * @param method      The request method (i.e. GET, POST)
	 * @param path        The resource path
	 * @param cmdFactory  The command factory instance
	 * @throws InvalidRegisterException
	 */
	public void registerCommand(String method, String path,CommandFactory cmdFactory) 
			throws InvalidRegisterException {
		// Para fazer a distribuição do conteúdo da path pelos nós facilita se o
		// método estiver junto à path, assim concantenam-se estes dois parâmetros.
		path = method.trim() + path;
		
		String[] treePathElementsArray = path.split("/"); 
		
		updateSubtree(root, treePathElementsArray, 0, cmdFactory);
	}

	/**
	 * Helper method that recursively updates the parser tree whenever a new
	 * command is registered.
	 * 
	 * @param rootNode       The tree's root
	 * @param pathElements
	 *            An array containing the elements of the path (i.e.
	 *            /users/{username}) results in an array containing "users" and "{username}"
	 * @param pathStartIndex     The start index of the array (to enable recursion)
	 * @param cmdFactory         The {@link CommandFactory} instance
	 * @throws InvalidRegisterException
	 *             If the given command cannot be registered (i.e. perhaps the
	 *             grammar is not correct)
	 */
	private void updateSubtree(Node rootNode, String[] pathElements,
			int pathStartIndex, CommandFactory cmdFactory)
			throws InvalidRegisterException 
	{
		
	//se a path já foi toda percorrida -> criação da command Factory
		if (pathStartIndex == pathElements.length) {
			// TODO : Should we check if this registration has already been made?
			rootNode.factory = cmdFactory;
			return;
		}

		// se ainda não chegámos ao fim da path, fazemos a verificação se o
		// currentContent corresponde a um nó fixo ou placeholder
		String currentContent = pathElements[pathStartIndex];
		Node node;
		
		//se não é um placeholder verifica se existe um nó fixo com o nome currentContent
		if (!isPlaceHolderNode(currentContent))   
		{
			node = rootNode.getAndCreateIfDoesNotExitFixedNode(currentContent);
		} else 
		{
			// se é um placeholder verifica se o nó já tem algum nó filho
			// placholder, uma vez que cada nó só pode ter um nó filho placeholder
			if (rootNode.hasPlaceholderChild(currentContent)) 
			{
				throw new InvalidRegisterException(
						MessageFormat
								.format("Command registred with a placeholder with name '{0}', at node with an already existant placeholder child with name '{1}'",
										currentContent, rootNode.placeholderChild.content));
			}
			node = rootNode
					.getAndCreateIfDoesNotExitPlacehoderNode(currentContent);
		}
		updateSubtree(node, pathElements, pathStartIndex + 1, cmdFactory);
	}


	
	/**
	 * Produces the command instance associated with the given arguments
	 * 
	 * @param args
	 * @return The associated command instance
	 * @throws CommandParserException
	 */
	public Command getCommand(String... args) throws CommandParserException 
	{
				
		if (args.length < 2 || args.length > 3) 
		{
			throw new UnknownCommandException("args must have 2 or 3 elements");
		}

		String cmd = args[0] + args[1];   // method + path
		String[] pathElements = cmd.split("/");

		// se args.length == 2, cria-se um HashMap<String, String>() vazio para
		// inserir os placeholders da path, caso args.length == 3, primeiro
		// obtém-se os parametros presentes em arg[2]
		Map<String, String> parametersMap = (args.length == 2) ? new HashMap<String, String>()
				: getParameters(args[2]);

		Command c = getCommandInternal(root, pathElements, 0, parametersMap);

		return c;

	}

	
	/**
	 * Produces the {@link Map} with the parameters
	 * 
	 * @throws InvalidCommandArgumentsException
	 * @throws DuplicateArgumentsException
	 */
	private Map<String, String> getParameters(String parameters)
			throws InvalidCommandArgumentsException,
			DuplicateArgumentsException 
	{
		Map<String, String> parametersMap = new HashMap<>();
		
		if (parameters != null) {
			String[] parametersElements = parameters.split("&");
			for (String parameterElement : parametersElements) 
			{
				String[] keyAndValues = parameterElement.split("=");
				if (keyAndValues.length != 2)
					throw new InvalidCommandArgumentsException();
				
				if (parametersMap.containsKey(keyAndValues[0]))
					throw new DuplicateArgumentsException();
				parametersMap.put(keyAndValues[0], keyAndValues[1]);
			}
		}
		return parametersMap;
	}
	
	
	/**
	 * 
	 * @param rootNode
	 * @param pathElements
	 * @param pathStartIndex
	 * @return
	 * @throws UnknownCommandException
	 */
	private Command getCommandInternal(Node rootNode, String[] pathElements,
			int pathStartIndex, Map<String, String> parameters)
			throws UnknownCommandException 
	{
		if (pathStartIndex == pathElements.length) 
		{
			if (rootNode.factory == null)
				throw new UnknownCommandException("Current node has no command factory!");

			return rootNode.factory.newInstance(parameters);
		}

		String currentContent = pathElements[pathStartIndex];
		Node child = rootNode.getChildForValue(currentContent);
		if (child == null)
			throw new UnknownCommandException("Command path not found!");

		if (isPlaceHolderNode(child.content))
			parameters.put(child.content.substring(1, child.content.length() - 1),currentContent);

		return getCommandInternal(child, pathElements, pathStartIndex + 1, parameters);
	}
}
