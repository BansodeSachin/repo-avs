package com.sachin.springdemo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sachin.springdemo.dao.EmpSalaryDAO;
import com.sachin.springdemo.dao.EmployeeDAO;
import com.sachin.springdemo.entity.EmpSalary;
import com.sachin.springdemo.entity.Employee;

@Service
public class EmpSalaryServiceImpl implements EmpSalaryService {

	// Need to inject employee DAO
	@Autowired
	private EmpSalaryDAO empSalaryDAO;
	
	
	@Override
	@Transactional
	public List<EmpSalary> getEmpSalaryComponents(String theId) {
		return empSalaryDAO.getEmpSalaryComponents(theId);
	}


	@Override
	@Transactional
	public void saveEmpSalaryComponent(EmpSalary theEmpSalary) {
		// Save employee in EmployeeDAO
		empSalaryDAO.saveEmpSalaryComponent(theEmpSalary);
		
	}


	@Override
	@Transactional
	public EmpSalary getEmpSalaryComponent(String theId, String componentId) {
		// TODO Auto-generated method stub
		return empSalaryDAO.getEmpSalaryComponent(theId, componentId);
	}


	@Override
	@Transactional
	public void deleteEmpSalaryComponent(String theId, String componentId) {
		empSalaryDAO.deleteEmpSalaryComponent(theId, componentId);
		
	}


}
