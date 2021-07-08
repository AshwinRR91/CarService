package com.luv2code.springboot.crm.entity;

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
@Table(name = "customer")
public class Customer {
	
	@Column(name = "email_id")
	private String email_id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@OneToMany(fetch = FetchType.LAZY, 
			mappedBy = "customer", 
						cascade = {CascadeType.DETACH, CascadeType.MERGE, 
								   CascadeType.PERSIST,CascadeType.REFRESH })
	@JsonIgnore
	private List<CustomerRequest> customerRequests;
	
	public Customer() {
		
	}
	
	public List<CustomerRequest> getCustomerRequests() {
		return customerRequests;
	}

	public void setCustomerRequests(List<CustomerRequest> customerRequests) {
		this.customerRequests = customerRequests;
	}

	public Customer(String firstName, String lastName, String email_id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email_id = email_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
	
	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	
	public void addRequest(CustomerRequest customerRequest) {
		if(customerRequests == null) {
			customerRequests = new ArrayList<CustomerRequest>();
		}
		customerRequests.add(customerRequest);
		customerRequest.setCustomer(this);
	}
	
	public List<CustomerRequest> getCustomerRequestsbyEmail(String carServicerEmail) {
		List<CustomerRequest> customerByServicer = null;
		for(CustomerRequest customerRequest :  this.customerRequests) {
		if (customerRequest.getCarServicerEmailId().equals(carServicerEmail)){
			customerByServicer.add(customerRequest);
			}
		}
		return customerByServicer;
	}
}
