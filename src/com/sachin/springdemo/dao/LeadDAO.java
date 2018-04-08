package com.sachin.springdemo.dao;

import java.util.List;

import com.sachin.springdemo.entity.LeadInfo;

public interface LeadDAO {
	public List<LeadInfo> getLeads();

	public void saveLeadInfo(LeadInfo theLead);

	public LeadInfo getLeadInfo(String theId);

	public void deleteLeadInfo(String theId);
}
