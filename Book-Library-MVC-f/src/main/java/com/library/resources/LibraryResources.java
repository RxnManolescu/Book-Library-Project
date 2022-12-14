package com.library.resources;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.library.entity.Library;
import com.library.service.LibraryService;

@RestController
public class LibraryResources {

	@Autowired
	private LibraryService libraryService;
	
	@RequestMapping(path = "/libraries",method = RequestMethod.GET,produces =MediaType.APPLICATION_JSON_VALUE)
	public List<Library> getBorrowedBooksResource(){
		return libraryService.getBorrowedBooks();
	}
	
	
	@RequestMapping(path = "/libraries/{tid}",method = RequestMethod.GET,produces =MediaType.APPLICATION_JSON_VALUE)
	public Library getBorrowedBookResource(@PathVariable("tid") String tid){
		return libraryService.getRecord(tid);
	}
	
	@RequestMapping(path = "/libraries/{type}/{date}/{eid}", method = RequestMethod.GET,produces =MediaType.APPLICATION_JSON_VALUE)
	public List<Library> libraryByTypeandDateResource(@PathVariable("type") String type, @PathVariable("date") LocalDate date, @PathVariable("eid") int eid){
		return libraryService.getBooksByTypeAndDate(type, date, eid);
	}
	
	
	
}
