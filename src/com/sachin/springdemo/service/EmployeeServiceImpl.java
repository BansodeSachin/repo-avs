package com.sachin.springdemo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sachin.springdemo.dao.EmployeeDAO;
import com.sachin.springdemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	// Need to inject employee DAO
	@Autowired
	private EmployeeDAO employeeDAO;
	
	
	@Override
	@Transactional
	public List<Employee> getEmployees() {
		return employeeDAO.getEmployees();
	}


	@Override
	@Transactional
	public void saveEmployee(Employee theEmployee) {
		// Save employee in EmployeeDAO
		employeeDAO.saveEmployee(theEmployee);
		
	}


	@Override
	@Transactional
	public Employee getEmployee(String theId) {
		// TODO Auto-generated method stub
		return employeeDAO.getEmployee(theId);
	}


	@Override
	@Transactional
	public void deleteEmployee(String theId) {
		employeeDAO.deleteEmployee(theId);
		
	}


	@Override
	@Transactional
	public boolean isValidUserLogin(String id, String password, String reCaptchaResponse, HttpServletRequest request, HttpServletResponse response) {
		return employeeDAO.isValidUserLogin(id, password, reCaptchaResponse, request, response);
	}
	
	@Override
	public void sendRegistrationMail(Employee theEmployee) {
		System.out.println("Registration Additionl mail sending process...");
		
		String to= "bansodesachin0@gmail.com";  
	    String subject="New User Registration Completed";
	    
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
				.append("<th>ID</th>")
				.append("<th>Name</th>")
				.append("<th>Email</th>")
				.append("<th>Phone</th>")
			  .append("</tr>");
			    body.append("<tr>")
					.append("<td>" + theEmployee.getId() + "</td>")
					.append("<td>" + theEmployee.getFirstName() + " " + theEmployee.getLastName() + "</td>")
					.append("<td>" + theEmployee.getEmail() + "</td>")
					.append("<td>" + theEmployee.getPhoneNumber() + "</td>")
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
