
package com.luv2code.springboot.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.crm.carservicer.CarServicerDao.CarServicerImpl;
import com.luv2code.springboot.crm.carservicer.entity.CarServicer;
import com.luv2code.springboot.crm.carservicer.entity.PlacedRequest;
import com.luv2code.springboot.crm.dao.CustomerDaoImpl;
import com.luv2code.springboot.crm.entity.Customer;
import com.luv2code.springboot.crm.entity.CustomerRequest;

@Service
public class CustomerServicerDaoImpl implements CustomerService {

	@Autowired
	private CustomerDaoImpl customerDaoImpl;
	
	@Autowired
	private CarServicerImpl carServicerImpl;
	
	@Override
	@Transactional
	public Customer getCustomer(int id) {
		Customer customer = customerDaoImpl.getCustomer(id);
		return customer;
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		customerDaoImpl.saveCustomer(customer);
	}

	@Override
	@Transactional
	public void deleteCustomer(int id) {
		customerDaoImpl.deleteCustomer(id);
	}

	@Override
	@Transactional
	public Customer getCustomerByUser(String user) {
		// TODO Auto-generated method stub
		return customerDaoImpl.getCustomerByUser(user);
	}

	@Override
	@Transactional
	public CarServicer getCarServicer(int id) {
		return carServicerImpl.getCarServicer(id);
	}

	@Override
	@Transactional
	public CarServicer getCarServicerByName(String username) {
		// TODO Auto-generated method stub
		return carServicerImpl.getCarservicerByName(username);
	}

	@Override
	@Transactional
	public void saveCarServicer(CarServicer carServicer) {
		// TODO Auto-generated method stub
		carServicerImpl.saveCarServicer(carServicer);
	}

	@Override
	@Transactional
	public void deleteCarservicer(int id) {
		// TODO Auto-generated method stub
		carServicerImpl.deleteCarServicer(id);		
	}

	@Override
	@Transactional
	public List<CarServicer> getCarServicers(int pincode) {
		List<CarServicer> carservicers = carServicerImpl.getCarServicers(pincode);
		return carservicers;
	}

	@Override
	@Transactional
	public List<CustomerRequest> getRequests(String email) {
		// TODO Auto-generated method stub
		return customerDaoImpl.getUserRequests(email);
	}

	@Override
	@Transactional
	public void savePlacedRequest(PlacedRequest placedRequest) {
		// TODO Auto-generated method st
		carServicerImpl.savePlacedRequest(placedRequest);
	}

}
