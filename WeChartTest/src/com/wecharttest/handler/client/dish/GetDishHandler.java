package com.wecharttest.handler.client.dish;

import java.util.List;
import java.util.Set;

import com.wecharttest.bean.DishBean;
import com.wecharttest.dao.dish.GetDishDAO;

public class GetDishHandler {

	private GetDishDAO getDishDAO; 
	
	public GetDishHandler(){
		getDishDAO = new GetDishDAO();
	}
	
	public List<DishBean> getAllDish(String instance){
	return getDishDAO.getAllDish(instance);
	}
	
	public List<DishBean> getDishes(String instance,Set<String> dishes){
	return getDishDAO.getDishes(instance,dishes);
	}
	
	
	
	
	
	
}
