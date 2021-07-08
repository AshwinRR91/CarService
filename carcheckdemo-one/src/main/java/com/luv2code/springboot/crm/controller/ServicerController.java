package com.luv2code.springboot.crm.controller;

import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServlet;
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
import com.luv2code.springboot.crm.carservicer.entity.ServicesOffered;
import com.luv2code.springboot.crm.entity.Customer;
import com.luv2code.springboot.crm.entity.CustomerRequest;
import com.luv2code.springboot.crm.service.CustomerServicerDaoImpl;

@Controller
@RequestMapping("/servicer")
public class ServicerController {

	@Autowired
	private CustomerServicerDaoImpl customerServicerImpl;
	private CarServicer carServicer;
	private ServicesOffered servicesOffered;
	
	@GetMapping("/home")
	public String getPage(Principal principal1, Model model) {
		String emailId = principal1.getName();
		carServicer = customerServicerImpl.getCarServicerByName(emailId);
		List<CustomerRequest> customerRequest = customerServicerImpl.getRequests(emailId);
		model.addAttribute("carServicer", carServicer);
		model.addAttribute("customerRequest", customerRequest);
		return "servicer-homepage";
		
	}
	
	
	@GetMapping("/addServicesOffered")
	public String addServicesOffered(Model model) {
		servicesOffered = new ServicesOffered();
		model.addAttribute("serviceOffered", servicesOffered);
		return "add-service-form";
	}
	
	@GetMapping("/requests")
	public String getRequests(Model model, Principal principal) {
		CarServicer carservicer = customerServicerImpl.getCarServicerByName(principal.getName());
		model.addAttribute("carservicer", carservicer);
		return "requests";
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.POST, params = "accept")
	public String getcheck(	@ModelAttribute("carservicer")CarServicer carservicer,HttpServletRequest request, Model model, Principal principal) {		
	
		/*List<PlacedRequest> request = carservicer.getPlacedRequest();
		for (PlacedRequest placedRequest : request) {
			placedRequest.setCarServicer(carservicer);
			customerServicerImpl.savePlacedRequest(placedRequest);
		}*/
		String status = "ACCEPTED";
		System.out.println(status);
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				updateServicerRequest(principal.getName(), carservicer, status);
			}
		}).run();
		model.addAttribute("status",status);
		return "awesome";
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.POST, params = "decline")
	public String getsheck(	@ModelAttribute("carservicer")CarServicer carservicer,HttpServletRequest request, Model model, Principal principal) {		
	
		/*List<PlacedRequest> request = carservicer.getPlacedRequest();
		for (PlacedRequest placedRequest : request) {
			placedRequest.setCarServicer(carservicer);
			customerServicerImpl.savePlacedRequest(placedRequest);
		}*/
		String status ="DECLINED";
		System.out.println(status);
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				updateServicerRequest(principal.getName(), carservicer, status);
			}
		}).run();
		model.addAttribute("status",status);
		return "awesome";
	}
	
	private void updateServicerRequest(String name, CarServicer carservicer2, String status) {
		CarServicer carservicer1 = customerServicerImpl.getCarServicerByName(name);
		System.out.println(status);
		PlacedRequest placedRequest2 = carservicer2.getPlacedRequest().get(0);
		List<PlacedRequest> placedRequests1 = carservicer1.getPlacedRequest();
		int i = 0;
		PlacedRequest placedRequest = null;
 		 for(int j = 0; j< placedRequests1.size();j++)	{
 			placedRequest  = placedRequests1.get(i); 
 			if(placedRequest.getRequestId()== placedRequest2.getRequestId()) {
 				if(status.equals("ACCEPTED")) {
 					placedRequest.setStatus(placedRequest2.getStatus());
 				}
 				else if (status.equals("DECLINED")) {
 					placedRequest.setStatus("DECLINED");
				} 
 				placedRequest.setTransactionAmount(placedRequest2.getTransactionAmount());
 				placedRequests1.add(placedRequest);
 				break;
 			}
 			i++;
		}
 		carservicer1.setPlacedRequest(placedRequests1);
 		updateCustomerRequest(placedRequest2, carservicer1.getEmailId(), status);				
 		customerServicerImpl.saveCarServicer(carservicer1);
	}


                

	private void updateCustomerRequest(PlacedRequest placedRequests1, String emailId, String status) {
		Customer customer = customerServicerImpl.getCustomerByUser(placedRequests1.getCustomerEmail());
		List<CustomerRequest> customerRequests = customer.getCustomerRequests();
		PlacedRequest placedRequest = placedRequests1;
		CustomerRequest customerRequest =null;
		for(int i=0; i<customerRequests.size(); i++) {
		 if(customerRequests.get(i).getRequestId()==placedRequest.getRequestId()) {
			customerRequest = customerRequests.get(i);
			if(status.equals("ACCEPTED")) {
				customerRequest.setStatus(placedRequest.getStatus());
			}
			else {
				customerRequest.setStatus("DECLINED");
			}
			customerRequest.setEstimateAmount(placedRequest.getTransactionAmount());
			customerRequests.add(customerRequest);
			break;
		 }
		}
		customer.setCustomerRequests(customerRequests);
		customerServicerImpl.saveCustomer(customer);
	/*	for(int j = 0; j<placedRequests1.size(); j++) {
			placedRequest = placedRequests1.get(j);
			customer = customerServicerImpl.getCustomerByUser(placedRequest.getCustomerEmail());
			customerRequests = customer.getCustomerRequests();
			CustomerRequest customerRequest = null;
			for(int i =0; i< customerRequests.size(); i++) {
				customerRequest= customerRequests.get(i);
				if(customerRequest.getCarServicerEmailId().equals(emailId)) {
					customerRequest.setStatus(placedRequest.getStatus());
					customerRequests.add(customerRequest);
				}
			}
		}
		customer.setCustomerRequests(customerRequests);
		customerServicerImpl.saveCustomer(customer);
		*/
	}


	@PostMapping("/addService")
	public String addService(@ModelAttribute("serviceOffered")ServicesOffered serviceOffered, Principal principal1) {
		String emailId = principal1.getName();
		carServicer = customerServicerImpl.getCarServicerByName(emailId);
		ServicesOffered serviceOffered1 = serviceOffered;
		carServicer.addServices(serviceOffered1);
		customerServicerImpl.saveCarServicer(carServicer);
		return "redirect:/servicer/home";
	}
	
	public List<CustomerRequest> getCustomerRequest(String email){
		return customerServicerImpl.getRequests(email);
	}
	
}
