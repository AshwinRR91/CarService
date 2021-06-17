
package com.luv2code.springboot.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.luv2code.springboot.crm.entity.Customer;
import com.luv2code.springboot.crm.dao.CustomerDaoImpl;



@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDaoImpl customerDaoImpl;
	
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

}
