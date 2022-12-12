package com.library.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Library {

	@Id
	private int transactionId;
	private int employeeId;
	private String employeeName;
	private int bookId;
	private String bookType;
	private LocalDate issueDate;
	private LocalDate expectedReturnDate;
	private LocalDate returnDate;
	private double lateFee;
}
