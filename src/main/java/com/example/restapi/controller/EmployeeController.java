package com.example.restapi.controller;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.restapi.dao.EmployeeDAO;
import com.example.restapi.model.Employee;

@RestController
@RequestMapping(path = "/sampleapp/api/v1/employees")
public class EmployeeController {
	@Autowired
	private EmployeeDAO employeeDao;

	@GetMapping(path = "/", produces = "application/json")

	public ResponseEntity<Collection<Employee>> getEmployees() {
		return new ResponseEntity<>(employeeDao.getAllEmployees(), HttpStatus.OK);
	}

	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> addEmployee(
			@RequestBody Employee employee) throws Exception {
		// Generate resource id
		Integer id = employeeDao.getAllEmployees().size() + 1;
		employee.setId(id);

		// add resource
		employeeDao.addEmployee(employee);
	
		return new ResponseEntity<>(("Created employee with Id ="+employee.getId()),HttpStatus.CREATED);
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<Employee> getSpecificEmployees(@PathVariable("id") int id) {
		return new ResponseEntity<>(employeeDao.getEmployeeByID(id), HttpStatus.OK);
	}
}
