package parser;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import outputMethods.Result;
import parserUtils.CommandFactory;
import parserUtils.CommandParserException;
import parserUtils.DuplicateArgumentsException;
import parserUtils.InvalidCommandArgumentsException;
import parserUtils.InvalidRegisterException;
import parserUtils.UnknownCommandException;



/**
 * Class whose instances are responsible for translating Command Strings into
 * their {@link App.commands.Command} instance counterparts.
 */
public class CommandParser{

	//TODO para aperfeiçoar
	private static CommandParser cp;
	//TODO o construtor tem de passar a private
	private CommandParser(){}
	//TODO o proximo metodo nao e thread safe
	public static CommandParser getInstance(){
		if (cp==null)
			cp=new CommandParser();
		return cp;
	}
	//TODO ...
	public static CommandParser register(CommandsRegister cmdsReg){
		if (cp==null)
			cp=new CommandParser();
		for (String method : cmdsReg.getMethods())
		{
			for (String path : cmdsReg.getPaths())
			{
				for (CommandFactory cmdFactory : cmdsReg.getFactories())
				{
//					this.registerCommand(method, path, cmdFactory);
				}
			}
		}
		return cp;
	}

	/**
	 * The registry root
	 */
	private final Node root = new Node("/");

	/**
	 * Class whose instances represent the parser tree nodes
	 */
	private static class Node{

		/**
		 * If {@code Node.content} starts with "{" and ends with "}", then this
		 * {@code Node} is a placeholder. Also {@see Node.isPlaceHolderNode}.
		 */
		protected final String content;

		/**
		 * {@code Map} with the Node childs (that are not PlaceHolders) of
		 * {@code this.Node}.
		 */
		protected final Map<String, Node> fixedChilds;

		/**
		 * Node child of {@code this.Node} that is a Placeholder.
		 */
		protected Node placeholderChild;

		/**
		 * Factory of the Command that should be instantiated when this Node is
		 * the last Node called in a Command String.
		 */
		protected CommandFactory factory;

		/**
		 * Constructor for {@code Node}.
		 * 
		 * @param content
		 *            The Name of the Node.
		 */
		public Node(String content){
			this.content = content;
			this.fixedChilds = new HashMap<String, Node>();
		}

		/**
		 * Verify if the current Node has a placeholder child.
		 * 
		 * @param currentContent
		 *            The Name of the current Node.
		 * @return
		 */
		public boolean hasPlaceholderChild(String currentContent){
			return placeholderChild != null
					&& !placeholderChild.content.equals(currentContent);
		}

		/**
		 * Adds a fixed child Node to the current node
		 * 
		 * @param currentContent
		 *            The Name of the current Node.
		 * @return The fixed child Node.
		 */
		public Node addFixedChild(String currentContent){
			Node n = new Node(currentContent);
			fixedChilds.put(currentContent, n);
			return n;
		}

		/**
		 * Get the child fixed Node with the key in {@code currentContent} if it
		 * exists, if not it creates the fixed child Node.
		 * 
		 * @param currentContent
		 *            The name of the child fixed Node.
		 * @return The child fixed Node.
		 */
		public Node getAndCreateIfDoesNotExitFixedNode(String currentContent){
			Node node = fixedChilds.get(currentContent);
			if (node == null){
				node = addFixedChild(currentContent);
			}
			return node;
		}

		/**
		 * Get the child placeholder Node with the name {@code currentConcent}
		 * if it exists, otherwise it creates it.
		 * 
		 * @param currentContent
		 *            The child placeholder Node.
		 * @return The child placeholder Node.
		 */
		public Node getAndCreateIfDoesNotExitPlacehoderNode(
				String currentContent){
			if (placeholderChild == null){
				placeholderChild = new Node(currentContent);
			}
			return placeholderChild;
		}

		/**
		 * Returns the child with the specified content or {@code null} if not
		 * found. Implementation details: This method first tries to find the
		 * value in fixedNodes. If it finds it, returns that node. if it does
		 * not, returns the placeholderChild, that may be null;
		 * 
		 * @param currentContent
		 *            the current content to find
		 * @return the node
		 */
		public Node getChildForValue(String content){
			Node n = fixedChilds.get(content);
			if (n == null){
				n = placeholderChild;
			}
			return n;
		}
	}

	/**
	 * Checks whether the given content is relative to a placeholder node.
	 * 
	 * @param currentContent
	 *            The content to be evaluated
	 * @return <code>true</code> if it is a placeholder node content,
	 *         <code>false</code> otherwise
	 */
	private boolean isPlaceHolderNode(String currentContent){
		return currentContent.startsWith("{") && currentContent.endsWith("}");
	}

	/**
	 * Method used to register an association between the string and its
	 * associated command factory.
	 * 
	 * @param method
	 *            The request method (i.e. GET, POST)
	 * @param path
	 *            The resource path
	 * @param cmdFactory
	 *            The command factory instance
	 * @throws InvalidRegisterException
	 */
	public void registerCommand(String method, String path,
			CommandFactory cmdFactory) throws InvalidRegisterException{
		String path2 = method.trim() + path;

		String[] treePathElementsArray = path2.split("/");

		updateSubtree(root, treePathElementsArray, 0, cmdFactory);
	}

