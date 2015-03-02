package com.wecharttest.handler.event;

import org.apache.log4j.Logger;

import com.wecharttest.dao.subscribe.UserUnSubscribeDAO;

public class UserUnSubscribeHandler {

	private Logger log = Logger.getLogger(UserUnSubscribeHandler.class);

	private String instance;

	private String userName;

	private UserUnSubscribeDAO uusDAO;

	public UserUnSubscribeHandler(String instance, String userName) {
		this.instance = instance;
		this.userName = userName;
		uusDAO = new UserUnSubscribeDAO();
	}

	public String userUnSubscribe() {
		String retMsg = uusDAO.userUnSubscribe(instance, userName);
		log.info("User " + userName + " unsubscribe " + instance);
		return retMsg;
	}
}
