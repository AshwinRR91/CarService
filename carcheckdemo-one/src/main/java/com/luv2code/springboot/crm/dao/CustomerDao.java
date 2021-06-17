package com.luv2code.springboot.crm.dao;

import com.luv2code.springboot.crm.entity.Customer;

public interface CustomerDao {
	
	public Customer getCustomer(int id);

	public Customer getCustomerByUser(String username);
	
	public void deleteCustomer(int id);
	
	public void saveCustomer(Customer customer);
	
}
