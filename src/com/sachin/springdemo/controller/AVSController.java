package com.sachin.springdemo.controller;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sachin.springdemo.entity.Employee;
import com.sachin.springdemo.entity.LeadInfo;
import com.sachin.springdemo.service.EmployeeService;
import com.sachin.springdemo.service.LeadService;

@Controller
@RequestMapping("/avs")
public class AVSController {
	
	// Need to inject our lead service
	@Autowired
	private LeadService leadService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/index")
	public String getIndexPage(Model theModel) {
		return "avsindex";
	}
	
	@GetMapping("/login")
	public String getLoginPage(Model theModel, HttpSession session) {
		// Create model attribute to bind form data
		/*
	     * This will end the current session, and open login page.
	     */
	    session.invalidate();
		Employee theEmployee = new Employee();
		theModel.addAttribute("employee",theEmployee);
		return "login";
	}
	
	@GetMapping("/edit")
	public String getEditPage(Model theModel) {
		return "edit";
	}
	
	@GetMapping("/pages")
	public String getPages(Model theModel) {
		return "pages";
	}
	
	@GetMapping("/posts")
	public String getPosts(Model theModel) {
		return "posts";
	}
	
	@GetMapping("/users")
	public String getUsersPage(Model theModel) {
		// Get employees from the service
		List<Employee> employees = employeeService.getEmployees();
		
		// Add the employee to the model
		theModel.addAttribute("employees", employees);
		
		return "users";
	}
	
	@GetMapping("/adminlogin")
	public String getAdminLoginPage(Model theModel) {
		return "adminlogin";
	}
	
	@GetMapping("/registeruser")
	public String getRegisterUserPage(Model theModel) {
		return "registeruser";
	}
	
	@GetMapping("/leadInfo")
	public String getLeadInformationPage(Model theModel) {
		return "leadInfo";
	}
	
	@GetMapping("/addLead")
	public String getNewLeadPage(Model theModel) {
		return "addlead";
	}
	
	@GetMapping("/allLeads")
	public String getAllLeads(Model theModel, HttpServletRequest request) {
		// Get employees from the service
		List<LeadInfo> theLeads = leadService.getLeads();
		
		// Add the employee to the model
		theModel.addAttribute("theLeads", theLeads);
		
		System.out.println("Creation time after = " + request.getSession().getCreationTime());
	    System.out.println("Is new session after = " + request.getSession().isNew());
	    System.out.println("EMP ID From Session = " + request.getSession().getAttribute("session_empid"));
		
		return "allLeads";
	}
	
	@GetMapping("/addLeadForm")
	public String addLeadForm(Model theModel) {
		
		// Create model attribute to bind form data
		LeadInfo theLead = new LeadInfo();
		theModel.addAttribute("theLead",theLead);
		
		return "addlead";
	}
	
	@PostMapping("/saveLead")
	public String saveNewLead(@Valid @ModelAttribute("theLead") LeadInfo theLead,
			BindingResult theBindingResult) {
		
		System.out.println("theLead.getAgentName() = " + theLead.getAgentName());
		System.out.println("theLead.getMedId() = " + theLead.getMedId());
		System.out.println("theLead.getPpoId() = " + theLead.getPpoId());
		System.out.println("theBindingResult = " + theBindingResult);
		System.out.println("isBrcBack = " + theLead.isBrcBack());
		System.out.println("isBrcIsCGX = " + theLead.isBrcIsCGX());
		System.out.println("isBrcIsPainCream = " + theLead.isBrcIsPainCream());
		System.out.println("isBrcRightAnkle = " + theLead.isBrcRightAnkle());
		System.out.println("isBrcRightElbow = " + theLead.isBrcRightElbow());
		System.out.println("isBrcRightKnee = " + theLead.isBrcRightKnee());
		System.out.println("isBrcRightShoulder = " + theLead.isBrcRightShoulder());
		System.out.println("isBrcRightWrist = " + theLead.isBrcRightWrist());
		System.out.println("isBrcLeftAnkle = " + theLead.isBrcLeftAnkle());
		System.out.println("isBrcLeftElbow = " + theLead.isBrcLeftElbow());
		System.out.println("isBrcLeftKnee = " + theLead.isBrcLeftKnee());
		System.out.println("isBrcLeftShoulder = " + theLead.isBrcLeftShoulder());
		System.out.println("isBrcLeftWrist = " + theLead.isBrcLeftWrist());
		
		if(theBindingResult.hasErrors()) {
			return "addlead";
		} else {
			Date d = new Date();
			SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");
		    String finalLeadId = yearFormat.format(d) + "101";
		    
		    List<LeadInfo> leads = leadService.getLeads();
		    
		    if(!leads.isEmpty()) {
		    	LeadInfo lastLead = leads.get(leads.size()-1);
	    	    
			    finalLeadId = new Integer(new Integer(lastLead.getLeadId()) + 1).toString();
		    }
		     
		    theLead.setLeadStatus("Open");
			theLead.setLeadId(finalLeadId);
			// Save the lead using our service
			leadService.saveLead(theLead);
			return "redirect:/avs/allLeads";
		}
		
	}
	
	/*@PostMapping("/updateLead")
	public String updateLead(@ModelAttribute("theLead") LeadInfo theLead) {
		
		// Update the lead using our service
		leadService.saveLead(theLead);
		
		return "allLeads";
	}*/
	
}

