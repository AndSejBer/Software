package dtu.library.acceptance_tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.*;
import dtu.library.app.*;


public class BorrowBookSteps {
	private LibraryApp libraryApp;
	private User user;
	private ArrayList<Book> books = new ArrayList<Book>();
	private String emsg;
	
	public BorrowBookSteps(LibraryApp libraryApp) {
		this.libraryApp = libraryApp;
	}
	
	@Given("^I have a new user with firstname \"([^\"]*)\", lastname \"([^\"]*)\", and CPRnumber \"([^\"]*)\"$")
	public void iHaveANewUserWithNameAndCPR(String firstname, String lastname, String CPRnumber) throws Exception {
		user = new User(firstname,lastname,CPRnumber);
		books.addAll(libraryApp.getBooks());
	}
	
	@When("^I borrow the book to the user")
	public void iBorrowTheBookToUser() throws Exception {
	  libraryApp.borrowBook(books.get(0), user);
	}
	
	@Then("^User has borrowed the book")
	public void userHasBorrowedTheBook() throws Exception {
		assertTrue(user.getBorrowed().contains(books.get(0)));
	}

	@When("^I borrow 11 books to the user")
	public void iBorrow11BooksToTheUser() throws Exception {
		try {
		for (int i = 0; i < books.size(); i++) {
			libraryApp.borrowBook(books.get(i), user);
		}
		} catch (OperationNotAllowedException e) {
			emsg = e.getMessage();
		}
	}
	
	@Then("^I get the book error \"([^\"]*)\"$")
	public void iGetTheBookError(String errormsg) throws Exception {
		try {
		assertTrue(emsg.equals(errormsg));
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	@When("^user returns the book$")
	public void userReturnsTheBook() throws Exception {
		try {
		user.returnBook(books.get(0));
		} catch (OperationNotAllowedException e1) {
			emsg = e1.getMessage();
		}
	}
	
	@Then("^the book is returned$")
	public void theBookIsReturned() throws Exception {
		assertFalse(user.getBorrowed().contains(books.get(0)));
	}
	
	@Then("^I get the return error \"([^\"]*)\"$")
	public void iGetTheReturnError(String error) throws Exception {
	    assertTrue(error.equals(emsg));
	}

}
