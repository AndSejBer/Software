package dtu.library.app;

import java.util.ArrayList;
import java.util.List;

public class LibraryApp {

	private boolean loggedin;
	private String pass = "adminadmin";
	private ArrayList <Book> books = new ArrayList<Book>();
	private ArrayList <User> users = new ArrayList<User>();
	public OperationNotAllowedException test;

	public boolean adminLoggedIn() {
		return loggedin;
	}

	public boolean adminLogin(String password) {
		loggedin = pass.equals(password);
		return loggedin;
	}

	public void adminLogout() {
		loggedin = false;	
	}

	public void addBook(Book book) throws OperationNotAllowedException {
		if (loggedin) {
			books.add(book);
		} else {
			throw new OperationNotAllowedException("Administrator login required");
		}
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public ArrayList <Book> search(String searchText) {
		ArrayList <Book> found = new ArrayList<Book>();
		for (int i = 0; i < books.size(); i++) {
			if(books.get(i).getAuthor().contains(searchText)) {
				found.add(books.get(i));
			} else if (books.get(i).getTitle().contains(searchText)) {
				found.add(books.get(i));
			} else if(books.get(i).getSignature().contains(searchText)) {
				found.add(books.get(i));
			} 
		}
		return found;
	}

	public void addUser(User user) throws OperationNotAllowedException {
		if (loggedin) {
			if (allreadyRegistered(user)) {
				throw new OperationNotAllowedException("User is already registered");
			} else {
				users.add(user);
			}	
		} else {
			throw new OperationNotAllowedException("Administrator login required");
		}
	}

	private boolean allreadyRegistered(User user) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getFirstName().equals(user.getFirstName())||
					users.get(i).getLastName().equals(user.getLastName())||
					users.get(i).getCPRNumber().equals(user.getCPRNumber())) {
				return true;
			}else {
				return false;
			}
		}
		return false; 
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public User getUser(String name) {
		for (int i = 0; i < users.size(); i++) {
			if(users.get(i).getFirstName().equals(name) || users.get(i).getLastName().equals(name)) {
				return users.get(i);
			}
		}
		return null;
	}

	public void borrowBook(Book book, User user) throws OperationNotAllowedException {
		
		if (user.getBorrowedNum() <= 10 && books.contains(book)) {
			user.borrowBook(book);
		} else {
			throw new OperationNotAllowedException("You can't borrow more than 10 books");
		}
	}

}
