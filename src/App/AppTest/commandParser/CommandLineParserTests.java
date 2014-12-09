package App.AppTest.commandParser;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import App.AppTest.GetProductsFactory;
import App.AppTest.GetUserFactory;
import App.AppTest.GetUsersFactory;
import App.commandParser.CommandParser;
import App.commandParser.InvalidRegisterException;
import App.commandParser.UnknownCommandException;
import App.commands.Command;
import App.commands.GetSubproject;
import App.commands.GetUser;
import App.commands.GetUsers;

//
// Create an hotel product: 
// POST /products/hotel strs=5&name=Hotel1&description=Descr hotel1&loginName=lfalcao&loginPassword=slb
// POST /products/hotel strs=4&name=Hotel2&description=Descr hotel2&loginName=lfalcao&loginPassword=slb

// Create a program: 
// POST /products/program name=Program1&description=Descr+Program1&loginName=lfalcao&loginPassword=slb&products=1,3,25,8

// Get a product details: 
// GET /products
// GET /products/programs
// GET /products/programs/10

public class CommandLineParserTests {
	
	
	static CommandParser cp;
	
	@BeforeClass
	public static void beforeClas() throws InvalidRegisterException {
		cp = new CommandParser();
		cp.registerCommand("GET", "/users", new GetUsersFactory());
		cp.registerCommand("GET", "/users/{username}", new GetUserFactory());
		cp.registerCommand("GET", "/products", new GetProductsFactory());
	}
	
	@Test
	public void shouldGetTheGetProductsCommand() throws UnknownCommandException {
		// Arrange
		
		// Act
		Command c = cp.getCommand("GET", "/products");
		
		// Assert
		assertTrue(c instanceof GetSubproject);
	}
	
	@Test
	public void shouldGetTheGetUsersCommand() throws UnknownCommandException {
		// Arrange
		Command c = cp.getCommand("GET", "/users");
		
		assertTrue(c instanceof GetUsers);
	}
	
	@Test
	public void shouldGetTheGetUserCommand() throws UnknownCommandException {
		// Arrange
		Command c = cp.getCommand("GET", "/users/10", "username=10");
		
		assertTrue(c instanceof GetUser);
	}
}
