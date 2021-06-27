package com.luv2code.springboot.crm.controller;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.crm.carservicer.entity.CarServicer;
import com.luv2code.springboot.crm.entity.Customer;
import com.luv2code.springboot.crm.entity.CustomerRequest;
import com.luv2code.springboot.crm.service.CustomerServicerDaoImpl;


@Controller
public class CustomerController {
	
	@Autowired
	private CustomerServicerDaoImpl customerServiceImpl;
	
	@GetMapping("/showMyLoginPage")
	public String getString(Model model) {
		//Customer customer = customerServiceImpl.getCustomer(1);
		//return customer
		return "check-login";
	}
	
	@GetMapping("/customerLogin")
	public String getCustomerLogin() {
		//Customer customer = customerServiceImpl.getCustomer(1);
		//return customer
		return "fancy-login";
	}
	
	@GetMapping("/showMyLoginPageForServicer")
	public String getServicerLogin() {
		//Customer customer = customerServiceImpl.getCustomer(1);
		//return customer
		return "servicer-login";
	}
	
	@GetMapping("/error")
	public String letString() {
		return "sin";
	}
	
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "access-denied";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
    public String currentUserName(Principal principal, Model model) {
        String username = principal.getName();    
        Customer customer = customerServiceImpl.getCustomerByUser(username);
        String pincode="";
        model.addAttribute("customer", customer);      
        model.addAttribute("pincode", pincode);
        return "customer_homepage";
    }
	
	@GetMapping("/carServicers")
	public String getCarServicers(HttpServletRequest httpServletRequest, Model model, Principal principal) {
		String username = principal.getName();    
	    Customer customer = customerServiceImpl.getCustomerByUser(username);   
	    List <String>customerRequest = new ArrayList<String>();
		int pincode = Integer.parseInt(httpServletRequest.getParameter("pincode"));
		List<CarServicer> carservicer = customerServiceImpl.getCarServicers(pincode);
		model.addAttribute("carservicers", carservicer);
		return "car-servicers";
	}

	
	@PostMapping("/placeRequest")
	public String placeRequest(@RequestParam("customerRequest[]")List<String>customerRequest, Principal principal) {
		String username = principal.getName();    
	    Customer customer = customerServiceImpl.getCustomerByUser(username);   
		List<String>request = customerRequest;
		for (String string : request) {
			CustomerRequest customerRequests = new CustomerRequest(string);
			customer.addRequest(customerRequests);
			customerServiceImpl.saveCustomer(customer);
		}
		return "redirect:/home";
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
