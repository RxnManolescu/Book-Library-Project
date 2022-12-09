package com.library.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

import com.library.entity.Book;
import com.library.entity.Employee;
import com.library.entity.Library;
import com.library.persistence.LibraryDao;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private LibraryDao libraryDao;

	@Override
	public Library borrowBook(Employee employee, Book book) {
		if (book.getNumberOfCopies() <= 0) {
			return null;
		}
		
		Library library = new Library();
		library.setTransactionId(ThreadLocalRandom.current().nextInt(0, 2000000000));
		library.setEmployeeId(employee.getEmployeeId());
		library.setEmployeeName(employee.getEmployeeName());
		library.setBookId(book.getBookId());
		library.setBookType(book.getBookType());
		library.setIssueDate(LocalDate.now());
		library.setReturnDate(null);


		return libraryDao.save(library);
	}
	
	@Override
	public Library returnBook(Library library) {
		library.setReturnDate(LocalDate.now());
		
		return libraryDao.save(library);
	}
		
		
	@Override
	public List<Library> getLibrariesByEmployeeId(int employeeId) {
		List<Library> libraries = libraryDao.findByEmployeeId(employeeId);
		return libraries;
	}
}
