package com.library.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

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
import org.springframework.boot.test.context.SpringBootTest;

import com.library.persistence.LibraryDao;
import com.library.service.LibraryService;

@RunWith(MockitoJUnitRunner.class)
class BookLibraryMvcApplicationTests {

	@InjectMocks
	private LibraryService libraryService;
	
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

//	@Test
//	void testBorrowBookOne() {
//		//Specifying the behavior of the mock
//		when(libraryDao.borrowBook(new Employee(110, "JJJJ", "Exdecutive", "Sales", 23000, LocalDate.now()))).thenReturn(1);
//		
//		assertTrue(employeeServiceImpl.addEmployee(new Employee(110, "JJJJ", "Exdecutive", "Sales", 23000, LocalDate.now())));
//	}
//	
//	@Test
//	void testAddEmployeeTwo() {
//		when(employeedao.insertRecord(new Employee(110, "JJJJ", "Exdecutive", "Sales", 23000, LocalDate.now()))).thenReturn(0);
//		
//		assertFalse(employeeServiceImpl.addEmployee(new Employee(110, "JJJJ", "Exdecutive", "Sales", 23000, LocalDate.now())));
//	}
//
//	@Test
//	void testDeleteEmployeeOne() {
//		when(employeedao.deleteRecord(101)).thenReturn(1);
//		
//		assertTrue(employeeServiceImpl.deleteEmployee(101));
//		
//	}
//
//	@Test
//	void testDeleteEmployeeTwo() {
//		when(employeedao.deleteRecord(101)).thenReturn(0);
//		
//		assertFalse(employeeServiceImpl.deleteEmployee(101));
//		
//	}
//
//	@Test
//	void testGeneratePaySlip() {
//		when(employeedao.searchRecord(101)).thenReturn(new Employee(101, "AAAA", "Executive", "HR", 10000, LocalDate.now()));
//		
//		EmployeePaySlip paySlip=new EmployeePaySlip(new Employee(101, "AAAA", "Executive", "HR", 10000, LocalDate.now()), 1800, 1200, 800, 12200);
//		
//		assertEquals(paySlip, employeeServiceImpl.generatePaySlip(101));
//		
//	}
	
}
