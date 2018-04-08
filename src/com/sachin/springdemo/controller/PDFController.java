package com.sachin.springdemo.controller;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DOMWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sachin.springdemo.entity.Student;
import com.sachin.springdemo.service.Mailer;
import com.sachin.springdemo.entity.EmpSalary;
import com.sachin.springdemo.entity.Employee;
import com.sachin.springdemo.entity.LeadInfo;
import com.sachin.springdemo.entity.StudentVo;
import com.sachin.springdemo.service.EmpSalaryService;
import com.sachin.springdemo.service.EmployeeService;
import com.sachin.springdemo.service.LeadService;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@Controller
@RequestMapping("/avs")
public class PDFController {
	
	// Need to inject our lead service
	@Autowired
	private LeadService leadService;
	
	@Autowired
	private EmployeeService employeeService;
	
	// Need to inject our employee Salary service
	@Autowired
	private EmpSalaryService empSalaryService;
	
		
	@GetMapping("/printofferpdf")
	public void printOfferPDF(Model theModel, HttpServletResponse response, HttpServletRequest request) {
		// Create model attribute to bind form data
		try {
			System.out.println("printPDF method started!!!");
			this.generateJasperReport(request, response, false, false, "offerReport");
		} catch(Exception e) {
			System.out.println("Exception occurrec while generating Jasper Report");
		}
	}
	
	@GetMapping("/printofferxml")
	public void printOfferXML(Model theModel, HttpServletResponse response, HttpServletRequest request) {
		try {
			this.generateJasperReport(request, response, true, false, "offerReport");
		} catch(Exception e) {
			System.out.println("Exception occurred while generating XML Data.");
		}
	}
	
	@PostMapping("/emailofferpdf")
	public String emailOfferPDF(Model theModel, HttpServletRequest request, HttpServletResponse response) {
		try {
			this.generateJasperReport(request, response, false, true, "offerReport");
		} catch(Exception e) {
			System.out.println("Exception occurrec while Sending PDF Report");
		}
		
		//send over to our form
		return "redirect:/avs/updateEmployeeWithId?employeeId="+request.getParameter("empIdInput");
	}
	
	
	
	@GetMapping("/printsalarypdf")
	public void printSalaryPDF(Model theModel, HttpServletResponse response, HttpServletRequest request) {
		// Create model attribute to bind form data
		try {
			System.out.println("printPDF method started!!!");
			this.generateJasperReport(request, response, false, false, "salaryReport");
			
			
			System.out.println("Sending SMS Started!!!");
			String output = this.sendSms();
			System.out.println("Sending SMS Finished!!!");
			System.out.println("Output = " + output);
		} catch(Exception e) {
			System.out.println("Exception occurrec while generating Jasper Report");
		}
	}
	
	@GetMapping("/printsalaryxml")
	public void printSalaryXML(Model theModel, HttpServletResponse response, HttpServletRequest request) {
		try {
			this.generateJasperReport(request, response, true, false, "salaryReport");
		} catch(Exception e) {
			System.out.println("Exception occurred while generating XML Data.");
		}
	}
	
	@PostMapping("/emailsalarypdf")
	public String emailSalaryPDF(Model theModel, HttpServletRequest request, HttpServletResponse response) {
		try {
			this.generateJasperReport(request, response, false, true, "salaryReport");
		} catch(Exception e) {
			System.out.println("Exception occurrec while Sending PDF Report");
		}
		
		//send over to our form
		return "redirect:/avs/updateEmployeeWithId?employeeId="+request.getParameter("empIdInput");
	}
	
	
	public static boolean addElement(Element root, String name, String value) {
		if(root!=null && StringUtils.isNotBlank(name) && StringUtils.isNotBlank(value) && !StringUtils.containsOnly(value, ", ")) {
			root.addElement(name).addText(value.trim());
			return true;
		}
		
		return false;
	}
	
