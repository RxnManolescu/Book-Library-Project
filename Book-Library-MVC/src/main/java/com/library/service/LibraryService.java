package com.library.service;

import java.util.List;

import com.library.entity.Book;
import com.library.entity.Employee;
import com.library.entity.Library;

public interface LibraryService {
	
	public Library borrowBook(Employee employee, Book book);
	
	public Library returnBook(Library library);
	
	public List<Library> getLibrariesByEmployeeId(int employeeId);
}
