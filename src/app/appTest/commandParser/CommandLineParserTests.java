package app.AppTest.commandParser;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import App.commandParser.CommandParser;
import App.commandParser.CommandParserException;
import App.commandParser.DuplicateArgumentsException;
import App.commandParser.InvalidCommandArgumentsException;
import App.commandParser.InvalidRegisterException;
import App.commandParser.UnknownCommandException;
import formjava.module2.travelAgency.commands.Command;
import formjava.module2.travelAgency.commands.GetProduct;
import formjava.module2.travelAgency.commands.GetProducts;
import formjava.module2.travelAgency.commands.PostProduct;

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
		cp.registerCommand("GET", "/products", new GetProducts.Factory(null));
		cp.registerCommand("GET", "/products/{id}", new GetProduct.Factory(null));
		cp.registerCommand("POST", "/products/{productType}", new PostProduct.Factory(null));
	}
	
	@Test
	public void shouldGetTheGetProductsCommand() throws CommandParserException {
		// Arrange
		
		// Act
		Command c = cp.getCommand("GET", "/products");
		
		// Assert
		assertTrue(c instanceof GetProducts);
	}
	
//	@Test
//	public void shouldGetTheGetUsersCommand() throws CommandParserException {
//		// Arrange
//		Command c = cp.getCommand("GET", "/users");
//		
//		assertTrue(c instanceof GetUsers);
//	}
	
	@Test
	public void shouldGetTheGetProductCommand() throws CommandParserException {
		// Arrange
		Command c = cp.getCommand("GET", "/products/10");
		
		assertTrue(c instanceof GetProduct);
	}
	
	@Test
	public void shouldGetThePostProductCommand() throws CommandParserException {
		// Arrange
		Command c = cp.getCommand("POST", "/products/Hotel", "name=Hotel1&description=Descr");
		
		assertTrue(c instanceof PostProduct);
	}
	
	@Test(expected=DuplicateArgumentsException.class)
	public void shouldThrowExceptionDuplicateParameter() throws CommandParserException {
		// Arrange
		
		// Act
		Command c = cp.getCommand("GET", "/products/10/abc", "a=10&a=123");
	}
	
	@Test(expected=InvalidCommandArgumentsException.class)
	public void shouldThrowExceptionForInvalidArguments() throws CommandParserException {
		// Arrange
		
		// Act
		Command c = cp.getCommand("GET", "/products/10/abc", "a=10a=123");
	}
	
	@Test(expected=InvalidRegisterException.class)
	public void shouldNotRegisterCommandHavingAPlaceholderInTheSamePositionOfARegisterdOneButWithDiffrentName() throws CommandParserException {
		// Arrange
		
		// Act
		cp.registerCommand("GET", "/products/{id1}", null);
	}

	
	@Test(expected=UnknownCommandException.class)
	public void shouldThrowExceptionForAnUnregistredCommandLongerThanAnExistantOne() throws CommandParserException {
		// Arrange
		
		// Act
		Command c = cp.getCommand("GET", "/products/10/abc");
		
	}
	
	@Test(expected=UnknownCommandException.class)
	public void shouldThrowExceptionForAnUnregistredCommandShorterThanAnExistantOne() throws CommandParserException {
		// Arrange
		
		// Act
		Command c = cp.getCommand("GET", "/");
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionCallingGetCommandWithMoreThanTwoArguments() throws CommandParserException {
		// Arrange
		
		// Act
		Command c = cp.getCommand("1", "2", "3", "4");
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionCallingGetCommandWithLessThanTwoArguments() throws CommandParserException {
		// Arrange
		
		// Act
		Command c = cp.getCommand("1");
		
	}
	
	@Test
	public void shouldXXXXX() throws CommandParserException {
		// Arrange
		Command c = cp.getCommand("GET", "/products/abc");
		
		assertTrue(c instanceof GetProduct);
	}
}
