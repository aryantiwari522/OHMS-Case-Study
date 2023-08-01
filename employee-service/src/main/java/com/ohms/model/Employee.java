package com.ohms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("Employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	
	@Id
	private int employeeId;
	private String employeeName;
	private int employeeAge;
	private double employeeSalary;
	private String department;
	private String address;
	private long employeePhoneNumber;

}
