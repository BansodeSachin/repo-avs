package com.sachin.springdemo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sachin.springdemo.entity.EmpSalary;
import com.sachin.springdemo.entity.Employee;

public interface EmpSalaryService {

	public List<EmpSalary> getEmpSalaryComponents(String theId);

	public void saveEmpSalaryComponent(EmpSalary theEmpSalary);

	public EmpSalary getEmpSalaryComponent(String theId, String componentId);

	public void deleteEmpSalaryComponent(String theId, String componentId);
	
}