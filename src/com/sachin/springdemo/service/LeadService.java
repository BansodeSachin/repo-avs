package com.sachin.springdemo.service;

import java.util.List;

import com.sachin.springdemo.entity.LeadInfo;

public interface LeadService {

	public List<LeadInfo> getLeads();

	public void saveLead(LeadInfo theLead);

	public LeadInfo getLead(String theId);

	public void deleteLead(String theId);
	
}
