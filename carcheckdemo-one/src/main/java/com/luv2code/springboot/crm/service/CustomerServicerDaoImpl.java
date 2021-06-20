
package com.luv2code.springboot.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.crm.carservicer.CarServicerDao.CarServicerImpl;
import com.luv2code.springboot.crm.carservicer.entity.CarServicer;
import com.luv2code.springboot.crm.dao.CustomerDaoImpl;
import com.luv2code.springboot.crm.entity.Customer;

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

}
