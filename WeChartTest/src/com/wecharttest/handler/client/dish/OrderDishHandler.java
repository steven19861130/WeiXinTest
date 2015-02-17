package com.wecharttest.handler.client.dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wecharttest.bean.DishBean;
import com.wecharttest.dao.dish.*;


public class OrderDishHandler {
	
	private String instance;
	
	private OrderDishDAO orderDishDAO;
	

	public OrderDishHandler(String instance){
		this.instance = instance;
		this.orderDishDAO = new OrderDishDAO();
	}
	
	
	public void addOderDish(String instance, List<DishBean> dbs, String userName){
		orderDishDAO.addOrderDish(instance, userName, dbs);
	}
	
	public List<DishBean> getAllOrderedAndNoCheckDish(String userName){
		List<DishBean> dbs = new ArrayList<DishBean>();
		GetDishHandler gdh = new GetDishHandler();
		Map<String,Integer> dishNames = orderDishDAO.getAllOrderedAndNoCheckDish(instance, userName);
		dbs = gdh.getDishes(instance, dishNames.keySet());
	    return dbs;
	}
	
	public List<DishBean> getAllCheckedDish(String userName){
		List<DishBean> dbs = new ArrayList<DishBean>();
		GetDishHandler gdh = new GetDishHandler();
		Map<String,Integer> dishNames = orderDishDAO.getAllCheckedDish(instance, userName);
		dbs = gdh.getDishes(instance, dishNames.keySet());
	    return dbs;
	}
}
