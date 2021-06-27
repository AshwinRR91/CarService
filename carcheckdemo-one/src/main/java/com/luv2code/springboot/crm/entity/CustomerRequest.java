
package com.luv2code.springboot.crm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "user_request")
public class CustomerRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "request_id")
	private int requestId;
	
	@Column(name = "req")
	private String request;
	
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH })
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	public String getCarServicerEmailId() {
		return carServicerEmailId;
	}

	public void setCarServicerEmailId(String carServicerEmailId) {
		this.carServicerEmailId = carServicerEmailId;
	}

	@Column(name="carservicer_email")
	private String carServicerEmailId;
	
	@Column(name="status")
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CustomerRequest(String request) {
		this.request = request;
	}

	public CustomerRequest() {
			}
	
	public int getRequestId() {
		return requestId;
	}
	
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	
	public String getRequest() {
		return request;
	}
	
	public void setRequest(String request) {
		this.request = request;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
