package com.library.service;

import java.util.List;

import com.library.entity.Library;

public interface LibraryService {

	public boolean borrowBook(int employeeId, int bookId);
	
	public boolean returnBook(int employeeId, int bookId);
	
	public List<Library> getBooksByEmployeeId(int employeeId);
	
	
}
