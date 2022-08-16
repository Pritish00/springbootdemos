package com.example.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.training.domain.Employee;
import com.example.training.domain.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService service;
	
	@RequestMapping(path="/employees/all", method=RequestMethod.GET)
	public String getAll(Model model) {
		//Pushing generated data as model data.
		model.addAttribute("employees", service.getAllEmployees());
		//Returning the name of View (i.e. Page) which processes the request
		return "employeelist";	
	}

	
	@RequestMapping(path="/employees/{paramId}", method=RequestMethod.GET)
	public String getEmployee(@PathVariable("paramId") Integer pId, Model model) {
		//Find the employee with paramId
		Employee emp = service.getEmployeeById(pId);
		System.out.println("EmployeeController.getEmployee: Param Id is " + pId);
		System.out.println("EmployeeController.getEmployee: Employee Name is " + emp.getName());
		//Save found employee as model data
		model.addAttribute("employee", emp);
		//Requesting for employeedetails.jsp view for rendering.
		return "employeedetails";
	}
	
	@RequestMapping(path="/employees/delete", method=RequestMethod.GET)
	public String deleteEmployee(@RequestParam("paramId") Integer pId, Model model) {
		service.removeEmployee(pId);
		model.addAttribute("employees", service.getAllEmployees());
		return "redirect:/mvc/employees/all";
	}

	
	@RequestMapping(path="/employees/create", method=RequestMethod.GET)
	public String createEmployee(Model model) {
		//Creating new blank employee record
		Employee newEmployee = new Employee();
		//Save new blan employee record as Model data
		model.addAttribute("newEmployee", newEmployee);
		//Display employeecreate form page
		return "employeecreate";
	}
	
	@RequestMapping(path="/employees/create", method=RequestMethod.POST)
	public String createEmployee(@ModelAttribute("newEmployee") Employee newEmployee) {
		//Call the business logic to create employee
		service.hireEmployee(newEmployee);
		//Redirect the request to the Employee List page.
		return "redirect:/mvc/employees/all";
	}




	
}
