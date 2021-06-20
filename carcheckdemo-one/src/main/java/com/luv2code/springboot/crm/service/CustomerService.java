package com.luv2code.springboot.crm.service;

import com.luv2code.springboot.crm.carservicer.entity.CarServicer;
import com.luv2code.springboot.crm.entity.Customer;


public interface CustomerService {
	
	public Customer getCustomer(int id);

	public CarServicer getCarServicer(int id);
	
	public Customer getCustomerByUser(String user);
	
	public CarServicer getCarServicerByName(String username);
	
	public void saveCustomer(Customer customer);
	
	public void saveCarServicer(CarServicer carServicer);
	
	public void deleteCustomer(int id);
	
	public void deleteCarservicer(int id);
}
