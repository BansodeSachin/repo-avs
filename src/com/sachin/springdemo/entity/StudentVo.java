package com.sachin.springdemo.entity;

public class StudentVo{
    private String rollNo;
    private String name;
 
    public StudentVo(String rollNo, String name){
        this.rollNo = rollNo;
        this.name = name;
    }
 
    public String getRollNo() {
        return rollNo;
    }
    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}