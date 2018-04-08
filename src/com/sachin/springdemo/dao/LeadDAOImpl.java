package com.sachin.springdemo.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sachin.springdemo.entity.LeadInfo;

@Repository
public class LeadDAOImpl implements LeadDAO {

	// Need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<LeadInfo> getLeads() {
		
		// Get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Create a query
		Query<LeadInfo> theQuery = 
				currentSession.createQuery("from LeadInfo order by leadId", LeadInfo.class);
		
		// Execute query and get result list
		List<LeadInfo> leads = theQuery.getResultList();
		
		// Return the results
		return leads;
	}

	@Override
	public void saveLeadInfo(LeadInfo theLeadInfo) {
		// Get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Save/Update the employee...
		currentSession.saveOrUpdate(theLeadInfo);
		
		
	}

	@Override
	public LeadInfo getLeadInfo(String theId) {
		// Get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Now retrieve/read from database using the primary key
		LeadInfo theLeadInfo = currentSession.get(LeadInfo.class, theId);
		
		return theLeadInfo;
	}

	@Override
	public void deleteLeadInfo(String theId) {
		// Get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Delete object with primary key
		Query theQuery = currentSession.createQuery("delete from LeadInfo where lead_id=:leadId");
		theQuery.setParameter("leadId", theId);
		theQuery.executeUpdate();
	}

}
