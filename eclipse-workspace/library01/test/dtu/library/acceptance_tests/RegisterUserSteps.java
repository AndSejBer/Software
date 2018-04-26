package dtu.library.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.library.app.User;
import dtu.library.app.LibraryApp;
import dtu.library.app.OperationNotAllowedException;

public class RegisterUserSteps {

	private LibraryApp libraryApp;
	private String password, errorMessage;
	private User user;

	/*
	 * Note that the constructor is apparently never called, but there are no null
	 * pointer exceptions regarding that libraryApp is not set. When creating the
	 * LoginSteps object, the Cucumber libraries are using that constructor with an
	 * object of class LibraryApp as the default.
	 * 
	 * This also holds for all other step classes that have a similar constructor.
	 * In this case, the <b>same</b> object of class LibraryApp is used as an
	 * argument. This provides an easy way of sharing the same object, in this case
	 * the object of class LibraryApp, among all step classes.
	 * 
	 * This principle is called <em>dependency injection</em>. More information can
	 * be found in the "Cucumber for Java" book available online from the DTU Library.
	 */
	public RegisterUserSteps(LibraryApp libraryApp) {
		this.libraryApp = libraryApp;
	}

	@Given("^I have a user with firstname \"([^\"]*)\", lastname \"([^\"]*)\", and CPRnumber \"([^\"]*)\"$")
	public void iHaveAUserWithNameAndCPR(String firstname, String lastname, String CPRnumber) throws Exception {
		user = new User(firstname,lastname,CPRnumber);
	}

	@When("^I add the user$")
	public void iAddTheUser() throws Exception {
		try {
			libraryApp.addUser(user);
		} catch (OperationNotAllowedException e) {
			errorMessage = e.getMessage();
		}
	}
	
	@Then("^the user with first name \"([^\"]*)\", last name \"([^\"]*)\", and CPR number \"([^\"]*)\" is added to the registry$")
	public void theUserWithFirstNameLastNameAndCPRNumberIsAddedToTheRegistry(String fname, String lname, String cpr)
			throws Exception {
		assertEquals(fname, user.getFirstName());
		assertEquals(lname, user.getLastName());
		assertEquals(cpr, user.getCPRNumber());
		assertTrue(libraryApp.getUsers().contains(user));
	}
	
	@Then("^I get the user error message \"([^\"]*)\"$")
	public void iGetTheUserErrorMessage(String errorMessage) throws Exception {
		assertEquals(errorMessage, this.errorMessage);
	}
}
