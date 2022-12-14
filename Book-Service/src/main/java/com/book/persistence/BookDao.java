package com.book.persistence;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.book.entity.Book;

@Repository
public interface BookDao extends JpaRepository<Book, Integer> {
	//JPQL
			@Modifying
			@Transactional
			@Query("update Book set numberOfCopies=numberOfCopies-:changeInCopies where bookId=:id") //have to use the class name NOT the table name
			int updateCopies(@Param("id") int id,@Param("changeInCopies") int changeInCopies);

			Book searchBookByBookId(int id);


}
