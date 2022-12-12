package com.library.main;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.library.entity.Book;
import com.library.entity.BookList;
import com.library.entity.Library;
import com.library.service.LibraryServiceImpl;

@SpringBootTest
class BookLibraryMvcApplicationTests {
	
	@Autowired
	LibraryServiceImpl libraryServiceImpl;
	
	
	@Test
	void testGetBookList() {	
		assertTrue(libraryServiceImpl.getBookList().size()>0);
	}
	
	@Test
	void testBorrowBook2() {
		//pretending Bob is borrowing book 111
		//this is what should be returned from borrowBook2
		//transactionid = employeeIdBookIdIssueDate; say empId = 1, bookId=111, issueDate = 2022-12-01- need to add that to the database 
		Library borrowedBook = new Library("21112022-12-12", 2, "Martin", 111, "Data Analytics", LocalDate.now(), LocalDate.now().plusDays(7), LocalDate.now(), 0, 1);
		
		assertEquals(borrowedBook, libraryServiceImpl.borrowBook2(111, 1, 2, "password2"));
	}

}