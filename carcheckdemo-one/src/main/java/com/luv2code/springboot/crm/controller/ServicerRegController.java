package com.luv2code.springboot.crm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springboot.crm.CrmUser.LoginServicer;
import com.luv2code.springboot.crm.carservicer.entity.CarServicer;
import com.luv2code.springboot.crm.service.CustomerServicerDaoImpl;

@Controller
@RequestMapping("/serviceregister")
public class ServicerRegController {
	
	@Autowired
	private UserDetailsManager userDetailsManager;
	
	private CarServicer carservicer;
	
	@Autowired
	private CustomerServicerDaoImpl customerServiceImpl;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/showRegistrationForms")
	public String registerForm(Model model) {
		LoginServicer loginServicer = new LoginServicer();
		model.addAttribute("loginServicer", loginServicer);
		return "service-registration-form";
	}
	
	@PostMapping("/processRegistrationForm")
	public String processRegistration(@Valid @ModelAttribute("loginServicer")LoginServicer loginServicer, 
								BindingResult bindingResult,
								Model model) {
		
		String userName = loginServicer.getUsername();
		if(bindingResult.hasErrors()) {
			model.addAttribute("loginServicer", loginServicer);
			model.addAttribute("registrationError", "username or passoword cannot be empty");
			return "service-registration-form";
		}
		if(doesUserExist(userName)) {
			model.addAttribute("loginServicer", loginServicer);
			model.addAttribute("registrationError", "username Exists");
			return "service-registration-form";
		}
		String emailId = loginServicer.getUsername();
		String carServicerName= loginServicer.getCarServicerName();
		String address = loginServicer.getAddress();
		int pincode = loginServicer.getPincode();
		carservicer = new CarServicer(carServicerName,address, pincode, emailId);
		customerServiceImpl.saveCarServicer(carservicer);
		String password = "{bcrypt}"+passwordEncoder.encode(loginServicer.getPassword());
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList("ROLE_SERVICER");
		User user = new User(emailId, password, grantedAuthorities);
		userDetailsManager.createUser(user);
		return "registration-confirmation";
	}
	
	public boolean doesUserExist(String username) {
		return userDetailsManager.userExists(username);
	}

}
