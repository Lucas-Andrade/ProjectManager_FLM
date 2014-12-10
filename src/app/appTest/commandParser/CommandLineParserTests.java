package app.appTest.commandParser;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import app.AppTest.GetProductsFactory;
import app.AppTest.GetUserFactory;
import app.AppTest.GetUsersFactory;
import app.commandParser.CommandParser;
import app.commandParser.InvalidRegisterException;
import app.commandParser.UnknownCommandException;
import app.commands.Command;
import app.commands.GetSubproject;
import app.commands.GetUser;
import app.commands.GetUsers;

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
