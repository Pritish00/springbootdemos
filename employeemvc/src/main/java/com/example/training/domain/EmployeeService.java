package com.example.training.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;
	
	public Employee getJamesObject() {
		return repository.findEmployeeById(100);
	}
	
	public List<Employee> getAllEmployees(){
		return repository.getAllEmployees();
	}
	
	public void hireEmployee(Employee emp) {
		repository.createEmployee(emp);
	}
	public Employee getEmployeeById(Integer id) {
		System.out.println("EmployeeService.getEmployeeById: Parameter id is " + id);
		return repository.findEmployeeById(id);
	}

	public void removeEmployee(Integer empId) {
		repository.deleteEmployee(empId);
	}


}
