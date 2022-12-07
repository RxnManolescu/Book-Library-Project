package com.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.entity.Book;
import com.book.persistence.BookDao;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookDao bookDao;

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book searchBookById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateQuantity(int bookId, int changeInCopies) {
		// TODO Auto-generated method stub
		return false;
	}


}
