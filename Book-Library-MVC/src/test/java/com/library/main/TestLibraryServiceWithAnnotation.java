package com.library.main;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.library.entity.Book;
import com.library.entity.Employee;
import com.library.entity.Library;
import com.library.persistence.LibraryDao;
import com.library.service.LibraryServiceImpl;


@RunWith(MockitoJUnitRunner.class)
class TestLibraryServiceWithAnnotation {

	@InjectMocks
	private LibraryServiceImpl libraryServiceImpl;
	@Mock
	private LibraryDao libraryDao;
	private AutoCloseable autoCloseable;
	
	@BeforeEach
	void setUp() throws Exception {
		
		autoCloseable=MockitoAnnotations.openMocks(this);
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
	autoCloseable.close();
	}

	@Test
	void testBorrowBookOne() {
		Library library = new Library(0, 1, "J", 1001, "Management", LocalDate.now(), null);
		when(libraryDao.save(library)).thenReturn(library);
			
		assertNotNull(libraryServiceImpl.borrowBook(
			new Employee(1, "J", "password", 1),
			new Book(1001, "book name", "Management", "book author", "book description", 10)
		));
	}
	
	@Test
	void testBorrowBookTwo() {
		Library library = new Library(0, 1, "J", 1001, "Management", LocalDate.now(), null);
		when(libraryDao.save(library)).thenReturn(library);
			
		assertNull(libraryServiceImpl.borrowBook(
			new Employee(1, "J", "password", 1),
			new Book(1001, "book name", "Management", "book author", "book description", 0)
		));	
	}

	@Test
	void testReturnBook() {
		Library beforeReturn = new Library(0, 1, "J", 1001, "Management", LocalDate.now(), null);
		Library afterReturn = new Library(0, 1, "J", 1001, "Management", LocalDate.now(), LocalDate.now());
		
		when(libraryDao.save(afterReturn)).thenReturn(afterReturn);
		
		Library result = libraryServiceImpl.returnBook(beforeReturn);
		assert(result).equals(afterReturn);
	}


	@Test
	void testGetLibrariesByEmployeeId() {
		List<Library> libraries = new ArrayList<Library>();
		Library library = new Library(0, 2, "J", 1001, "Management", LocalDate.now(), null);
		libraries.add(library);
		when(libraryDao.findByEmployeeId(2)).thenReturn(libraries);	
		assert(libraryServiceImpl.getLibrariesByEmployeeId(2)).equals(libraries);		
	}


}
