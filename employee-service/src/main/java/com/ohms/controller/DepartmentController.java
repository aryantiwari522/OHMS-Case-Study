package com.ohms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ohms.model.Department;
import com.ohms.service.DepartmentService;

@RestController
@RequestMapping("/department")
@CrossOrigin("*")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;

	/**
	 * this is used to add the department
	 */
	@PostMapping("/add")
	//@PreAuthorize("hasRole('OWNER')")
	public void addDepartment(@RequestBody Department department) {
		departmentService.addDepartment(department);
	}
	
	@GetMapping("/get")
	//@PreAuthorize("hasRole('OWNER')")
	public List<Department> getAllDepartments(){
		return departmentService.getAllDepartments();
	}
	
	@GetMapping("/get/{id}")
	//@PreAuthorize("hasRole('OWNER')")
	public Optional<Department> getDepartmentById(@PathVariable int id) {
		return departmentService.getDepartmentById(id);
	}
	
	@PutMapping("/update/{id}")
	//@PreAuthorize("hasRole('OWNER')")
	public String updateDepartment(@PathVariable int id, @RequestBody Department department) {
		departmentService.updateDepartment(department);
		return "Update Successfully";
	}
	
	@DeleteMapping("/delete/{id}")
	//@PreAuthorize("hasRole('OWNER')")
	public String deleteDepartment(@PathVariable int id) {
		departmentService.deleteDepartmet(id);
		return "Delete Successfully";
	}

}
