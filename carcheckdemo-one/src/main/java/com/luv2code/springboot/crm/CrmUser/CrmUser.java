package com.luv2code.springboot.crm.CrmUser;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CrmUser {
	@NotNull
	@Size(min= 1 ,message = "is Required")
	private String userName;
	
	@NotNull
	@Size(min= 1 ,message = "is Required")
	private String password;
	
	@NotNull
	@Size(min= 1 ,message = "is Required")
	String firstName;
	
	@NotNull
	@Size(min= 1 ,message = "is Required")
	String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public CrmUser() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

		
}
