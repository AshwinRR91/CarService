package com.luv2code.springboot.crm.carservicer.CarServicerDao;

import java.util.List;

import com.luv2code.springboot.crm.carservicer.entity.CarServicer;
import com.luv2code.springboot.crm.carservicer.entity.PlacedRequest;

public interface CarServicerDao {
	
	public List<CarServicer> getCarServicers(int pincode);
	
	public CarServicer getCarServicer(int id);
	
	public void deleteCarServicer(int id);
	
	public void saveCarServicer(CarServicer carServicer);
	
	public CarServicer getCarservicerByName(String name);
	
	public void savePlacedRequest(PlacedRequest placedRequest);
	
}
