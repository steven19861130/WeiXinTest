package com.wecharttest.bean;

public enum DishTypeEnum {

	type1("冷菜"),
	type2("热菜"),
	type3("甜点"),
	type4("主食"),
	type5("酒水"),
	type6("全部");
	
	private String dishName;
	
	DishTypeEnum(String dishName){
		this.dishName = dishName;
	}
	
	public DishTypeEnum getDishNameByType(String type){
		
		if(type == null){
			return type6;
		}
		for(DishTypeEnum dte: DishTypeEnum.values()){
			if(dte.name().equals(type)){
				return dte;
			}
		}
		return type6;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	
	
}