	/**
	 * Helper method that recursively updates the parser tree whenever a new
	 * command is registered.
	 * 
	 * @param rootNode
	 *            The tree's root
	 * @param pathElements
	 *            An array containing the elements of the path (i.e.
	 *            /users/{username}) results in an array containing "users" and
	 *            "{username}"
	 * @param pathStartIndex
	 *            The start index of the array (to enable recursion)
	 * @param cmdFactory
	 *            The {@link CommandFactory} instance
	 * @throws InvalidRegisterException
	 *             If the given command cannot be registered (i.e. perhaps the
	 *             grammar is not correct)
	 */
	private void updateSubtree(Node rootNode, String[] pathElements,
			int pathStartIndex, CommandFactory cmdFactory)
			throws InvalidRegisterException{

		if (pathStartIndex == pathElements.length){
			rootNode.factory = cmdFactory;
			return;
		}

		String currentContent = pathElements[pathStartIndex];
		Node node;

		if (!isPlaceHolderNode(currentContent)){
			node = rootNode.getAndCreateIfDoesNotExitFixedNode(currentContent);
		} else{
			if (rootNode.hasPlaceholderChild(currentContent)){
				throw new InvalidRegisterException(
					MessageFormat.format("Command registred with a placeholder with name '{0}', at node with an already existant placeholder child with name '{1}'",
							currentContent, rootNode.placeholderChild.content));
			}
			node = rootNode
					.getAndCreateIfDoesNotExitPlacehoderNode(currentContent);
		}
		updateSubtree(node, pathElements, pathStartIndex + 1, cmdFactory);
	}

	/**
	 * Produces the command instance associated with the given
	 * Method+Path+Parameters ({@code args}).
	 * 
	 * @see CommandParser#getParameters(String)
	 * @see CommandParser#getCommandInternal(Node, String[], int, Map)
	 * 
	 * @param args
	 *            The args for producing the Command instance
	 * @return The associated command instance
	 * @throws CommandParserException
	 */
	public Callable<Result> getCommand(String... args) throws CommandParserException{
		if (args.length < 2 || args.length > 3){
			throw new UnknownCommandException("args must have 2 or 3 elements");
		}

		String cmd = args[0] + args[1];
		String[] pathElements = cmd.split("/");

		Map<String, String> parametersMap = (args.length == 2) ? new HashMap<String, String>()
				: getParameters(args[2]);

		Callable<Result> c = getCommandInternal(root, pathElements, 0, parametersMap);

		return c;
	}

	/**
	 * Produces the {@link Map} with the Command parameters
	 * 
	 * @see CommandParser#getCommand(String...)
	 * @see CommandParser#getCommandInternal(Node, String[], int, Map)
	 * 
	 * @throws InvalidCommandArgumentsException
	 * @throws DuplicateArgumentsException
	 */
	private Map<String, String> getParameters(String parameters)
			throws InvalidCommandArgumentsException,
			DuplicateArgumentsException{
		Map<String, String> parametersMap = new HashMap<>();

		if (parameters != null){
			String[] parametersElements = parameters.split("&");
			for (String parameterElement : parametersElements){
				String[] keyAndValues = parameterElement.split("=");
				if (keyAndValues.length != 2){
					throw new InvalidCommandArgumentsException("InvalidCommandArgumentsException");
				}
				if (parametersMap.containsKey(keyAndValues[0])){
					throw new DuplicateArgumentsException("DuplicateArgumentsException");
				}
				parametersMap.put(keyAndValues[0], keyAndValues[1]);
			}
		}
		return parametersMap;
	}

	/**
	 * Searches the Command to instantiate in the NodeTree, and completes the
	 * {@link Map} with the Command parameters
	 * 
	 * @see CommandParser#getCommand(String...)
	 * @see CommandParser#getParameters(String)
	 * 
	 * @param rootNode
	 *            The current Node in the Tree.
	 * @param pathElements
	 *            The Method+Path associated with the Command to be
	 *            instantiated.
	 * @param pathStartIndex
	 *            The current index in the TreeNode.
	 * @return
	 * @throws UnknownCommandException
	 */
	private Callable<Result> getCommandInternal(Node rootNode, String[] pathElements,
			int pathStartIndex, Map<String, String> parameters)
			throws UnknownCommandException{
		if (pathStartIndex == pathElements.length){
			if (rootNode.factory == null){
				throw new UnknownCommandException(
						"Current node has no command factory!");
			}
			return rootNode.factory.newInstance(parameters);
		}

		String currentContent = pathElements[pathStartIndex];
		Node child = rootNode.getChildForValue(currentContent);
		if (child == null){
			throw new UnknownCommandException("Command path not found!");
		}
		if (isPlaceHolderNode(child.content)){
			parameters.put(
					child.content.substring(1, child.content.length() - 1),
					currentContent);
		}
		return getCommandInternal(child, pathElements, pathStartIndex + 1,
				parameters);
	}
}
