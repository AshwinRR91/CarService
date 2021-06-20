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
@Table(name = "services_offered")
public class ServicesOffered {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "service_offered")
	private String request;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH })
	@JoinColumn(name="carservicer_id")
	private CarServicer carServicer;

	public ServicesOffered() {
	}

	public ServicesOffered(String request) {
		this.request = request;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public CarServicer getCarServicer() {
		return carServicer;
	}

	public void setCarServicer(CarServicer carServicer) {
		this.carServicer = carServicer;
	}

	
}
