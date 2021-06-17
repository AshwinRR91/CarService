package com.luv2code.springboot.crm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
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

import com.luv2code.springboot.crm.CrmUser.CrmUser;
import com.luv2code.springboot.crm.service.CustomerServiceImpl;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private UserDetailsManager userDetailsManager;
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/showRegistrationForms")
	public String registerForm(Model model) {
		
		CrmUser crmUser = new CrmUser();
		model.addAttribute("crmUser", crmUser);
		return "registration-form";
	}
	
	@PostMapping("/processRegistrationForm")
	public String processRegistration(@Valid @ModelAttribute("crmUser")CrmUser crmUser, 
								BindingResult bindingResult,
								Model model) {
		
		String userName = crmUser.getUserName();
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("crmUser", crmUser);
			model.addAttribute("registrationError", "username or passoword cannot be empty");
			return "registration-form";
		}
		if(doesUserExist(userName)) {
			model.addAttribute("crmUser", crmUser);
			model.addAttribute("registrationError", "username Exists");
			return "registration-form";
		}
		
		String password = "{bcrypt}"+passwordEncoder.encode(crmUser.getPassword());
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE");
		User user = new User(userName, password, grantedAuthorities);
		userDetailsManager.createUser(user);
		return "registration-confirmation";
	}
	
	public boolean doesUserExist(String username) {
		return userDetailsManager.userExists(username);
	}


}
