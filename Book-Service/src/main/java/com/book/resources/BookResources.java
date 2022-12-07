package com.book.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.book.service.BookService;

@RestController
public class BookResources {
	
	@Autowired
	private BookService bookService;

}
