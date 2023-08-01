package com.ohms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohms.model.Department;
import com.ohms.model.Employee;
import com.ohms.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Autowired
	private DepartmentService departmentService;
	
	// Add Employee
	public void addEmployee(Employee employee) {
		int getId = sequenceService.getNextSequence("employee");
		employee.setEmployeeId(getId);
		
		Department department = departmentService.getDepartmentByDepartmentName(employee.getDepartment());
		
		if(department == null) {
			Department department2 = new Department( employee.getEmployeeId(), employee.getDepartment(), 1, employee.getEmployeeSalary());
			departmentService.addDepartment(department2);
		} else {
			int numberOfEmployee = department.getNunmberOfEmployee();
			double avgSalary = department.getAverageSalary();
			
			double totalSalary = (avgSalary * (double) numberOfEmployee) + employee.getEmployeeSalary();
			
			System.out.println(totalSalary);
			numberOfEmployee++;
			
			avgSalary = totalSalary/(double) numberOfEmployee;
			System.out.println(avgSalary);
			department.setAverageSalary(avgSalary);
			department.setNunmberOfEmployee(numberOfEmployee);
			departmentService.updateDepartment(department);
		}
		
		employeeRepository.save(employee);
	}
	
	// Return all the Employee Details in the form of list
	public List<Employee> getAllEmployee(){
		return employeeRepository.findAll();
	}
	
	// Return Employee Detail for requested id
	public Optional<Employee> getEmployeeById(int employeeId) {
		return employeeRepository.findById(employeeId);
	}
	
	// Update Employee Detail for requested Id
	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	
	// Delete Employee Detail for requested Id
	public void deleteEmployee(int employeeId) {
		employeeRepository.deleteById(employeeId);
	}

}
