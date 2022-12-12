package com.library.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.library.entity.Book;
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
	
	//idk if this is possible (should it be a post method?)
	@RequestMapping(path="/libraries/{bid}/{copies}/{eid}/{pass}",method = RequestMethod.PUT,produces = MediaType.TEXT_PLAIN_VALUE)
	public String borrowBookResource(@PathVariable("bid") int bid, @PathVariable("copies") int copies,@PathVariable("eid") int eid, @PathVariable("pass") String pass ) {
		if(libraryService.borrowBook2(bid,copies, eid, pass) !=null)
			return "Record added";
		else
			return "Record not added";
	}
	
	//111, 1, 1, "password1"
	
}
