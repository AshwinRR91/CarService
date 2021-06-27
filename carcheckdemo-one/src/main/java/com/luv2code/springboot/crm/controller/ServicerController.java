package com.luv2code.springboot.crm.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springboot.crm.carservicer.entity.CarServicer;
import com.luv2code.springboot.crm.carservicer.entity.ServicesOffered;
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
		model.addAttribute("carServicer", carServicer);
		return "servicer-homepage";
	}
	
	
	@GetMapping("/addServicesOffered")
	public String addServicesOffered(Model model) {
		servicesOffered = new ServicesOffered();
		model.addAttribute("serviceOffered", servicesOffered);
		return "add-service-form";
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
	
}
