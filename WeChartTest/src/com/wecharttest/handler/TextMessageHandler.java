package com.wecharttest.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import com.wecharttest.message.TextMessage;
import com.wecharttest.text.MenuCheck;
import com.wecharttest.text.OrderDish;

public class TextMessageHandler {
  
  private TextMessage tm;
  
  public TextMessageHandler(TextMessage tm){
    this.tm = tm;
  }
  
  public String returnMessage(){
    String fromUser = tm.getFromUser();
    String msgContent = tm.getMsgContent();
    
    return strToXml(tm);
  }
  
  private String handleContent(String msgContent,String userName){
    if(msgContent.equalsIgnoreCase("菜单")){
      return new MenuCheck().getMenu();
    }else if(msgContent.equalsIgnoreCase("点菜")){
      return "请根据菜单菜品，按照'我要吃：鱼香肉丝1,青椒肉丝2，番茄鸡蛋汤1'格式发送消息,其中数字代表份数";
    }else if(msgContent.startsWith("我要吃")){
      Map<String,Integer> orderDishes = getOrderDishes(msgContent);
      if(orderDishes == null){
        return "您输入的格式有问题，请按照'我要吃：鱼香肉丝1,青椒肉丝2，番茄鸡蛋汤1'格式发送消息,其中数字代表份数";
      }
      return new OrderDish().order(userName, "我要吃", orderDishes);
    }else if(msgContent.equalsIgnoreCase("点菜结束")){
      return new OrderDish().order(userName, "结束", null);
    }
    else{
      return "please give valid input";
    }
    
  }
  
  private String strToXml(TextMessage tm){
    String returnStr = "";  
    
    SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");  
    String times = format.format(new Date());  
      
    Element rootXML = new Element("xml");  
      
    rootXML.addContent(new Element("ToUserName").setText(tm.getFromUser()));  
    rootXML.addContent(new Element("FromUserName").setText(tm.getToUser()));  
    rootXML.addContent(new Element("CreateTime").setText(times));  
    rootXML.addContent(new Element("MsgType").setText("text"));  
    rootXML.addContent(new Element("Content").setText(this.handleContent(tm.getMsgContent(),tm.getFromUser())));  

    Document doc = new Document(rootXML);   
      
    XMLOutputter XMLOut = new XMLOutputter();    
    returnStr = XMLOut.outputString(doc);  

    return returnStr;  
    
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
}
