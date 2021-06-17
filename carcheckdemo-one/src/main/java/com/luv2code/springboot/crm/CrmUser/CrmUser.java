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
	/*
	@NotNull
	@Size(min= 1 ,message = "is Required")
	String firstName;
	
	@NotNull
	@Size(min= 1 ,message = "is Required")
	String last_Name;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLast_Name() {
		return last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}
*/
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
