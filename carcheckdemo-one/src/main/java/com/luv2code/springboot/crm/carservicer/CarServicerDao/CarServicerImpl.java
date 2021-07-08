package com.luv2code.springboot.crm.carservicer.CarServicerDao;


import java.util.List;

import javax.persistence.EntityManager;


import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.crm.carservicer.entity.CarServicer;
import com.luv2code.springboot.crm.carservicer.entity.PlacedRequest;
@Repository	
public class CarServicerImpl implements CarServicerDao {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public CarServicer getCarServicer(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		CarServicer carservicer = currentSession.get(CarServicer.class, id);
		return carservicer;
	}

	@Override
	public void deleteCarServicer(int id) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery("from CarServicer delete where id=:id1");
		query.setParameter("id1", id);
		query.executeUpdate();
	}

	@Override
	public void saveCarServicer(CarServicer carServicer) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(carServicer);
	}

	@Override
	public CarServicer getCarservicerByName(String emailId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<CarServicer> query = currentSession.createQuery("from CarServicer c where c.emailId=:emailId");
		query.setParameter("emailId", emailId);
		CarServicer carServicer = query.getSingleResult();
		return carServicer;
	}

	@Override
	public List<CarServicer> getCarServicers(int pincode) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<CarServicer> query =  currentSession.createQuery("from CarServicer s where s.pincode=:pincode");
		query.setParameter("pincode", pincode);
		List<CarServicer> carservicers = query.getResultList();
		return carservicers;
	}

	@Override
	public void savePlacedRequest(PlacedRequest placedRequest) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(placedRequest);
	}
}
