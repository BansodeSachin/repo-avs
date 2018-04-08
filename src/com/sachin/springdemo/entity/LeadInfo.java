package com.sachin.springdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="lead_records")
public class LeadInfo {
	
	@Id
	@Column(name="lead_id")
	private String leadId;
	
	@Column(name="emp_id")
	private String empId;
	
	@NotNull(message="Agent Name should not be blank")
	@Size(min=1, message="Agent Name should not be blank")
	@Column(name="agent_name")
	private String agentName;
	
	@Column(name="lead_status")
	private String leadStatus;
	
	@NotNull(message="Center Code should not be blank")
	@Size(min=1, message="Center Code should not be blank")
	@Column(name="center_code")
	private String center_code;
	
	@NotNull(message="Med ID should not be blank")
	@Size(min=1, message="Med ID should not be blank")
	@Column(name="med_id")
	private String medId;
	
	@NotNull(message="PPO ID should not be blank")
	@Size(min=1, message="PPO ID should not be blank")
	@Column(name="ppo_id")
	private String ppoId;
	
	@Column(name="insurance_type")
	private String insuranceType;
	
	@NotNull(message="Customer First Name should not be blank")
	@Size(min=1, message="Customer First Name should not be blank")
	@Column(name="cust_fname")
	private String custFname;
	
	@NotNull(message="Customer Last Name should not be blank")
	@Size(min=1, message="Customer Last Name should not be blank")
	@Column(name="cust_lname")
	private String custLname;
	
	@NotNull(message="Customer Phone should not be blank")
	@Size(min=1, message="Customer Phone should not be blank")
	@Column(name="cust_phone")
	private String custPhone;
	
	@Column(name="cust_dob")
	private String custDOB;
	
	@Column(name="eligiblity_check")
	private String eligibilityCheck;
	
	@Column(name="is_cancer_history")
	private String isCancerHistory;
	
	@NotNull(message="Relation should not be blank")
	@Size(min=1, message="Relation should not be blank")
	@Column(name="cancer_hist_relation")
	private String cancerHistRelation;
	
	@NotNull(message="Age should not be blank")
	@Size(min=1, message="Age should not be blank")
	@Column(name="cancer_hist_age")
	private String cancerHistAge;
	
	@Column(name="cust_gender")
	private String custGender;
	
	@NotNull(message="Weight should not be blank")
	@Size(min=1, message="Weight should not be blank")
	@Column(name="cust_weight")
	private String custWeight;
	
	@NotNull(message="Height should not be blank")
	@Size(min=1, message="Height should not be blank")
	@Column(name="cust_height")
	private String custHeight;
	
	@Column(name="past_braces_received")
	private String pastBracesReceived;
	
	@Column(name="past_braces_desc")
	private String pastBracesDesc;
	
	@NotNull(message="Street Address should not be blank")
	@Size(min=1, message="Street Address should not be blank")
	@Column(name="cust_street_address")
	private String custStreetAddress;
	
	@NotNull(message="City should not be blank")
	@Size(min=1, message="City should not be blank")
	@Column(name="cust_city")
	private String custCity;
	
	@NotNull(message="State should not be blank")
	@Size(min=1, message="State should not be blank")
	@Column(name="cust_state")
	private String custState;
	
	@NotNull(message="Zip Code should not be blank")
	@Size(min=1, message="Zip Code should not be blank")
	@Column(name="cust_zip_code")
	private String custZipCode;
	
	@Column(name="additional_note")
	private String additionalNote;
	
	@NotNull(message="Customer Best Time to call should not be blank")
	@Size(min=1, message="Customer Best Time to call should not be blank")
	@Column(name="cust_best_time_to_call")
	private String custBestTimeToCall;
	
	@Column(name="brc_back")
	private Boolean brcBack = false;
	
	@Column(name="brc_right_shoulder")
	private Boolean brcRightShoulder = false;
	
	@Column(name="brc_left_shoulder")
	private Boolean brcLeftShoulder = false;
	
	@Column(name="brc_right_ankle")
	private Boolean brcRightAnkle = false;
	
	@Column(name="brc_left_ankle")
	private Boolean brcLeftAnkle = false;
	
	@Column(name="brc_right_wrist")
	private Boolean brcRightWrist = false;
	
	@Column(name="brc_left_wrist")
	private Boolean brcLeftWrist = false;
	
	@Column(name="brc_right_elbow")
	private Boolean brcRightElbow = false;
	
