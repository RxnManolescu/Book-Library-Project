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
		return bookDao.findAll();
	}

	@Override
	public Book searchBookById(int id) {
		 return bookDao.findById(id).orElse(null);
	}

	//if book record is updated, will return true 
	@Override
	public boolean updateQuantity(int bookId, int changeInCopies) {
		return (bookDao.updateCopies(bookId, changeInCopies)>0);
	}


}
