package com.luv2code.springboot.crm.CrmUser;

public class LoginServicer {
	
	private String username;
	
	private String password;
	
	private int pincode;
	
	private String carServicerName;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCarServicerName() {
		return carServicerName;
	}

	public void setCarServicerName(String carServicerName) {
		this.carServicerName = carServicerName;
	}

	private String address;

	public LoginServicer() {
	}

	public LoginServicer(String username, int pincode, String address, String carServicerName) {
		this.username = username;
		this.pincode = pincode;
		this.address = address;
		this.carServicerName = carServicerName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
