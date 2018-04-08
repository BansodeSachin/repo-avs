package com.sachin.springdemo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sachin.springdemo.entity.EmpSalary;
import com.sachin.springdemo.entity.Employee;
import com.sachin.springdemo.service.EmpSalaryService;
import com.sachin.springdemo.service.EmployeeService;

@Controller
@RequestMapping("/avs")
public class EmpSalaryController {
	
	// Need to inject our employee Salary service
	@Autowired
	private EmpSalaryService empSalaryService;
	
	// Need to inject our employee service
	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping("/listSalaryComponents")
	public String listEmpSalaryComponents(Model theModel) {
		
		// Get employees from the service
		List<EmpSalary> theEmpSalaryComponents = empSalaryService.getEmpSalaryComponents("E1803001");
		
		// Add the employee to the model
		theModel.addAttribute("theEmpSalaryComponents", theEmpSalaryComponents);
		
		
		return "list-employees";
	}
	
	@PostMapping("/saveSalaryComponent")
	public String saveEmpSalaryComponent(@ModelAttribute("empSalary") EmpSalary theEmpSalary, Model theModel,
										HttpServletRequest request) {
		
		String empId = theEmpSalary.getId();
		System.out.println("empId from session attribute = " + empId);
		
		theEmpSalary.setComponentId(theEmpSalary.getId() + theEmpSalary.getComponentId());
		// Save the employee Salary Component using our service
		empSalaryService.saveEmpSalaryComponent(theEmpSalary);
			
		
		// Get the employee from the service
		Employee theEmployee = employeeService.getEmployee(empId);
		
		// Set employee as a model attribute to pre-populate the form
		theModel.addAttribute("employee", theEmployee);
		
		// Get the employee from the service
		EmpSalary empSalary = new EmpSalary();
		theEmpSalary.setId(empId);
		
		// Set employee as a model attribute to pre-populate the form
		theModel.addAttribute("empSalary", empSalary);
		
		// Get employees from the service
		List<EmpSalary> theEmpSalaryComponents = empSalaryService.getEmpSalaryComponents(empId);
				
		// Add the employee to the model
		theModel.addAttribute("theEmpSalaryComponents", theEmpSalaryComponents);
		
		theModel.addAttribute("employeeId", empSalary.getId());
		
		System.out.println("-----------------------------------------");
		System.out.println(theEmployee.toString());
		System.out.println(theEmpSalary.toString());
		System.out.println("empSalary.getId = " + theEmpSalary.getId());
		System.out.println("theEmployee.getId = " + theEmployee.getId());
		
		
		
		//return "updateEmployeeWithId";
		return "redirect:/avs/updateEmployeeWithId?employeeId="+theEmployee.getId();
	}
	
	
	@PostMapping("/updateSalaryComponent")
	public String updateEmpSalaryComponent(@ModelAttribute("empSalary") EmpSalary theEmpSalary, Model theModel,
										HttpServletRequest request) {
		
		String empId = theEmpSalary.getId();
		System.out.println("empId from session attribute = " + empId);
		
		// Save the employee Salary Component using our service
		empSalaryService.saveEmpSalaryComponent(theEmpSalary);
			
		
		// Get the employee from the service
		Employee theEmployee = employeeService.getEmployee(empId);
		
		// Set employee as a model attribute to pre-populate the form
		theModel.addAttribute("employee", theEmployee);
		
		// Get the employee from the service
		EmpSalary empSalary = new EmpSalary();
		theEmpSalary.setId(empId);
		
		// Set employee as a model attribute to pre-populate the form
		theModel.addAttribute("empSalary", empSalary);
		
		// Get employees from the service
		List<EmpSalary> theEmpSalaryComponents = empSalaryService.getEmpSalaryComponents(empId);
				
		// Add the employee to the model
		theModel.addAttribute("theEmpSalaryComponents", theEmpSalaryComponents);
		
		theModel.addAttribute("employeeId", empSalary.getId());
		
		System.out.println("-----------------------------------------");
		System.out.println(theEmployee.toString());
		System.out.println(theEmpSalary.toString());
		System.out.println("empSalary.getId = " + theEmpSalary.getId());
		System.out.println("theEmployee.getId = " + theEmployee.getId());
		
		
		
		//return "updateEmployeeWithId";
		return "redirect:/avs/updateEmployeeWithId?employeeId="+theEmployee.getId();
	}
	
	
	
	@GetMapping("/updateEmpSalaryComponentWithId")
	public String showFormForEmpUpdate(@RequestParam("employeeId") String theId,
									@RequestParam("empSalaryComponentId") String empSalaryComponentId,
									Model theModel) {
		
		// Get the employee from the service
		EmpSalary theEmpSalary = empSalaryService.getEmpSalaryComponent(theId, empSalaryComponentId);
		
		// Set employee as a model attribute to pre-populate the form
		theModel.addAttribute("empSalary", theEmpSalary);
		
		// send over to our form
		return "empSalaryComponentUpdate";
	}
	
	
	@GetMapping("/deleteEmpSalaryComponentWithId")
	public String deleteEmployeeWithId(@RequestParam("employeeId") String theId, Model theModel,
									@RequestParam("empSalaryComponentId") String empSalaryComponentId) {
		
		// Delete the Employee from the service
		empSalaryService.deleteEmpSalaryComponent(theId, empSalaryComponentId);
		
		// Get the employee from the service
		Employee theEmployee = employeeService.getEmployee(theId);
		
		// Set employee as a model attribute to pre-populate the form
		theModel.addAttribute("employee", theEmployee);
		
		
		// Get the employee from the service
		EmpSalary theEmpSalary = empSalaryService.getEmpSalaryComponent(theId, "001");
		
		// Set employee as a model attribute to pre-populate the form
		theModel.addAttribute("empSalary", theEmpSalary);
		
		
		// Get employees from the service
		List<EmpSalary> theEmpSalaryComponents = empSalaryService.getEmpSalaryComponents(theId);
				
		// Add the employee to the model
		theModel.addAttribute("theEmpSalaryComponents", theEmpSalaryComponents);
		
		
		// send over to our form
		return "redirect:/avs/updateEmployeeWithId?employeeId="+theId;
	}
	
}
