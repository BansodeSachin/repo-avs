package com.sachin.springdemo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sachin.springdemo.entity.LeadInfo;
import com.sachin.springdemo.service.LeadService;

@Controller
@RequestMapping("/avs")
public class LeadController {
	
	// Need to inject our lead service
	@Autowired
	private LeadService leadService;
	
	
	@GetMapping("/leadlist")
	public String listAllLeads(Model theModel, HttpServletRequest request) {
		
		// Get employees from the service
		List<LeadInfo> theLeads = leadService.getLeads();
		
		// Add the employee to the model
		theModel.addAttribute("theLeads", theLeads);
		
		return "list-leads";
	}
	
	@GetMapping("/leadshowFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// Create model attribute to bind form data
		LeadInfo theLead = new LeadInfo();
		theModel.addAttribute("theLead",theLead);
		
		return "lead-form";
	}
	
	@PostMapping("/updateLead")
	public String updateLead(@ModelAttribute("theLead") LeadInfo theLead) {
		
		//String[] bracesList = theLead.getBraces();
		//System.out.println("bracesList.length = " + bracesList.length);
		//System.out.println("bracesList = " + bracesList);
		// Save the lead using our service
		leadService.saveLead(theLead);
		
		return "redirect:/avs/allLeads";
	}
	
	
	@GetMapping("/leadshowFormForUpdate")
	public String showFormForUpdate(@RequestParam("leadId") String theLeadId, 
									Model theModel) {
		
		// Get the LeadInfo from the service
		LeadInfo theLead = leadService.getLead(theLeadId);
		
		// Set LeadInfo as a model attribute to pre-populate the form
		theModel.addAttribute("theLead", theLead);
		
		// send over to our form
		return "lead-update-form";
	}
	
	@GetMapping("/leadUpdateById")
	public String leadUpdateById(@RequestParam("leadId") String theLeadId, 
									Model theModel) {
		
		// Get the LeadInfo from the service
		LeadInfo theLead = leadService.getLead(theLeadId);
		
		// Set LeadInfo as a model attribute to pre-populate the form
		theModel.addAttribute("theLead", theLead);
		
		System.out.println("toString() = " + theLead.toString());
		
		// send over to our form
		return "leadInfoUpdate";
	}
	
	@GetMapping("/leadDeleteById")
	public String leadDeleteById(@RequestParam("leadId") String theLeadId) {
		
		// Delete the LeadInfo from the service
		leadService.deleteLead(theLeadId);
		
		// send over to our form
		return "redirect:/avs/allLeads";
	}
	
	@GetMapping("/leaddelete")
	public String deleteLead(@RequestParam("leadId") String theLeadId) {
		
		// Delete the LeadInfo from the service
		leadService.deleteLead(theLeadId);
		
		// send over to our form
		return "redirect:/avs/leadlist";
	}
	
}
