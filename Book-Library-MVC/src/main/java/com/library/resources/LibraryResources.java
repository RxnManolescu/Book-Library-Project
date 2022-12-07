package com.library.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.library.service.LibraryService;

@RestController
public class LibraryResources {

	@Autowired
	private LibraryService libraryService;
}
