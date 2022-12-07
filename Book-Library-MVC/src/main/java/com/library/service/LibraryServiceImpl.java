package com.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.persistence.LibraryDao;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private LibraryDao libraryDao;
}
