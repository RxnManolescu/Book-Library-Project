package com.employee.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.service.EmployeeService;

@RestController
public class EmployeeResources {
	
		@Autowired
		private EmployeeService employeeService;
		
		//login check
		@GetMapping(path = "/checks/{empId}/{password}")
		public Employee checkLogin(@PathVariable("empId") int id, @PathVariable("password") String password) {
			Employee returnEmp = employeeService.checkLoginIdAndPassword(id, password);
			return returnEmp;
		}
		
		//update book quantity
		@GetMapping(path = "/updates/{empId}/{quantity}")
		public boolean updateBookQuantity(@PathVariable("empId") int id, @PathVariable("quantity") int quantity) {
			return employeeService.changeBookQuantity(id, quantity);
		}
}
