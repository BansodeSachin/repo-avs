package com.sachin.springdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="emp_salary_records")
public class EmpSalary {
	
	@Column(name="emp_id")
	private String id;
	
	@Id
	@Column(name="component_id")
	private String componentId;
	
	@Column(name="component_desc")
	private String componentDesc;
	
	@Column(name="component_amt")
	private long componentAmt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getComponentDesc() {
		return componentDesc;
	}

	public void setComponentDesc(String componentDesc) {
		this.componentDesc = componentDesc;
	}

	public long getComponentAmt() {
		return componentAmt;
	}

	public void setComponentAmt(long componentAmt) {
		this.componentAmt = componentAmt;
	}

	@Override
	public String toString() {
		return "EmpSalary [id=" + id + ", componentId=" + componentId + ", componentDesc=" + componentDesc
				+ ", componentAmt=" + componentAmt + "]";
	}
}
