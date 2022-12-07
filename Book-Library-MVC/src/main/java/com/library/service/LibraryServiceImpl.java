package com.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.entity.Library;
import com.library.persistence.LibraryDao;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private LibraryDao libraryDao;

	@Override
	public boolean borrowBook(int employeeId, int bookId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean returnBook(int employeeId, int bookId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Library> getBooksByEmployeeId(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}
}
