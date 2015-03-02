package com.wecharttest.handler.event;

import org.apache.log4j.Logger;

import com.wecharttest.dao.subscribe.UserSubscribeDAO;

public class UserSubscribeHandler {
  
	private Logger log = Logger.getLogger(UserSubscribeHandler.class);
	
	private String instance;
	
	private String userName;
	
	private UserSubscribeDAO usDAO;
	
	public UserSubscribeHandler(String instance, String userName){
		this.instance = instance;
		this.userName = userName;
		usDAO = new UserSubscribeDAO();
	}
 
	public String userSubscribe(){
		String retMsg = usDAO.userSubscribe(instance, userName);
		log.info("User "+userName+" subscribe "+instance);
		return retMsg;
	}
}
