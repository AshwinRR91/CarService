package com.luv2code.springboot.crm.controller;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.luv2code.springboot.crm.entity.Customer;
import com.luv2code.springboot.crm.entity.CustomerRequest;
import com.luv2code.springboot.crm.service.CustomerServiceImpl;


@Controller
public class CustomerController {
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@GetMapping("/showMyLoginPage")
	public String getString() {
		//Customer customer = customerServiceImpl.getCustomer(1);
		//return customer;
		return "fancy-login";
	}
	
	@GetMapping("/error")
	public String letString() {
		return "sin";
	}
	
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "access-denied";
	}
	
	@RequestMapping(value = "/username", method = RequestMethod.GET)
    public String currentUserName(Principal principal, Model model) {
        String username = principal.getName();    
        Customer customer = customerServiceImpl.getCustomerByUser(username);	
        model.addAttribute("customer", customer);
        return "customer_homepage";
    }
	
	
	@GetMapping("/PlaceRequest")
	public String placeRequest(Model model) {
		CustomerRequest customerRequest = new CustomerRequest();
		model.addAttribute("Request",customerRequest);
		return "awesome";
	}
	
	@GetMapping("/processForm")
	public String createRequest(@ModelAttribute("Request")CustomerRequest customerRequest, Principal principal, Model model) {
		String username = principal.getName();    
        Customer customer = customerServiceImpl.getCustomerByUser(username);	
        customer.addRequest(customerRequest);
        customerServiceImpl.saveCustomer(customer);
        model.addAttribute("customer", customer);
		return "show-request";
	}
	
}
