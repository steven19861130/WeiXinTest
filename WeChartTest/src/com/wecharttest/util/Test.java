package com.wecharttest.util;

import com.wecharttest.bean.DishBean;

public class Test {

	
	public static void main(String[] args){
		JsonUtil ju = new JsonUtil();
		DishBean db = new DishBean();
		
		db.setDishName("水煮肉片");
		db.setDishDesc("非常好吃");
		db.setCount(2);
		db.setPrice(12);
		
		String jsonString = ju.bean2json(db);
		
		System.out.println(jsonString);
	}
}
