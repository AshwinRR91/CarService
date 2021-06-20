package com.luv2code.springboot.crm.carservicer.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "carservicer")
public class CarServicer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "email_id")
	private String emailId;
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Column(name = "car_servicer_name")
	private String carServicerName;
	
	@OneToMany(fetch = FetchType.LAZY, 
			mappedBy = "carServicer", 
						cascade = {CascadeType.DETACH, CascadeType.MERGE, 
								   CascadeType.PERSIST,CascadeType.REFRESH })
	@JsonIgnore
	private List<ServicesOffered> servicesOffered;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "pincode")
	private int pincode;
	
	public CarServicer() {
	}

	public CarServicer(String carServicerName, String address, int pincode, String emailId) {
		this.carServicerName = carServicerName;
		this.address = address;
		this.pincode = pincode;
		this.emailId = emailId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCarServicerName() {
		return carServicerName;
	}

	public void setCarServicerName(String carServicerName) {
		this.carServicerName = carServicerName;
	}

	public List<ServicesOffered> getServicesOffered() {
		return servicesOffered;
	}

	public void setServicesOffered(List<ServicesOffered> servicesOffered) {
		this.servicesOffered = servicesOffered;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	
	public void addServices(ServicesOffered serviceOffered) {
		
		if(this.servicesOffered == null) {
			this.servicesOffered = new ArrayList<ServicesOffered>();
		}
		
		this.servicesOffered.add(serviceOffered);
		serviceOffered.setCarServicer(this);
	}
}
