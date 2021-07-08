package com.luv2code.springboot.crm.service;

import java.util.List;

import com.luv2code.springboot.crm.carservicer.entity.CarServicer;
import com.luv2code.springboot.crm.carservicer.entity.PlacedRequest;
import com.luv2code.springboot.crm.entity.Customer;
import com.luv2code.springboot.crm.entity.CustomerRequest;


public interface CustomerService {
	
	public Customer getCustomer(int id);

	public CarServicer getCarServicer(int id);
	
	public Customer getCustomerByUser(String user);
	
	public CarServicer getCarServicerByName(String username);
	
	public void saveCustomer(Customer customer);
	
	public void saveCarServicer(CarServicer carServicer);
	
	public void deleteCustomer(int id);
	
	public void deleteCarservicer(int id);
	
	public List<CustomerRequest> getRequests(String email);
	
	public List<CarServicer> getCarServicers(int pincode);
	
	public void savePlacedRequest(PlacedRequest placedRequest);
}
