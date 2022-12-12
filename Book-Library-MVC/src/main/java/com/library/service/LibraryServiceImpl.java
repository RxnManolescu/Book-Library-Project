package com.library.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//import java.util.concurrent.ThreadLocalRandom;

import com.library.entity.Book;
import com.library.entity.BookList;
import com.library.entity.Employee;
import com.library.entity.Library;
import com.library.persistence.LibraryDao;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private LibraryDao libraryDao;

	//NAT HERE----------------------------------------------
	@Autowired
	private RestTemplate restTemplate;
	
	//getting the list of books for the employee to pick to borrow
	@Override
	public List<Book> getBookList() {
		
		List<Book> wholeBookList = new ArrayList<Book>();
		
		//calling book-service and storing books in bookList object
		BookList bookList = restTemplate.getForObject("http://localhost:8082/books", BookList.class);
		
		//need to use getter to get the list of books from object BookList
		for(Book book:bookList.getBooksList()) {
			wholeBookList.add(book);
		}
		
		return wholeBookList;
	}
	
	//borrow a book- will return a non-empty library object if can be borrowed;
	//will return null if too many copies taken or unable to update the number of copies in the book DB 
	//positive copies to be inputted if using my (Nats) impl, negative copies if using Roxanas impl 
	@Override
	public Library borrowBook2(int bookId, int copies, int employeeId, String password) {
		
		//dealing with number of copies is greater than the available number of copies
		//gets the book by bookId (rest API)
		Book bookToBorrow = restTemplate.getForObject("http://localhost:8082/books/" + bookId, Book.class);
		if(bookToBorrow.getNumberOfCopies()<copies)
			return null;
		
		//updates number of book copies available and outputs the updated message
		String updated = restTemplate.getForObject("http://localhost:8082/books/" +bookId + "/" + copies, String.class);
		//if copies not updated (i.e. book not borrowed then return null)
		if(updated != "Number of copies Updated!")
			return null;
		
		//getting the employees info to add to library 
		Employee myEmp = restTemplate.getForObject("http://localhost:8081/checks/" + employeeId +"/" + password, Employee.class);
		
		//todays date (format- YYYY-MM-DD)
		LocalDate issueDate = LocalDate.now();
		//todays date plus 7 days
		LocalDate expectedReturnDate = LocalDate.now().plusDays(7);
		
		//employeeIdBookIdIssueDate = transaction_Id
		//issue date - doesn't matter if same book taken out same day as will update the record with the correct number of copies, which is what we want
		String empId = String.valueOf(employeeId);
		String bId = String.valueOf(bookId);
		Library borrowedBook = new Library(empId+bId+issueDate, employeeId, myEmp.getEmployeeName(), bookId, bookToBorrow.getBookType(), issueDate, expectedReturnDate, null, 0, copies);
		
		//need to then add this borrowed book to the library database- im doing the save and update way to not deal with the exceptions
		//we can change later if needed - SAVE = SAVE AND UPDATE so if same transaction Id is being entered then will override i think? yes- 
		//if same transaction id then will override that id with new record- this is fine
		libraryDao.save(borrowedBook);
		
		return borrowedBook;
	
	}
	
	//first need to get list of books which have been borrowed (from library dao)- display in html page and get user to click which 1 they want to return
	//needs to be just the borrowed books 
	@Override
	public List<Library> getBorrowedBooks() {
		return libraryDao.findAll();
	}
	
	
	//In controller: if late fees is not 0 then print message "There is no late fee applicable and book has been returned" - COPIES SET ALWAYS TO 1 FOR NOW, in controller
	//negative number of copies inputted here (whatever the number want to return - do the negative of that)
	//NEED TO ADD NUMBER OF COPIES IN LIBRARY - CHECK FOR IF THE COPIES IS 0 THEN DELETE THAT ENTRY INSIDE OF LIBRARY 
	@Override
	public Library returnBook2(int bookId, int copies, int employeeId, String password) {
		//for now, say copies = 1
		
		//transaction_id of book to return in library 
		String empId = String.valueOf(employeeId);
		String bId = String.valueOf(bookId);
		LocalDate todaysDate = LocalDate.now();
		String transaction = empId+bId+todaysDate;
		
		//finding the book to return via transaction ID
		Library returningBook = libraryDao.findByTransactionId(transaction);
		
		//setting the return date as today
		returningBook.setReturnDate(todaysDate);
		
		//getting the expected return date of the book
		LocalDate expectedReturn = returningBook.getExpectedReturnDate();
		
		//difference between returned and expected return date (if dayDelay > 0 then late fees applied)
		Period daysLate = Period.between(expectedReturn, todaysDate);
		int dayDelay = daysLate.getDays();
		
		//conditionals for the late fee (calculating fees in Rs)
		if(dayDelay > 0) {
			if(returningBook.getBookType().equals("Data Analytics")) {
				returningBook.setLateFee(dayDelay * 5);
			}else if (returningBook.getBookType().equals("Technology")) {
				returningBook.setLateFee(dayDelay * 6);
			}else if (returningBook.getBookType().equals("Management")) {
				returningBook.setLateFee(dayDelay * 7);
			}
		}	
		
		//need to save the new record in library - lowered number of copies and if copies = 0 then delete record from the library table  
		int copiesAvailible = returningBook.getNumberOfCopies();
		if(copiesAvailible - 1 == 0) {
			//DELETE THE RECORD IN LIBRARY 
			
		}else {
			//save the returned book with the updated number of copies
			returningBook.setNumberOfCopies(copiesAvailible - 1);
			libraryDao.save(returningBook);
		}
		
		//need to update number of copies (+1) in book service (so -1 in nat impl)
		String updated = restTemplate.getForObject("http://localhost:8082/books/" +bookId + "/" + -1, String.class);
		
		
		return returningBook;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//--------------------------------------------------------
	
//	@Override
//	public Library borrowBook(Employee employee, Book book) {
//		if (book.getNumberOfCopies() <= 0) {
//			return null;
//		}
//		
//		Library library = new Library();
//		//library.setTransactionId(ThreadLocalRandom.current().nextInt(0, 2000000000));
//		library.setEmployeeId(employee.getEmployeeId());
//		library.setEmployeeName(employee.getEmployeeName());
//		library.setBookId(book.getBookId());
//		library.setBookType(book.getBookType());
//		library.setIssueDate(LocalDate.now());
//		library.setReturnDate(null);
//
//
//		return libraryDao.save(library);
//	}
//	
//	@Override
//	public Library returnBook(Library library) {
//		library.setReturnDate(LocalDate.now());
//		
//		return libraryDao.save(library);
//	}
//		
//		
//	@Override
//	public List<Library> getLibrariesByEmployeeId(int employeeId) {
//		List<Library> libraries = libraryDao.findByEmployeeId(employeeId);
//		return libraries;
//	}
	@Override
	public Library borrowBook(Employee employee, Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Library returnBook(Library library) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Library> getLibrariesByEmployeeId(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
