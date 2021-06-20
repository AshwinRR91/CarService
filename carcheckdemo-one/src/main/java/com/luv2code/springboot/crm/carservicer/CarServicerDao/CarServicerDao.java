package com.luv2code.springboot.crm.carservicer.CarServicerDao;

import java.util.List;

import com.luv2code.springboot.crm.carservicer.entity.CarServicer;

public interface CarServicerDao {
	
	public CarServicer getCarServicer(int id);
	
	public void deleteCarServicer(int id);
	
	public void saveCarServicer(CarServicer carServicer);
	
	public CarServicer getCarservicerByName(String name);
	
}
