package com.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.persistence.EmployeeDao;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Employee checkLoginIdAndPassword(int id, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeBookQuantity(int id, int changeQuantity) {
		// TODO Auto-generated method stub
		return false;
	}

}
