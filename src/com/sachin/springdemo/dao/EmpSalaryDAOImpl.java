package com.sachin.springdemo.dao;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.query.Query;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DOMWriter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sachin.springdemo.entity.EmpSalary;
import com.sachin.springdemo.entity.Employee;
import com.sachin.springdemo.entity.StudentVo;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@Repository
public class EmpSalaryDAOImpl implements EmpSalaryDAO {

	// Need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<EmpSalary> getEmpSalaryComponents(String theId) {
		
		// Get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Create a query
		Query<EmpSalary> theQuery = 
				currentSession.createQuery("from EmpSalary where id=:id order by id", EmpSalary.class);
		theQuery.setParameter("id", theId);
		
		// Execute query and get result list
		LinkedList<EmpSalary> empSalaryComponents = new LinkedList<EmpSalary>(theQuery.getResultList());
		
		// Return the results
		return empSalaryComponents;
	}

	@Override
	public void saveEmpSalaryComponent(EmpSalary theEmpSalary) {
		
		System.out.println("Line 76 Salary Component Addition Started...");
		System.out.println("theEmpSalary.toString() = " + theEmpSalary.toString());
		
		
		// Get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Save/Update the employee...
		currentSession.saveOrUpdate(theEmpSalary);
		
		
	}

	@Override
	public EmpSalary getEmpSalaryComponent(String theId, String componentId) {
		// Get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		System.out.println("Line 94 Fetching EmpSalary object");
		// Now retrieve/read from database using the primary key
		EmpSalary theEmpSalary = currentSession.get(EmpSalary.class, componentId);
		//System.out.println("theEmpSalary = " + theEmpSalary);
		
		
		/*System.out.println("theId = " + theId);
		System.out.println("componentId = " + componentId);
		// Create a query
		Query<EmpSalary> theQuery = 
				currentSession.createQuery("from EmpSalary where id=:employeeId and componentId=:salcomponentId", EmpSalary.class);
		theQuery.setParameter("employeeId", theId);
		theQuery.setParameter("salcomponentId", componentId);
		
		// Execute query and get result list
		LinkedList<EmpSalary> empSalaryComponents = new LinkedList<EmpSalary>(theQuery.getResultList());
		System.out.println("empSalaryComponents = " + empSalaryComponents);
		EmpSalary theEmpSalary = empSalaryComponents.get(0);
		System.out.println("theEmpSalary.toString() = " + theEmpSalary.toString());*/
		
		return theEmpSalary;
	}

	@Override
	public void deleteEmpSalaryComponent(String theId, String componentId) {
		// Get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Delete object with primary key
		Query theQuery = currentSession.createQuery("delete from EmpSalary where id=:employeeId and componentId=:salcomponentId");
		theQuery.setParameter("employeeId", theId);
		theQuery.setParameter("salcomponentId", componentId);
		theQuery.executeUpdate();
	}

}
