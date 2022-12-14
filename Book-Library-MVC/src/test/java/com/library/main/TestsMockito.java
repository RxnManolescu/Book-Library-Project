package com.library.main;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.library.entity.Library;
import com.library.persistence.LibraryDao;
import com.library.service.LibraryServiceImpl;

@SpringBootTest
class TestsMockito {

	private LibraryServiceImpl libraryServiceImpl;
	private LibraryDao libraryDao;
	
	@BeforeEach
	void setUp() throws Exception{
		
		libraryServiceImpl = new LibraryServiceImpl();
		
		libraryDao = Mockito.mock(LibraryDao.class);
		//libraryServiceImpl.setLibraryDao(libraryDao);
	}
	
	@AfterEach
	void tearDown() throws Exception{
		
	}
	
	//it works- it outputs what we want it to output
	@Test
	void findLibraryByDateTest() {
		List<Library> libs = new ArrayList<>();
		libs.add(new Library("21112022-12-13", 2, "Martin", 111, "Data Analytics", LocalDate.of(2022, 12, 13) , LocalDate.of(2022, 12, 20), LocalDate.of(2022, 12, 20), 0, 2));
		
		when(libraryDao.findByBookTypeAndIssueDateAndEmployeeId("Data Analytics", LocalDate.of(2022, 12, 13), 2)).thenReturn(libs);
		
		assertEquals(libs, libraryServiceImpl.getBooksByTypeAndDate("Data Analytics", LocalDate.of(2022, 12, 13), 2));
		
	}
	

}