	public void generateJasperReport(HttpServletRequest request, HttpServletResponse response, boolean printXML, boolean sendPDFEmail, String docName) throws Exception {
		
		String empId = request.getParameter("empIdInput");
		
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
        	String logoPath = request.getSession().getServletContext().getRealPath("/resources/reports/logo.jpg");
            System.out.println("fullPath = " + logoPath);
            boolean logoElement = addElement(alwaysNode, "logoPath", logoPath);
        	System.out.println("doc.asXML() = " + doc.asXML());
        	
        	
        	System.out.println("Line 194 empId = " + empId);
        	
        	Employee emp = employeeService.getEmployee(empId);
        	System.out.println("emp = " + emp);
        	
        	Element empInfoNode = alwaysNode.addElement("EmpInfo");
        	addElement(empInfoNode, "EmpId", emp.getId());
        	addElement(empInfoNode, "FirstName", emp.getFirstName());
        	addElement(empInfoNode, "LastName", emp.getLastName());
        	addElement(empInfoNode, "Gender", emp.getGender());
        	addElement(empInfoNode, "PermanentAddress", emp.getPermanentAddress());
        	addElement(empInfoNode, "CurrentAddress", emp.getCurrentAddress());
        	addElement(empInfoNode, "PhoneNumber", emp.getPhoneNumber());
        	addElement(empInfoNode, "EmailAddress", emp.getEmail());
        	addElement(empInfoNode, "DateOfJoining", emp.getDateOfJoining()!=null ? new SimpleDateFormat("MM/dd/yyyy").format(emp.getDateOfJoining()) : "" );
        	addElement(empInfoNode, "DateOfBirth", emp.getDateOfBirth()!=null ? new SimpleDateFormat("MM/dd/yyyy").format(emp.getDateOfBirth()) : "" );
        	addElement(empInfoNode, "AccessLevel", emp.getAccessLevel()!=null ? emp.getAccessLevel().toString() : "");
        	
        	
        	List<EmpSalary> salaryComponents = empSalaryService.getEmpSalaryComponents(empId);
        	
        	Element empSalaryComponentsNode = empInfoNode.addElement("SalaryComponents");
        	
        	for(EmpSalary component: salaryComponents) {
        		Element componentNode = empSalaryComponentsNode.addElement("Component");
            	addElement(componentNode, "ComponentId", component.getComponentId());
            	addElement(componentNode, "ComponentDesc", component.getComponentDesc());
            	addElement(componentNode, "ComponentAmt", Long.toString(component.getComponentAmt()));
        	}
        	
        	
        	
        	// XML for Salary Earnings
        	List<EmpSalary> salaryEarningComponentsTemp = empSalaryService.getEmpSalaryComponents(empId);
        	
        	Element empSalaryEarningNodeTemp = empInfoNode.addElement("SalaryEarnings");
        	Element componentNode1 = empSalaryEarningNodeTemp.addElement("Component");
        	addElement(componentNode1, "ComponentDesc", "BASIC");
        	addElement(componentNode1, "ComponentAmt", "10000.00");
        	
        	Element componentNode2 = empSalaryEarningNodeTemp.addElement("Component");
        	addElement(componentNode2, "ComponentDesc", "H.R.A");
        	addElement(componentNode2, "ComponentAmt", "4000.00");
        	
        	Element componentNode3 = empSalaryEarningNodeTemp.addElement("Component");
        	addElement(componentNode3, "ComponentDesc", "COLA");
        	addElement(componentNode3, "ComponentAmt", "9540.00");
        	
        	Element componentNode4 = empSalaryEarningNodeTemp.addElement("Component");
        	addElement(componentNode4, "ComponentDesc", "CONVEYANCE");
        	addElement(componentNode4, "ComponentAmt", "1600.00");
        	
        	Element componentNode5 = empSalaryEarningNodeTemp.addElement("Component");
        	addElement(componentNode5, "ComponentDesc", "SPL TRANSPORT ALLOW");
        	addElement(componentNode5, "ComponentAmt", "5000.00");
        	
        	Element componentNode6 = empSalaryEarningNodeTemp.addElement("Component");
        	addElement(componentNode6, "ComponentDesc", "MEDICAL");
        	addElement(componentNode6, "ComponentAmt", "1250.00");
        	
        	Element componentNode7 = empSalaryEarningNodeTemp.addElement("Component");
        	addElement(componentNode7, "ComponentDesc", "SHIFT ALLOWANCE");
        	addElement(componentNode7, "ComponentAmt", "8000.00");
        	
        	Element componentNode8 = empSalaryEarningNodeTemp.addElement("Component");
        	addElement(componentNode8, "ComponentDesc", "MONTHLY VARIABLE");
        	addElement(componentNode8, "ComponentAmt", "6640.00");
        	
        	Element componentNode9 = empSalaryEarningNodeTemp.addElement("Component");
        	addElement(componentNode9, "ComponentDesc", "OTHER EARNINGS");
        	addElement(componentNode9, "ComponentAmt", "27857.00");
        	
        	Element componentNode10 = empSalaryEarningNodeTemp.addElement("Component");
        	addElement(componentNode10, "ComponentDesc", "SMA");
        	addElement(componentNode10, "ComponentAmt", "1966.00");
        	
        	
        	// XML for Salary Deductions
        	Element empSalaryDeductionsNodeTemp = empInfoNode.addElement("SalaryDeductions");
        	
        	Element componentNode11 = empSalaryDeductionsNodeTemp.addElement("Component");
        	addElement(componentNode11, "ComponentDesc", "PROV. FUND");
        	addElement(componentNode11, "ComponentAmt", "1200.00");
        	
        	Element componentNode12 = empSalaryDeductionsNodeTemp.addElement("Component");
        	addElement(componentNode12, "ComponentDesc", "P.TAX");
        	addElement(componentNode12, "ComponentAmt", "200.00");
        	
        	Element componentNode13 = empSalaryDeductionsNodeTemp.addElement("Component");
        	addElement(componentNode13, "ComponentDesc", "TDS");
        	addElement(componentNode13, "ComponentAmt", "18730.00");
        	
        	
        	
        	/*Element componentNode1 = empSalaryComponentsNode.addElement("Component");
        	addElement(componentNode1, "ComponentId", "101");
        	addElement(componentNode1, "ComponentDesc", "Basic");
        	addElement(componentNode1, "ComponentAmt", "15000");
        	
        	Element componentNode2 = empSalaryComponentsNode.addElement("Component");
        	addElement(componentNode2, "ComponentId", "102");
        	addElement(componentNode2, "ComponentDesc", "COLA");
        	addElement(componentNode2, "ComponentAmt", "4000");
        	
        	Element componentNode3 = empSalaryComponentsNode.addElement("Component");
        	addElement(componentNode3, "ComponentId", "103");
        	addElement(componentNode3, "ComponentDesc", "HRA");
        	addElement(componentNode3, "ComponentAmt", "9000");*/
        	
        	
        	//JRXmlDataSource jrxmlDS = new JRXmlDataSource((org.w3c.dom.Document) doc);
        	
        	if(printXML) {
        		response.setContentType("application/xml");
        		OutputStream responseOutputStream = response.getOutputStream();
                byte[] bytes=doc.asXML().getBytes();
                if(bytes.length>1){
                	responseOutputStream.write(bytes);
                	responseOutputStream.flush();
                	responseOutputStream.close();
                    /*File someFile = new File("C:/Java New Lapee/iReport/Temp/testReport.pdf");
                    fos = new FileOutputStream(someFile);
                    fos.write(bytes);
                    fos.flush();
                    fos.close();*/
                    System.out.println("<<<<<<<<<<<<XML Created>>>>>>>>");
                }
        	} else {
        		if(sendPDFEmail) {
        			/*response.setContentType("application/pdf");
            		OutputStream responseOutputStream = response.getOutputStream();
                    byte[] bytes=this.generateJasperReportPDF("inputReport", outputStream, list, doc, request);
                    if(bytes.length>1){
                    	responseOutputStream.write(bytes);
                    	responseOutputStream.flush();
                    	responseOutputStream.close();
                        File someFile = new File("C:/Java New Lapee/iReport/Temp/testReport.pdf");
                        fos = new FileOutputStream(someFile);
                        fos.write(bytes);
                        fos.flush();
                        fos.close();
                    }*/
                    
        			// TODO Auto-generated method stub
            		//response.getWriter().append("Served at: ").append(request.getContextPath() + "<br>");
            		System.out.println("This is first Servlet!!! Strating mail sending process...");
            		
            		//response.setContentType("text/html");  
            	    //PrintWriter out = response.getWriter();  
            	    
            	    String ip = request.getRemoteAddr();
            	    if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
            	        InetAddress inetAddress = InetAddress.getLocalHost();
            	        String ipAddress = inetAddress.getHostAddress();
            	        ip = ipAddress;
            	    }
            	    
            	    //out.println("User IP: " + ip + "<br>");
            	    
            	    //String to=request.getParameter("to");  
            	    //String subject=request.getParameter("subject");  
            	    //String msg=request.getParameter("msg");
            	    
            	    System.out.println("Line 238 = " + request.getParameter("inputEmailId"));
            	    String to= request.getParameter("inputEmailId");  
            	    String subject="Student Database information";
            	    List<Student> students =  new ArrayList<>();
            	    
            	    StringBuffer body = new StringBuffer();
            	    try {
            	    	Class.forName("com.mysql.jdbc.Driver");
            		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shreesha_student","shreesha_sachin","Sachin@123");
            		    System.out.println("Connection successfull");
            		    //out.println("Connection successfull");
            		    Statement st = con.createStatement();
            		    ResultSet rs = st.executeQuery("select * from studTable");
            		    
            		    
            		    
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
            		
            			.append("<h2>Student Information</h2>")
            		
            			.append("<table>")
            			  .append("<tr>")
            				.append("<th>Student ID</th>")
            				.append("<th>Student Name</th>")
            				.append("<th>Student Email</th>")
            				.append("<th>Marks</th>")
            			  .append("</tr>");
            			  while(rs.next()) {
            				  students.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            				  body.append("<tr>")
            					.append("<td>" + rs.getInt(1) + "</td>")
            					.append("<td>" + rs.getString(2) + "</td>")
            					.append("<td>" + rs.getString(3) + "</td>")
            					.append("<td>" + rs.getString(4) + "</td>")
            				  .append("</tr>");
            			    }
            			body.append("</table>")
            		
            			.append("</body>")
            			.append("</html>");
            	    } catch(Exception e) {
            	    	e.printStackTrace();
            	    	System.out.println("Exception occurred while connecting to database.");
            	    	//out.println("Exception occurred while connecting to database.");
            	    }
            	    
            	    	    
            	    String msg=body.toString();
            	    
            	    //out.println(" Line 48<br>");
            	    
            	    byte[] bytesForPDF=this.generateJasperReportPDF(docName, outputStream, list, doc, request);
            	    Mailer.send(to, subject, msg, students, bytesForPDF);  
            	    //out.print("message has been sent successfully");  
            	    //out.close();  
                	
                	
                	
                    System.out.println("<<<<<<<<<<<<PDF Email Sent>>>>>>>>");
        		} else {
        			response.setContentType("application/pdf");
            		OutputStream responseOutputStream = response.getOutputStream();
                    byte[] bytes=this.generateJasperReportPDF(docName, outputStream, list, doc, request);
                    if(bytes.length>1){
                    	responseOutputStream.write(bytes);
                    	responseOutputStream.flush();
                    	responseOutputStream.close();
                        /*File someFile = new File("C:/Java New Lapee/iReport/Temp/testReport.pdf");
                        fos = new FileOutputStream(someFile);
                        fos.write(bytes);
                        fos.flush();
                        fos.close();*/
                        System.out.println("<<<<<<<<<<<<Report Created>>>>>>>>");
                    }
        		}
        		
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
    
    public String sendSms() {
		try {
			// Construct data
			String apiKey = "apikey=" + "suY/DPGllw8-Eo60XC8QWxZmSZB2K6iuzBdp8yU5w8";
			String message = "&message=" + "This SMS sent from AVS Technolgies Website, while printing Salary Slip.";
			//String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + "7021705635";
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message;// + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			System.out.println("Success = " + stringBuffer.toString());
			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}
}

