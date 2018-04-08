package com.sachin.springdemo.service;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sachin.springdemo.dao.LeadDAO;
import com.sachin.springdemo.entity.LeadInfo;
import com.sachin.springdemo.entity.Student;

@Service
public class LeadServiceImpl implements LeadService {

	// Need to inject employee DAO
	@Autowired
	private LeadDAO leadDAO;
	
	@Override
	@Transactional
	public List<LeadInfo> getLeads() {
		return leadDAO.getLeads();
	}

	@Override
	@Transactional
	public void saveLead(LeadInfo theLeadInfo) {
		leadDAO.saveLeadInfo(theLeadInfo);
		this.sendLeadAdditionMail(theLeadInfo);
	}

	@Override
	@Transactional
	public LeadInfo getLead(String theId) {
		return leadDAO.getLeadInfo(theId);
	}

	@Override
	@Transactional
	public void deleteLead(String theId) {
		leadDAO.deleteLeadInfo(theId);

	}
	
	public void sendLeadAdditionMail(LeadInfo theLeadInfo) {
		System.out.println("Lead Additionl mail sending process...");
		
		String to= "bansodesachin0@gmail.com";  
	    String subject="New Lead Added";
	    
	    StringBuffer body = new StringBuffer();
	    try {
	    	
		    
		    body.append("<!DOCTYPE html>")
		    	.append("<html>")
		    	.append("</html>")
		    
		    .append("<head>")
			.append("<style>")
			.append("table {")
			.append("font-family: arial, sans-serif;")
			.append("border-collapse: collapse;")
			.append("width: 100%;")
			.append("}")

			.append("td, th {")
			.append("border: 1px solid #dddddd;")
			.append("text-align: left;")
			.append("padding: 8px;")
			.append("}")
		
			.append("tr:nth-child(even) {")
				.append("background-color: #dddddd;")
			.append("}")
			.append("</style>")
			.append("</head>")
			.append("<body>")
		
			.append("<h2>Lead Information</h2>")
		
			.append("<table>")
			  .append("<tr>")
				.append("<th>Field1</th>")
				.append("<th>Field2</th>")
				.append("<th>Field3</th>")
				.append("<th>Field4</th>")
			  .append("</tr>");
			    body.append("<tr>")
					.append("<td>" + theLeadInfo.getAgentName() + "</td>")
					.append("<td>" + theLeadInfo.getCenter_code() + "</td>")
					.append("<td>" + theLeadInfo.getEmpId() + "</td>")
					.append("<td>" + theLeadInfo.getCustBestTimeToCall() + "</td>")
				  .append("</tr>");
			    	    
			body.append("</table>")
		
			.append("</body>")
			.append("</html>");
	    } catch(Exception e) {
	    	e.printStackTrace();
	    	System.out.println("Exception occurred while connecting to database.");
	    	//out.println("Exception occurred while connecting to database.");
	    }
	    
	    	    
	    String msg=body.toString();
	    Mailer.send(to, subject, msg);  
	    //out.print("message has been sent successfully");  
	    //out.close();  
    	
    	
    	
        System.out.println("<<<<<<<<<<<<PDF Email Sent>>>>>>>>");
	}

}
