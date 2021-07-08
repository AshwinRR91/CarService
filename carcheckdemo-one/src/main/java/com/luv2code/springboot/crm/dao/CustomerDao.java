package com.luv2code.springboot.crm.dao;

import java.util.List;

import com.luv2code.springboot.crm.entity.Customer;
import com.luv2code.springboot.crm.entity.CustomerRequest;

public interface CustomerDao {
	
	public Customer getCustomer(int id);

	public Customer getCustomerByUser(String username);
	
	public void deleteCustomer(int id);
	
	public void saveCustomer(Customer customer);
	
	public List<CustomerRequest> getUserRequests(String email);
	
}
