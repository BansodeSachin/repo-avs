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
public class EmployeeDAOImpl implements EmployeeDAO {

	// Need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Employee> getEmployees() {
		
		// Get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Create a query
		Query<Employee> theQuery = 
				currentSession.createQuery("from Employee order by id", Employee.class);
		
		// Execute query and get result list
		LinkedList<Employee> employees = new LinkedList<Employee>(theQuery.getResultList());
		
		// Return the results
		return employees;
	}

	@Override
	public void saveEmployee(Employee theEmployee) {
		// Get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Save/Update the employee...
		currentSession.saveOrUpdate(theEmployee);
		
		
	}

	@Override
	public Employee getEmployee(String theId) {
		// Get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Now retrieve/read from database using the primary key
		Employee theEmployee = currentSession.get(Employee.class, theId);
		
		return theEmployee;
	}

	@Override
	public void deleteEmployee(String theId) {
		// Get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();
	}

	@Override
	public boolean isValidUserLogin(String id, String password, String reCaptchaResponse, HttpServletRequest request, HttpServletResponse response) {
		// Get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Now retrieve/read from database using the primary key
		Employee theEmployee = currentSession.get(Employee.class, id);
		
		System.out.println("reCaptchaResponse = " + reCaptchaResponse);
		
		try {
			this.generateJasperReport(request, response);
		} catch(Exception e) {
			System.out.println("Exception occurrec while generating Jasper Report");
		}
		
		
		
		if(theEmployee!=null) {
			if(password.equals(theEmployee.getPassword()) ) {
				String recaptchSecretKey = "6Lcfu0wUAAAAAOIc_n5onk37TRZgdzBIUnt7CDqU";
				//if(isCaptchaValid(recaptchSecretKey, reCaptchaResponse))
					return true;
			}
		}
		
		return false;
	}
	
	public static boolean isCaptchaValid(String secretKey, String response) {
	    try {
	        String url = "https://www.google.com/recaptcha/api/siteverify?"
	                + "secret=" + secretKey
	                + "&response=" + response;
	        InputStream res = new URL(url).openStream();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(res, Charset.forName("UTF-8")));

	        StringBuilder sb = new StringBuilder();
	        int cp;
	        while ((cp = rd.read()) != -1) {
	            sb.append((char) cp);
	        }
	        String jsonText = sb.toString();
	        res.close();
	        System.out.println("jsonText = " + jsonText);
	        JSONObject json = new JSONObject(jsonText);
	        return json.getBoolean("success");
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	public static boolean addElement(Element root, String name, String value) {
		if(root!=null && StringUtils.isNotBlank(name) && StringUtils.isNotBlank(value) && !StringUtils.containsOnly(value, ", ")) {
			root.addElement(name).addText(value.trim());
			return true;
		}
		
		return false;
	}
	
	public void generateJasperReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<StudentVo> list = new ArrayList<StudentVo>();
        list.add(new StudentVo("1","ashish"));
        list.add(new StudentVo("2","deepak"));
        list.add(new StudentVo("3","rabi"));
        list.add(new StudentVo("4","anil"));
 
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
 
        FileOutputStream fos = null;
        try {
        	/*
        	// Below code is used to create XML Document object and save the XML file
        	Element root=new Element("CONFIGURATION");
        	Document doc=new Document();

        	Element child1=new Element("BROWSER");
        	child1.addContent("chrome");
        	Element child2=new Element("BASE");
        	child1.addContent("http:fut");
        	Element child3=new Element("EMPLOYEE");
        	child3.addContent(new Element("EMP_NAME").addContent("Anhorn, Irene"));

        	root.addContent(child1);
        	root.addContent(child2);
        	root.addContent(child3);

        	doc.setRootElement(root);

        	XMLOutputter outter=new XMLOutputter();
        	outter.setFormat(Format.getPrettyFormat());
        	outter.output(doc, new FileWriter(new File("C:/Java New Lapee/iReport/Temp/myxml.xml")));*/
        	
        	
        	Document doc = DocumentHelper.createDocument();
        	Element row = doc.addElement("Document").addElement("Disclosure").addElement("row");
        	Element alwaysNode = row.addElement("Always");
        	System.out.println("alwaysNode = " + alwaysNode.asXML());
        	boolean test1 = addElement(alwaysNode, "test1", "test1Value");
        	System.out.println("test1 = " + test1);
        	boolean test2 = addElement(alwaysNode, "test2", "test2Value");
        	System.out.println("test2 = " + test2);
        	boolean test3 = addElement(alwaysNode, "test3", "test3Value");
        	System.out.println("test3 = " + test3);
        	System.out.println("doc.asXML() = " + doc.asXML());
        	
        	
        	//JRXmlDataSource jrxmlDS = new JRXmlDataSource((org.w3c.dom.Document) doc);
            byte[] bytes=this.generateJasperReportPDF("offerReport", outputStream, list, doc, request);
            if(bytes.length>1){
                File someFile = new File("C:/Java New Lapee/iReport/Temp/offerReport.pdf");
                fos = new FileOutputStream(someFile);
                fos.write(bytes);
                fos.flush();
                fos.close();
                System.out.println("<<<<<<<<<<<<Report Created>>>>>>>>");
            }
            //response.getOutputStream().write(bytes);
            
            
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
 
    public byte[]  generateJasperReportPDF(String jasperReportName, ByteArrayOutputStream outputStream, 
    													List reportList, Document doc, HttpServletRequest request) {
        JRPdfExporter exporter = new JRPdfExporter();
        try {
        	System.out.println("EMPDAO request.getContextPath() = " + request.getContextPath());
            String contextPath = request.getContextPath();
            System.out.println("contextPath = " + contextPath);
            System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
            System.out.println("request.getRemotePort() = " + request.getRemotePort());
            System.out.println("request.getRemoteHost() = " + request.getRemoteHost());
            System.out.println("request.getRemoteUser() = " + request.getRemoteUser());
            
            String fullPath = request.getSession().getServletContext().getRealPath("/resources/reports/" + jasperReportName + ".jrxml");
            System.out.println("fullPath = " + fullPath);
            
            //String reportLocation = contextPath + "/resources/reports/"+jasperReportName+".jrxml";
            JasperReport jasperReport = JasperCompileManager.compileReport(fullPath);
            //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, new JRBeanCollectionDataSource(reportList));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, new JRXmlDataSource(new DOMWriter().write(doc)));
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);   
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
            exporter.exportReport();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in generate Report..."+e);
        } finally {
        }
        return outputStream.toByteArray();
    }

}
