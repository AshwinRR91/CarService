package com.luv2code.springboot.crm.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.crm.entity.Customer;
import com.luv2code.springboot.crm.service.CustomerServiceImpl;

@RestController
@RequestMapping("/new")
public class RequestController {
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@GetMapping("/awesome")
	public Customer returnCust(Principal principal) {
		String username = principal.getName();    
        Customer customer = customerServiceImpl.getCustomerByUser(username);	
        return customer;
	}

}
