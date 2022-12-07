package com.employee.service;

import com.employee.entity.Employee;

public interface EmployeeService {
	
	public Employee checkLoginIdAndPassword(int id, String password);
	
	public boolean changeBookQuantity(int id, int changeQuantity);
}
