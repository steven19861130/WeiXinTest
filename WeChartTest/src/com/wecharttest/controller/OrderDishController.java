package com.wecharttest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.wecharttest.bean.DishBean;
import com.wecharttest.handler.client.dish.GetDishHandler;
import com.wecharttest.util.InstanceUtil;

public class OrderDishController {

	private String instance = null;
	
	private Logger log = Logger.getLogger(OrderDishController.class);
	private GetDishHandler gad ;
	
	public OrderDishController(String weiXinId){
		this.instance = InstanceUtil.instanceMap.get(weiXinId);
		this.gad = new GetDishHandler();
	}
	
	public List<DishBean> GetDishes(Set<String> dishes){
		List<DishBean> dbs = new ArrayList<DishBean>();
		dbs = gad.getDishes(instance,dishes);
		return dbs;
	}
	
	public List<DishBean> getAllDish(){
		List<DishBean> dbs = new ArrayList<DishBean>();
		dbs = gad.getAllDish(instance);
		return dbs;
	}
}
