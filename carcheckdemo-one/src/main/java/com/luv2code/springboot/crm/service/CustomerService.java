package com.luv2code.springboot.crm.service;

import com.luv2code.springboot.crm.entity.Customer;


public interface CustomerService {
	
	public Customer getCustomer(int id);
	
	public Customer getCustomerByUser(String user);
	
	public void saveCustomer(Customer customer);
	
	public void deleteCustomer(int id);
}
