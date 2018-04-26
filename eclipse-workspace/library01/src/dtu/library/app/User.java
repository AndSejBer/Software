package dtu.library.app;

import java.util.ArrayList;

public class User {
	
	private String fname, lname, cpr;
	private int borrowednum = 0;
	private ArrayList <Book> borrowed = new ArrayList <Book>();

	public User(String fname, String lname, String cpr) {
		this.fname = fname;
		this.lname = lname;
		this.cpr = cpr;
	}

	public String getFirstName() {
		return fname;
	}

	public String getLastName() {
		return lname;
	}

	public String getCPRNumber() {
		return cpr;
	}
	
	public ArrayList<Book> getBorrowed() {
		return borrowed;
	}
	
	public int getBorrowedNum() {
		return borrowednum;
	}

	public void borrowBook(Book book) {
		borrowed.add(book);
		borrowednum++;
	}

	public void returnBook(Book book) throws OperationNotAllowedException {
		if(borrowed.contains(book)) {
			borrowed.remove(book);
		} else {
			throw new OperationNotAllowedException("That book is not borrowed to the user!");
		}
	}

}
