package com.library.entity;

import java.time.Duration;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Library {

	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String transactionId;
	private int employeeId;
	private String employeeName;
	private int bookId;
	private String bookType;
	private LocalDate issueDate;
	//NAT HERE--------------------------------
	private LocalDate expectedReturnDate;
	private LocalDate returnDate;
	private int lateFee;
	private int numberOfCopies;
	
//	Can be used directly in HTML: <div tx:text="${library.getLateFee()}">
//	public double getLateFee() {
//		Duration diff = Duration.between(issueDate.atStartOfDay(), returnDate.atStartOfDay());
//		long diffDays = diff.toDays();
//		long extraDays = diffDays - 7;
//		
//		if (extraDays <= 0) {
//			return 0;
//		}
//
//		if (bookType.equals("Data Analytics")) {
//			return extraDays * 0.05;
//		} else if (bookType.equals("Technology")) {
//			return extraDays * 0.06;
//		} else if (bookType.equals("Management")) {
//			return extraDays * 0.07;
//		}
//		return 0;
//	}
//	
}