	@Column(name="brc_left_elbow")
	private Boolean brcLeftElbow = false;
	
	@Column(name="brc_right_knee")
	private Boolean brcRightKnee = false;
	
	@Column(name="brc_left_knee")
	private Boolean brcLeftKnee = false;
	
	@Column(name="brc_is_cgx")
	private Boolean brcIsCGX = false;
	
	@Column(name="brc_is_pain_cream")
	private Boolean brcIsPainCream = false;
	
	//String[] braces;
	
	public LeadInfo() {
		
	}

	

	@Override
	public String toString() {
		return "LeadInfo [leadId=" + leadId + ", empId=" + empId + ", agentName=" + agentName + ", leadStatus="
				+ leadStatus + ", center_code=" + center_code + ", medId=" + medId + ", ppoId=" + ppoId
				+ ", insuranceType=" + insuranceType + ", custFname=" + custFname + ", custLname=" + custLname
				+ ", custPhone=" + custPhone + ", custDOB=" + custDOB + ", eligibilityCheck=" + eligibilityCheck
				+ ", isCancerHistory=" + isCancerHistory + ", cancerHistRelation=" + cancerHistRelation
				+ ", cancerHistAge=" + cancerHistAge + ", custGender=" + custGender + ", custWeight=" + custWeight
				+ ", custHeight=" + custHeight + ", pastBracesReceived=" + pastBracesReceived + ", pastBracesDesc="
				+ pastBracesDesc + ", custStreetAddress=" + custStreetAddress + ", custCity=" + custCity
				+ ", custState=" + custState + ", custZipCode=" + custZipCode + ", additionalNote=" + additionalNote
				+ ", custBestTimeToCall=" + custBestTimeToCall + ", brcBack=" + brcBack + ", brcRightShoulder="
				+ brcRightShoulder + ", brcLeftShoulder=" + brcLeftShoulder + ", brcRightAnkle=" + brcRightAnkle
				+ ", brcLeftAnkle=" + brcLeftAnkle + ", brcRightWrist=" + brcRightWrist + ", brcLeftWrist="
				+ brcLeftWrist + ", brcRightElbow=" + brcRightElbow + ", brcLeftElbow=" + brcLeftElbow
				+ ", brcRightKnee=" + brcRightKnee + ", brcLeftKnee=" + brcLeftKnee + ", brcIsCGX=" + brcIsCGX
				+ ", brcIsPainCream=" + brcIsPainCream + "]";
	}



	public String getLeadId() {
		return leadId;
	}

	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getLeadStatus() {
		return leadStatus;
	}

	public void setLeadStatus(String leadStatus) {
		this.leadStatus = leadStatus;
	}

	public String getCenter_code() {
		return center_code;
	}

	public void setCenter_code(String center_code) {
		this.center_code = center_code;
	}

	public String getMedId() {
		return medId;
	}

	public void setMedId(String medId) {
		this.medId = medId;
	}

	public String getPpoId() {
		return ppoId;
	}

