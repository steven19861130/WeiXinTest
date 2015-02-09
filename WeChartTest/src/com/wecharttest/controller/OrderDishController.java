package com.wecharttest.controller;

import java.util.ArrayList;
import java.util.List;

import com.wecharttest.bean.DishBean;
import com.wecharttest.handler.client.GetAllDish;
import com.wecharttest.util.InstanceUtil;

public class OrderDishController {

	private String weiXinId;
	
	public OrderDishController(String weiXinId){
		this.weiXinId = weiXinId;
	}
	
	public List<DishBean> getAllDish(){
		List<DishBean> dbs = new ArrayList<DishBean>();
		GetAllDish gad = new GetAllDish();
		dbs = gad.getAllDish(InstanceUtil.instanceMap.get(weiXinId));
		return dbs;
	}
}
