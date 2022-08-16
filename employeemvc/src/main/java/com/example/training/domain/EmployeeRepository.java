package com.example.training.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class EmployeeRepository {

	private static List<Employee> database;
	static {
		database = new ArrayList();
		database.add(new Employee(100, "James Cooper", 12345.6,"hr"));
		database.add(new Employee(200, "Steven King", 12345.6,"trainer"));
		database.add(new Employee(300, "James Henry", 12370.6,"hr"));
		database.add(new Employee(400, "Steve Hooper", 12695.6,"trainer"));
	}
	
	
	public Employee findEmployeeById(Integer id) {
		System.out.println("EmployeeRepository.findEmployeeById: Parameter id is " + id);
		for(Employee emp:database) {
			if(emp.getId().equals(id))
				return emp;
		}
		return null;
	}

	public List<Employee> getAllEmployees(){
		return database;
	}
	
	public void createEmployee(Employee emp) {
		database.add(emp);		
	}

	public void deleteEmployee(Integer empId) {
		for(int i=0; i<database.size(); i++) {
			if(database.get(i).getId().equals(empId))
				database.remove(i);
		}		
	}

	
}
