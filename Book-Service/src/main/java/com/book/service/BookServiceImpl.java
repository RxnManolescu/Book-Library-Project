package com.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.persistence.BookDao;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookDao bookDao;

}
