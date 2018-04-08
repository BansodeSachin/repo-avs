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
public class EmployeeController {
	
	// Need to inject our employee service
	@Autowired
	private EmployeeService employeeService;
	
	// Need to inject our employee service
	@Autowired
	private EmpSalaryService empSalaryService;
	
	
	
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		
		// Get employees from the service
		List<Employee> theEmployees = employeeService.getEmployees();
		
		// Add the employee to the model
		theModel.addAttribute("employees", theEmployees);
		
		
		return "list-employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// Create model attribute to bind form data
		Employee theEmployee = new Employee();
		theModel.addAttribute("employee",theEmployee);
		
		return "employee-form";
	}
	
	@GetMapping("/showFormForRegister")
	public String showFormForRegister(Model theModel) {
		
		// Create model attribute to bind form data
		Employee theEmployee = new Employee();
		theModel.addAttribute("employee",theEmployee);
		
		return "registeruser";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		
		Date d = new Date();
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		SimpleDateFormat yearFormat = new SimpleDateFormat("YY");
	    String startWith = "E" + yearFormat.format(d) + monthFormat.format(d);
	    int eid = 1;
	    String seq = "";
	    
	    List<Integer> existingIdNumber = new ArrayList<Integer>();
	    
	    List<Employee> employees = employeeService.getEmployees();
	    for(Employee employee: employees) {
	    	System.out.println("employee.getId() = " + employee.getId());
	    	System.out.println("employee.getId() = " + employee.getId().substring(employee.getId().length()-3, employee.getId().length()-1));
	    	System.out.println("employee.getFirstName() = " + employee.getFirstName());
	    	System.out.println("------------------------------------------------");
	    	existingIdNumber.add(new Integer(employee.getId().substring(employee.getId().length()-3, employee.getId().length()-1)));
	    }
	    
	    System.out.println("existingIdNumber = " + existingIdNumber);
	    
	    for(Employee employee: employees) {
	    	String tempEid = employee.getId();
	    	//System.out.println("tempEid = " + tempEid);
	    	if(tempEid.startsWith(startWith)) {
	    		seq = (eid<10) ? new String("00"+eid) : (eid<100 ? new String("0"+eid) : new String("" + eid) );
	    		System.out.println("seq = " + seq);
	    		while(tempEid.equals(startWith + seq)) {
	    			eid++;
	    			seq = (eid<10) ? new String("00"+eid) : (eid<100 ? new String("0"+eid) : new String("" + eid) );
	    		}
	    	}
	    }
	    
	    if(employees.isEmpty()) {
	    	seq = (eid<10) ? new String("00"+eid) : (eid<100 ? new String("0"+eid) : new String("" + eid) );
	    }
	    
	    String finalEmpId = startWith + seq;
	    System.out.println("Final Employee ID = " + finalEmpId);
		theEmployee.setId(finalEmpId);
		// Save the employee using our service
		employeeService.saveEmployee(theEmployee);
		
		return "redirect:/avs/list";
	}
	
	
	@PostMapping("/registerNewUser")
	public String registerNewUser(@ModelAttribute("employee") Employee theEmployee, Model theModel) {
		
		Date d = new Date();
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		SimpleDateFormat yearFormat = new SimpleDateFormat("YY");
	    String startWith = "E" + yearFormat.format(d) + monthFormat.format(d);
	    int eid = 1;
	    String seq = "";
	    
	    List<Employee> employees = employeeService.getEmployees();
	    
	    for(Employee employee: employees) {
	    	String tempEid = employee.getId();
	    	System.out.println("tempEid = " + tempEid);
	    	if(tempEid.startsWith(startWith)) {
	    		seq = (eid<10) ? new String("00"+eid) : (eid<100 ? new String("0"+eid) : new String("" + eid) );
	    		System.out.println("seq = " + seq);
	    		while(tempEid.equals(startWith + seq)) {
	    			eid++;
	    			seq = (eid<10) ? new String("00"+eid) : (eid<100 ? new String("0"+eid) : new String("" + eid) );
	    		}
	    	}
	    }
	    
	    if(employees.isEmpty()) {
	    	seq = (eid<10) ? new String("00"+eid) : (eid<100 ? new String("0"+eid) : new String("" + eid) );
	    }
	    
	    String finalEmpId = startWith + seq;
	    
		theEmployee.setId(finalEmpId);
		// Save the employee using our service
		employeeService.saveEmployee(theEmployee);
		employeeService.sendRegistrationMail(theEmployee);
		
		// Create model attribute to bind form data
		Employee newEmployee = new Employee();
		theModel.addAttribute("employee",newEmployee);
		theModel.addAttribute("newEmpId",finalEmpId);
		
		return "registeruser";
	}
	
	
	@PostMapping("/validateUserLogin")
	public String validateUserLogin(@ModelAttribute("employee") Employee theEmployee, Model theModel, 
								HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("theEmployee.getId() = " + theEmployee.getId());
		System.out.println("theEmployee.getPassword() = " + theEmployee.getPassword());
		System.out.println("EMPCONtrOLLER request.getContextPath() = " + request.getContextPath());
		
		
		String id = theEmployee.getId();
		String password = theEmployee.getPassword();
		
		String reCaptchaResponse = request.getParameter("g-recaptcha-response");
		
		
		if(employeeService.isValidUserLogin(id, password, reCaptchaResponse, request, response)) {
			/*
		     * create new session if session is not new
		     */
			System.out.println("Creation time before = " + request.getSession().getCreationTime());
			System.out.println("Is new session before = " + request.getSession().isNew());
		    if (!session.isNew()) {
		        session.invalidate();
		    }
		    request.getSession().setAttribute("session_empid", theEmployee.getId());
		    Employee emp = employeeService.getEmployee(theEmployee.getId());
		    request.getSession().setAttribute("session_empName", emp.getFirstName());
		    System.out.println("Creation time after = " + request.getSession().getCreationTime());
		    System.out.println("Is new session after = " + request.getSession().isNew());
		    
			return "avsindex";
		}
		
		theModel.addAttribute("validationMsg", "Invalid Username and Password Combination...");
			
		return "login";
	}
	
	
	@GetMapping("/updateEmployeeWithId")
	public String showFormForEmpUpdate(@RequestParam("employeeId") String theId, 
									Model theModel) {
		
		// Get the employee from the service
		Employee theEmployee = employeeService.getEmployee(theId);
		
		// Set employee as a model attribute to pre-populate the form
		theModel.addAttribute("employee", theEmployee);
		
		
		// Get the employee from the service
		EmpSalary theEmpSalary = new EmpSalary();
		theEmpSalary.setId(theId);
		
		// Set employee as a model attribute to pre-populate the form
		theModel.addAttribute("empSalary", theEmpSalary);
		
		
		// Get employees from the service
		List<EmpSalary> theEmpSalaryComponents = empSalaryService.getEmpSalaryComponents(theId);
				
		// Add the employee to the model
		theModel.addAttribute("theEmpSalaryComponents", theEmpSalaryComponents);
		
		
		// send over to our form
		return "empInfoUpdate";
	}
	
	@PostMapping("/updateUserInfo")
	public String updateUserInfo(@ModelAttribute("employee") Employee theEmployee, Model theModel) {
		// Save the employee using our service
		employeeService.saveEmployee(theEmployee);
		
		return "redirect:users";
	}
	
	
	@GetMapping("/deleteEmployeeWithId")
	public String deleteEmployeeWithId(@RequestParam("employeeId") String theId) {
		
		// Delete the Employee from the service
		employeeService.deleteEmployee(theId);
		
		// send over to our form
		return "redirect:users";
	}
	
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") String theId, 
									Model theModel) {
		
		// Get the employee from the service
		Employee theEmployee = employeeService.getEmployee(theId);
		
		// Set employee as a model attribute to pre-populate the form
		theModel.addAttribute("employee", theEmployee);
		
		// send over to our form
		return "employee-form";
	}
	
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") String theId) {
		
		// Delete the Employee from the service
		employeeService.deleteEmployee(theId);
		
		// send over to our form
		return "redirect:list";
	}
	
}
