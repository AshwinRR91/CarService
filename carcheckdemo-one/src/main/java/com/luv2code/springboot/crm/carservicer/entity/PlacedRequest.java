package com.luv2code.springboot.crm.carservicer.entity;

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
@Table(name = "placed_request")
public class PlacedRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "customer_email")
	private String customerEmail;
	
	public PlacedRequest(String customerEmail, int requestId, String request, String status) {
		this.customerEmail = customerEmail;
		this.requestId = requestId;
		this.request = request;
		this.status = status;
	}

	@Column(name = "request_id")
	private int requestId;
	
	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "request")
	private String request;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "transaction_amount")
	private Integer transactionAmount;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH })
	@JoinColumn(name="carservicer_id")
	private CarServicer carServicers;

	public int getRequestId() {
		return requestId;
	}

	public CarServicer getCarServicers() {
		return carServicers;
	}

	public void setCarServicers(CarServicer carServicers) {
		this.carServicers = carServicers;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Integer transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public PlacedRequest() {
	}

	
}
