package com.employee.service;

import java.util.List;

import com.employee.entity.Employee;

public interface EmployeeService {
	
	Employee checkLoginIdAndPassword(int id, String password);
	
	boolean changeBookQuantity(int id, int changeQuantity);

	Employee searchById(int id);

	List<Employee> getAllEmployees();
}
