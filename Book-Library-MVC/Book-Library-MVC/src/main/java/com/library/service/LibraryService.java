package com.library.service;

import java.time.LocalDate;
import java.util.List;

import com.library.entity.Library;
import com.library.entity.Book;
import com.library.entity.Employee;

public interface LibraryService {
	
	List<Book> getBookList();
	
	Library borrowBook(int bookId, int copies, Employee employee );
	
	List<Library> getBorrowedBooks();
	
	Library returnBook(String transactionId, int copies);
	
	Library getRecord(String transactionId);
	
	Employee loginCheck(int id, String password);
	
	List<Library> getLibraryByEmployeeId(int employeeId);
	
	List<Library> getBooksByTypeAndDate(String type, LocalDate date, int empId);
	
	
	
}
