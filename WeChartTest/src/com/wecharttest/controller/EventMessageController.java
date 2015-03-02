package com.wecharttest.controller;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;

import com.wecharttest.handler.event.UserSubscribeHandler;
import com.wecharttest.handler.event.UserUnSubscribeHandler;
import com.wecharttest.message.ReceiveBaseMessage;
import com.wecharttest.message.ReceiveEventMessage;
import com.wecharttest.message.ReceiveTextMessage;
import com.wecharttest.message.ReturnTextMessage;
import com.wecharttest.util.InstanceUtil;
import com.wecharttest.util.MessageToXMLUtil;

public class EventMessageController {
  
  private ReceiveEventMessage em;
  
  private String instance;
  
  private Logger log = Logger.getLogger(EventMessageController.class);
  
  public EventMessageController(ReceiveEventMessage em){
    this.em = em;
    instance = InstanceUtil.instanceMap.get(em.getToUser());
  }
  
  public String returnMessage(){
    String event = em.getEvent();
    String fromUser = null;
    String retMsg = null;
    
    
    if(event.equalsIgnoreCase("subscribe")){
      log.info("Subscribe event received");
      fromUser = em.getFromUser();
      UserSubscribeHandler ush = new UserSubscribeHandler(instance,fromUser);
      retMsg = ush.userSubscribe();
      return MessageToXMLUtil.strToTextXml(constructTextMessage(retMsg));
    }else if(event.equalsIgnoreCase("unsubscribe")){
    	log.info("Unsubscribe event received");	
    	fromUser = em.getFromUser();
    	UserUnSubscribeHandler uush = new UserUnSubscribeHandler(instance,fromUser);
    	retMsg = uush.userUnSubscribe();
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
