package com.sachin.springdemo.entity;

public class Student {
	
	private int sid;
	private String name;
	private String email;
	private String marks;
	
	public Student(int sid, String name, String email, String marks) {
		super();
		this.sid = sid;
		this.name = name;
		this.email = email;
		this.marks = marks;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	
}
