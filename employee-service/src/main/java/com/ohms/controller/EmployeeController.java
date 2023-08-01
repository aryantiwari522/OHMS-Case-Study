package com.ohms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ohms.model.Employee;
import com.ohms.model.Response;
import com.ohms.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/employee")
@CrossOrigin("http://localhost:4200/")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	// For logging errors
	Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	// To add new Employee into Inventory Database
	
	@PostMapping("/add")
	@Operation(summary = "To add new Employee into Inventory Database")
	//@PreAuthorize("hasRole('OWNER')")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
		try {
			employeeService.addEmployee(employee);
			return ResponseEntity.ok(new Response("Employee Added"));
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseEntity.badRequest().body(new Response("Error while adding product"));
		}
	}
	
	// Return all the Employee Details in the form of list
	
	@GetMapping("/get")
	@Operation(summary = "Return all the Employee Details in the form of list")
	//@PreAuthorize("hasRole('OWNER')")
	public List<Employee> getAllEmployee(){
		try {
			return employeeService.getAllEmployee();
		} catch (Exception e) {
			logger.error(e.toString());
			return new ArrayList<Employee>(null);
		}
	}
	
	// Return Employee Detail for requested id
	
	@GetMapping("/get/{employeeId}")
	@Operation(summary = "Return Employee Detail for requested id")
	//@PreAuthorize("hasRole('OWNER')")
	public Optional<Employee> getEmployeeById(@PathVariable int employeeId) {
		try {
			return employeeService.getEmployeeById(employeeId);
		} catch (Exception e) {
			logger.error(e.toString());
			return null;
		}
	}
	
	// Update Employee Detail for requested Id
	
	@PutMapping("/update")
	@Operation(summary = "Update Employee Detail for requested Id")
	//@PreAuthorize("hasRole('OWNER')")
	public ResponseEntity<?> updateRoom(@RequestBody Employee employee) {
		try {
			employeeService.updateEmployee(employee);
			return ResponseEntity.ok(new Response("Updated the employee"));
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseEntity.badRequest().body(new Response("Error while updating employee"));
		}
	}
	
	// Delete Employee Detail for requested Id
	
	@DeleteMapping("/delete/{employeeId}")
	@Operation(summary = "Delete Employee Detail for requested Id")
	//@PreAuthorize("hasRole('OWNER')")
	public ResponseEntity<?> deleteEmployee(@PathVariable int employeeId) {
		try {
			employeeService.deleteEmployee(employeeId);
			return ResponseEntity.ok(new Response("Employee Deleted"));
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseEntity.badRequest().body(new Response("Error while Deleting Product"));
		}
	}

}
