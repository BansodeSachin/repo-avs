package com.sachin.springdemo.service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;  
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sachin.springdemo.entity.Student;  
  
public class Mailer {

	private static String[] columns = {"Student ID", "Student Name", "Email ID", "Marks"};
    //private static List<Student> students =  new ArrayList<>();
    
	// Initializing Students data to insert into the excel file
    /*static {
    	students.add(new Student(101, "Sachin Bansode", "bansodesachin0@gmail.com"));

    	students.add(new Student(102, "Nitin Bansode", "bansodenitin26@gmail.com"));

    	students.add(new Student(103, "Test Test", "test@gmail.com"));
    }*/
	
	
	
public static void send(String to,String subject,String msg, List<Student> students, byte[] bytesForPDF){
	
	Workbook workbookForAttachment = createExcelFile(students);
  
final String user="bansodesachin0@gmail.com";//change accordingly  
final String pass="sachin123";  
  
//out.println(" Line 16<br>");
//1st step) Get the session object    
Properties props = new Properties();  
props.put("mail.smtp.host", "smtp.gmail.com");//change accordingly  
props.put("mail.smtp.port", "587"); // You can also use port 587
props.put("mail.smtp.auth", "true");  
props.put("mail.smtp.starttls.enable", "true");


//out.println(" Line 24<br>");
Session session = Session.getDefaultInstance(props,  
 new javax.mail.Authenticator() {  
  protected PasswordAuthentication getPasswordAuthentication() {  
   return new PasswordAuthentication(user,pass);  
   }  
});  
//2nd step)compose message  
try {  
	//out.println(" Line 34<br>");
 MimeMessage message = new MimeMessage(session);  
 message.setFrom(new InternetAddress(user));  
 message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
 message.setSubject(subject);  
 //message.setText(msg);
 //message.setContent(msg, "text/html");
 
 
 //Create a multipart message
 Multipart multipart = new MimeMultipart();
 //Create the message part
 BodyPart messageBodyPart1 = new MimeBodyPart();
 BodyPart messageBodyPart2 = new MimeBodyPart();
 BodyPart messageBodyPart3 = new MimeBodyPart();

 // Now set the actual message
 messageBodyPart1.setContent(msg, "text/html");

  // Part two is Excel attachment
 messageBodyPart2 = new MimeBodyPart();
 
 // Part three is PDF attachment
 messageBodyPart3 = new MimeBodyPart();
 
 DataSource ds = null;
 ByteArrayOutputStream baos = new ByteArrayOutputStream();
 try{
	 workbookForAttachment.write(baos);
	 byte[] bytes = baos.toByteArray();
	 ds = new ByteArrayDataSource(bytes, "application/excel");
 }catch (IOException ioe ){
	 ioe.printStackTrace();
 }
 
 DataHandler dh = new DataHandler(ds);
 messageBodyPart2.setHeader("Content-Disposition", "attachment;filename=StudentInformation.xls");
 messageBodyPart2.setDataHandler(dh);
 messageBodyPart2.setFileName("Student Information.xls");
 

 DataSource dsPDF = null;
 dsPDF = new ByteArrayDataSource(bytesForPDF, "application/pdf");
 
 DataHandler dhPDF = new DataHandler(dsPDF);
 messageBodyPart3.setHeader("Content-Disposition", "attachment;filename=TestReport.pdf");
 messageBodyPart3.setDataHandler(dhPDF);
 messageBodyPart3.setFileName("Test Jasper Report.pdf");
 
 /*String filename = "C://Sachin/hioxSteps.txt";
 DataSource source = new FileDataSource(filename);
 messageBodyPart2.setDataHandler(new DataHandler(source));
 messageBodyPart2.setFileName(filename);*/
 
 multipart.addBodyPart(messageBodyPart1);  
 multipart.addBodyPart(messageBodyPart2);  
 multipart.addBodyPart(messageBodyPart3);
 

 // Send the complete message parts
 message.setContent(multipart, "text/html");
   
 //out.println(" Line 41<br>");
 
 //3rd step)send message  
 Transport.send(message);  
  
 //out.println("Done<br>");  
  
 } catch (MessagingException e) {
	 System.out.println(" Line 47<br>");
	 System.out.println(" Exception = " + e.getMessage());
	 e.printStackTrace();
    throw new RuntimeException(e);  
 }  
      
}

public static void send(String to,String subject,String msg){
	
  
final String user="bansodesachin0@gmail.com";//change accordingly  
final String pass="sachin123";  
  
//out.println(" Line 16<br>");
//1st step) Get the session object    
Properties props = new Properties();  
props.put("mail.smtp.host", "smtp.gmail.com");//change accordingly  
props.put("mail.smtp.port", "587"); // You can also use port 587
props.put("mail.smtp.auth", "true");  
props.put("mail.smtp.starttls.enable", "true");


//out.println(" Line 24<br>");
Session session = Session.getDefaultInstance(props,  
 new javax.mail.Authenticator() {  
  protected PasswordAuthentication getPasswordAuthentication() {  
   return new PasswordAuthentication(user,pass);  
   }  
});  
//2nd step)compose message  
try {  
	//out.println(" Line 34<br>");
 MimeMessage message = new MimeMessage(session);  
 message.setFrom(new InternetAddress(user));  
 message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
 message.setSubject(subject);  
 //message.setText(msg);
 //message.setContent(msg, "text/html");
 
 
 //Create a multipart message
 Multipart multipart = new MimeMultipart();
 //Create the message part
 BodyPart messageBodyPart1 = new MimeBodyPart();

 // Now set the actual message
 messageBodyPart1.setContent(msg, "text/html");
 multipart.addBodyPart(messageBodyPart1);  
 

 // Send the complete message parts
 message.setContent(multipart, "text/html");
   
 
 //3rd step)send message  
 Transport.send(message);  
  
 //out.println("Done<br>");  
  
 } catch (MessagingException e) {
	 System.out.println(" Exception = " + e.getMessage());
	 e.printStackTrace();
    throw new RuntimeException(e);  
 }  
      
}


public static Workbook createExcelFile(List<Student> students) {
	// Create a Workbook
    Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

    /* CreationHelper helps us create instances for various things like DataFormat, 
       Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
    CreationHelper createHelper = workbook.getCreationHelper();

    // Create a Sheet
    Sheet sheet = workbook.createSheet("Employee");

    // Create a Font for styling header cells
    Font headerFont = workbook.createFont();
    //headerFont.setBold(true);
    headerFont.setFontHeightInPoints((short) 14);
    headerFont.setColor(IndexedColors.RED.getIndex());

    // Create a CellStyle with the font
    CellStyle headerCellStyle = workbook.createCellStyle();
    headerCellStyle.setFont(headerFont);

    // Create a Row
    Row headerRow = sheet.createRow(0);

    // Creating cells
    for(int i = 0; i < columns.length; i++) {
        Cell cell = headerRow.createCell(i);
        cell.setCellValue(columns[i]);
        cell.setCellStyle(headerCellStyle);
    }

    // Create Cell Style for formatting Date
    CellStyle dateCellStyle = workbook.createCellStyle();
    dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

    // Create Other rows and cells with employees data
    int rowNum = 1;
    for(Student student: students) {
        Row row = sheet.createRow(rowNum++);

        row.createCell(0)
                .setCellValue(student.getSid());

        row.createCell(1)
                .setCellValue(student.getName());

        row.createCell(2)
                .setCellValue(student.getEmail());
        
        row.createCell(3)
        		.setCellValue(student.getMarks());
    }

	// Resize all columns to fit the content size
    for(int i = 0; i < columns.length; i++) {
        sheet.autoSizeColumn(i);
    }

    try {
    	// Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("sachin.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        System.out.println("Excel file generated successfully!");
        // Closing the workbook
        //workbook.close();
    } catch(Exception e) {
    	System.out.println("Exception occurred while creating excel file...");
    }
    
    return workbook;
    
}

}  