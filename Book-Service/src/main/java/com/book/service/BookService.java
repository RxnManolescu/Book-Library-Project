package com.book.service;

import java.util.List;

import com.book.entity.Book;

public interface BookService {

	public List<Book> getAllBooks();
	
	public Book searchBookById(int id); //how do we search by multiple things without creating loads of methods
	//overload this to take int id, String type
	
//	public List<Book> searchBookByType(String type);
	
	boolean updateCopies(int bookId, int changeInCopies);
	
}
