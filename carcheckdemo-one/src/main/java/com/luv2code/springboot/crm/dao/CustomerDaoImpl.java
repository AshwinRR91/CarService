package com.luv2code.springboot.crm.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.luv2code.springboot.crm.entity.Customer;
import com.luv2code.springboot.crm.entity.CustomerRequest;


@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public void deleteCustomer(int id) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery("delete from customer where id=:customerId");
		query.setParameter("customerId", id);
		query.executeUpdate();
		// TODO Auto-generated method stub
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(customer);
		// TODO Auto-generated method stub

	}

	@Override
	public Customer getCustomer(int id) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		Customer customer = currentSession.get(Customer.class, id);	
		return customer; 
	}
	
	@Override
	public Customer getCustomerByUser(String username) {
		Session currentSession = entityManager.unwrap(Session.class);
		Customer customer = (Customer)currentSession.createQuery("from Customer c where c.email_id=:emailId")
			.setParameter("emailId", username)
			.getSingleResult();
		return customer;
	}

	@Override
	public List<CustomerRequest> getUserRequests(String email) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		Query<CustomerRequest> query = currentSession.createQuery("from CustomerRequest c where c.carServicerEmailId=:email");
		query.setParameter("email", email);
		List<CustomerRequest> requests = query.getResultList();
		return requests;
	}
	
}
