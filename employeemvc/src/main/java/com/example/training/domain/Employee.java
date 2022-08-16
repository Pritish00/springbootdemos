package com.example.training.domain;

public class Employee {

	private Integer id;
	private String name;
	private Double salary;
	private String job;
	
	public Employee(Integer id, String name, Double salary,String job) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.job=job;
	}
	
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Employee() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}	
}
