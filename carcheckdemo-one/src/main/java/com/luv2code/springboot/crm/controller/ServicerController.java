package com.luv2code.springboot.crm.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springboot.crm.carservicer.entity.CarServicer;
import com.luv2code.springboot.crm.service.CustomerServicerDaoImpl;

@Controller
@RequestMapping("/servicer")
public class ServicerController {

	@Autowired
	private CustomerServicerDaoImpl customerServicerImpl;
	
	@GetMapping("/showMyRequests")
	public String getPage(Principal principal, Model model) {
		String emailId = principal.getName();
		CarServicer carServicer = customerServicerImpl.getCarServicerByName(emailId);
		model.addAttribute("carServicer", carServicer);
		return "servicer-homepage";
	}
	
}
