package com.luv2code.springboot.crm.controller;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.luv2code.springboot.crm.carservicer.entity.PlacedRequest;
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
		Customer customer = getCustomer(principal.getName());	
        List<CustomerRequest> customerRequests = getRequests(customer);
        model.addAttribute("requests", customerRequests);
        String pincode="";
        model.addAttribute("customer", customer);      
        model.addAttribute("pincode", pincode);
        return "customer_homepage";
    }
	
	@GetMapping("/carServicers")
	public String getCarServicers(HttpServletRequest httpServletRequest, Model model, Principal principal) {
		Customer customer = getCustomer(principal.getName());	   
	    List <String>customerRequest = new ArrayList<String>();
		int pincode = Integer.parseInt(httpServletRequest.getParameter("pincode"));
		List<CarServicer> carservicer = customerServiceImpl.getCarServicers(pincode);
		model.addAttribute("carservicers", carservicer);
		return "car-servicers";
	}

	
	@PostMapping("/placeRequest")
	public String placeRequest(@RequestParam("customerRequest[]")List<String>customerRequest,
								@RequestParam("servicerEmail")String servicerEmail,
								@RequestParam("status")String status,
								@RequestParam("carServicerId") int id,
								Principal principal) {
		Customer customer = getCustomer(principal.getName());	   
		List<String>request = customerRequest;
		for (String string : request) {
			CustomerRequest customerRequests = new CustomerRequest("NO",string,servicerEmail,status);
			customer.addRequest(customerRequests);
		}
		customerServiceImpl.saveCustomer(customer);
		updatePlacedRequest(principal.getName(),servicerEmail);
		return "redirect:/home";
	}
	
	private void updatePlacedRequest(String custEmail, String email) {
		Customer customer = getCustomer(custEmail);
		String status = null;
		String request = null;
		CarServicer carservicer = customerServiceImpl.getCarServicerByName(email);
		PlacedRequest placedRequest;	
		CustomerRequest customerRequest = null;
		int requestId;
		List<CustomerRequest> customerRequests = customer.getCustomerRequests();
		for(int i = 0; i<customerRequests.size();i++) {
			customerRequest = customerRequests.get(i);
			if(customerRequest.getPosted().equals("NO")) {
				status = customerRequest.getStatus();
				request = customerRequest.getRequest();
				requestId = customerRequest.getRequestId();
				placedRequest = new PlacedRequest(customer.getEmail_id(),requestId, request, status);
				carservicer.addPlacedRequest(placedRequest);
				customerRequest.setPosted("YES");
				customerRequests.add(customerRequest);
			}
		}
		customer.setCustomerRequests(customerRequests);
		customerServiceImpl.saveCustomer(customer);
		customerServiceImpl.saveCarServicer(carservicer);
	}

	
	@GetMapping("/yourRequest")
	public String senrequest(Principal principal, Model model) {
        Customer customer = getCustomer(principal.getName());	
        List<CustomerRequest> customerRequests = getRequests(customer);
        model.addAttribute("requests", customerRequests);
		return "show-request";
	}
	
	
	public List<CustomerRequest> getRequests(Customer customer){
		return customer.getCustomerRequests();
	}
	
	public Customer getCustomer(String name) {
		return customerServiceImpl.getCustomerByUser(name);
	}
}
