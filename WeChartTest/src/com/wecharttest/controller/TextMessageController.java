package com.wecharttest.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.wecharttest.handler.text.MenuCheckHandler;
import com.wecharttest.handler.text.OrderDishHandler;
import com.wecharttest.message.ReceiveTextMessage;
import com.wecharttest.message.ReturnTextMessage;
import com.wecharttest.util.*;

public class TextMessageController {
  
  private ReceiveTextMessage tm;
  
  private Logger log = Logger.getLogger(TextMessageController.class);

  
  public TextMessageController(ReceiveTextMessage tm){
    this.tm = tm;
  }
  
  public String returnMessage(){

	  ReturnTextMessage rtm = constructTextMessage(handleContent(tm.getMsgContent(), tm.getFromUser()));
    
    return MessageToXMLUtil.strToTextXml(rtm);
  }
  
  private String handleContent(String msgContent,String userName){
    if(msgContent.equalsIgnoreCase("菜单")){
      return new MenuCheckHandler().getMenu();
    }else if(msgContent.equalsIgnoreCase("点菜")){
      return Constants.ORDERMSG;
    }else if(msgContent.startsWith("我要吃")){
      Map<String,Integer> orderDishes = getOrderDishes(msgContent);
      if(orderDishes == null){
        log.info("User did not order anything");
        return Constants.INPUTERR+Constants.ORDERMSG;
      }
      return new OrderDishHandler().order(userName, "我要吃", orderDishes);
    }else if(msgContent.equalsIgnoreCase("点菜结束")){
      return new OrderDishHandler().order(userName, "点菜结束", null);
    }
    else{
      log.info("Input invalid");	
      return Constants.INPUTERR;
    }
    
  }
  
  private Map<String,Integer> getOrderDishes(String msgContent){
    Map<String,Integer> dishMap = new HashMap<String,Integer>();
    String[] dishStrings = msgContent.split("：");
    String title = dishStrings[0];
    if(!title.equalsIgnoreCase("我要吃")){
    	return null;
    }
    String[] dishes = dishStrings[1].split("，");
    for(String dish:dishes){
      String dishCount = getCount(dish);
      if(dishCount == null || dishCount.isEmpty()){
        return null;
      }
      String dishName = dish.substring(0,dish.length()-dishCount.length());
      dishMap.put(dishName, Integer.valueOf(dishCount));
    }
    return dishMap;
  }
  
  private String getCount(String dish){
    String regEx="[^0-9]";   
    Pattern p = Pattern.compile(regEx);   
    Matcher m = p.matcher(dish);
    return m.replaceAll("").trim();
  }
  
  private ReturnTextMessage constructTextMessage(String retMsg){
	    ReturnTextMessage tx = new ReturnTextMessage();
	    tx.setFromUser(tm.getFromUser());
	    tx.setToUser(tm.getToUser());
	    tx.setMsgContent(retMsg);
	    tx.setCreateTime(Calendar.getInstance().getTimeInMillis());
	    return tx;
	  }
}
