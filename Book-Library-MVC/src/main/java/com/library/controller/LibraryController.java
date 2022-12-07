package com.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.library.service.LibraryService;

@Controller
public class LibraryController {

	@Autowired
	private LibraryService libraryService;
}
