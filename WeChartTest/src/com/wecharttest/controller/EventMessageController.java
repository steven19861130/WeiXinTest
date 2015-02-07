package com.wecharttest.controller;

import java.util.Calendar;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wecharttest.handler.event.UserSubscribeHandler;
import com.wecharttest.handler.event.UserUnSubscribeHandler;
import com.wecharttest.message.ReceiveBaseMessage;
import com.wecharttest.message.ReceiveEventMessage;
import com.wecharttest.message.ReceiveTextMessage;
import com.wecharttest.message.ReturnTextMessage;
import com.wecharttest.util.MessageToXMLUtil;

public class EventMessageController {
  
  private ReceiveEventMessage em;
  
  public EventMessageController(ReceiveEventMessage em){
    this.em = em;
  }
  
  public String returnMessage(){
    String event = em.getEvent();
    String fromUser = null;
    String retMsg = null;
    
    
    if(event.equalsIgnoreCase("subscribe")){
      UserSubscribeHandler ush = new UserSubscribeHandler();
      fromUser = em.getFromUser();
      retMsg = ush.userSubscribe(fromUser);
      return MessageToXMLUtil.strToTextXml(constructTextMessage(retMsg));
    }else if(event.equalsIgnoreCase("unsubscribe")){
    	UserUnSubscribeHandler uush = new UserUnSubscribeHandler();
    	fromUser = em.getFromUser();
    	retMsg = uush.userUnSubscribe(fromUser);
    	return MessageToXMLUtil.strToTextXml(constructTextMessage(retMsg));
    }
    
    return null;
  }
  
  private ReturnTextMessage constructTextMessage(String retMsg){
    ReturnTextMessage tx = new ReturnTextMessage();
    tx.setFromUser(em.getFromUser());
    tx.setToUser(em.getToUser());
    tx.setMsgContent(retMsg);
    tx.setCreateTime(Calendar.getInstance().getTimeInMillis());
    return tx;
  }
  
}
