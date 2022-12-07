package com.employee.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.employee.service.EmployeeService;

@RestController
public class EmployeeResources {

	@Autowired
	private EmployeeService employeeService;
}
