package com.library.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import javax.transaction.Transactional;
import com.library.entity.Library;

@Repository
public interface LibraryDao extends JpaRepository<Library, Integer> {
	
	List<Library> findByEmployeeId(int employeeId);
		
	Library findByTransactionId(String transactionId);
	
	//JPQL- delete a record from library 
	@Modifying
	@Transactional
	@Query("delete from Library where transactionId=:tId")
	int deleteRecord(@Param("tId") String tId);
	
	//get records by type of book and issue date
		List<Library> findByBookTypeAndIssueDateAndEmployeeId(String bookType, LocalDate issueDate, int employeeId);
	
}