	public void setPpoId(String ppoId) {
		this.ppoId = ppoId;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public String getCustFname() {
		return custFname;
	}

	public void setCustFname(String custFname) {
		this.custFname = custFname;
	}

	public String getCustLname() {
		return custLname;
	}

	public void setCustLname(String custLname) {
		this.custLname = custLname;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public String getCustDOB() {
		return custDOB;
	}

	public void setCustDOB(String custDOB) {
		this.custDOB = custDOB;
	}

	public String getEligibilityCheck() {
		return eligibilityCheck;
	}

	public void setEligibilityCheck(String eligibilityCheck) {
		this.eligibilityCheck = eligibilityCheck;
	}

	public String getIsCancerHistory() {
		return isCancerHistory;
	}

	public void setIsCancerHistory(String isCancerHistory) {
		this.isCancerHistory = isCancerHistory;
	}

	public String getCancerHistRelation() {
		return cancerHistRelation;
	}

	public void setCancerHistRelation(String cancerHistRelation) {
		this.cancerHistRelation = cancerHistRelation;
	}

	public String getCancerHistAge() {
		return cancerHistAge;
	}

	public void setCancerHistAge(String cancerHistAge) {
		this.cancerHistAge = cancerHistAge;
	}

	public String getCustGender() {
		return custGender;
	}

	public void setCustGender(String custGender) {
		this.custGender = custGender;
	}

	public String getCustWeight() {
		return custWeight;
	}

	public void setCustWeight(String custWeight) {
		this.custWeight = custWeight;
	}

	public String getCustHeight() {
		return custHeight;
	}

	public void setCustHeight(String custHeight) {
		this.custHeight = custHeight;
	}

	public String getPastBracesReceived() {
		return pastBracesReceived;
	}

	public void setPastBracesReceived(String pastBracesReceived) {
		this.pastBracesReceived = pastBracesReceived;
	}

	public String getPastBracesDesc() {
		return pastBracesDesc;
	}

	public void setPastBracesDesc(String pastBracesDesc) {
		this.pastBracesDesc = pastBracesDesc;
	}

	public String getCustStreetAddress() {
		return custStreetAddress;
	}

	public void setCustStreetAddress(String custStreetAddress) {
		this.custStreetAddress = custStreetAddress;
	}

	public String getCustCity() {
		return custCity;
	}

	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}

	public String getCustState() {
		return custState;
	}

	public void setCustState(String custState) {
		this.custState = custState;
	}

	public String getCustZipCode() {
		return custZipCode;
	}

	public void setCustZipCode(String custZipCode) {
		this.custZipCode = custZipCode;
	}

	public String getAdditionalNote() {
		return additionalNote;
	}

	public void setAdditionalNote(String additionalNote) {
		this.additionalNote = additionalNote;
	}

	public String getCustBestTimeToCall() {
		return custBestTimeToCall;
	}

	public void setCustBestTimeToCall(String custBestTimeToCall) {
		this.custBestTimeToCall = custBestTimeToCall;
	}

	public boolean isBrcBack() {
		return brcBack;
	}

	public void setBrcBack(boolean brcBack) {
		this.brcBack = brcBack;
	}

	public boolean isBrcRightShoulder() {
		return brcRightShoulder;
	}

	public void setBrcRightShoulder(boolean brcRightShoulder) {
		this.brcRightShoulder = brcRightShoulder;
	}

	public boolean isBrcLeftShoulder() {
		return brcLeftShoulder;
	}

	public void setBrcLeftShoulder(boolean brcLeftShoulder) {
		this.brcLeftShoulder = brcLeftShoulder;
	}

	public boolean isBrcRightAnkle() {
		return brcRightAnkle;
	}

	public void setBrcRightAnkle(boolean brcRightAnkle) {
		this.brcRightAnkle = brcRightAnkle;
	}

	public boolean isBrcLeftAnkle() {
		return brcLeftAnkle;
	}

	public void setBrcLeftAnkle(boolean brcLeftAnkle) {
		this.brcLeftAnkle = brcLeftAnkle;
	}

	public boolean isBrcRightWrist() {
		return brcRightWrist;
	}

	public void setBrcRightWrist(boolean brcRightWrist) {
		this.brcRightWrist = brcRightWrist;
	}

	public boolean isBrcLeftWrist() {
		return brcLeftWrist;
	}

	public void setBrcLeftWrist(boolean brcLeftWrist) {
		this.brcLeftWrist = brcLeftWrist;
	}

	public boolean isBrcRightElbow() {
		return brcRightElbow;
	}

	public void setBrcRightElbow(boolean brcRightElbow) {
		this.brcRightElbow = brcRightElbow;
	}

	public boolean isBrcLeftElbow() {
		return brcLeftElbow;
	}

	public void setBrcLeftElbow(boolean brcLeftElbow) {
		this.brcLeftElbow = brcLeftElbow;
	}

	public boolean isBrcRightKnee() {
		return brcRightKnee;
	}

	public void setBrcRightKnee(boolean brcRightKnee) {
		this.brcRightKnee = brcRightKnee;
	}

	public boolean isBrcLeftKnee() {
		return brcLeftKnee;
	}

	public void setBrcLeftKnee(boolean brcLeftKnee) {
		this.brcLeftKnee = brcLeftKnee;
	}

	public boolean isBrcIsCGX() {
		return brcIsCGX;
	}

	public void setBrcIsCGX(boolean brcIsCGX) {
		this.brcIsCGX = brcIsCGX;
	}

	public boolean isBrcIsPainCream() {
		return brcIsPainCream;
	}

	public void setBrcIsPainCream(boolean brcIsPainCream) {
		this.brcIsPainCream = brcIsPainCream;
	}

	/*public String[] getBraces() {
		return braces;
	}

	public void setBraces(String[] braces) {
		this.braces = braces;
	}*/

}
