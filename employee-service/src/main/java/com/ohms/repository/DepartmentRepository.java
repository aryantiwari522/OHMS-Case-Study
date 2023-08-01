package com.ohms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ohms.model.Department;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, Integer> {
	
	Department findByDepartmentName(String departmentName);
	
}
