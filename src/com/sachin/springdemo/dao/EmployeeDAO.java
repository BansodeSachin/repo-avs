package com.sachin.springdemo.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sachin.springdemo.entity.Employee;

public interface EmployeeDAO {
	public List<Employee> getEmployees();

	public void saveEmployee(Employee theEmployee);

	public Employee getEmployee(String theId);

	public void deleteEmployee(String theId);

	public boolean isValidUserLogin(String id, String password, String reCaptchaResponse, HttpServletRequest request, HttpServletResponse response);
}
