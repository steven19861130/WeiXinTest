package com.wecharttest.handler.event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wecharttest.util.DBConnection;

public class UserUnSubscribeHandler {

	
	public String userUnSubscribe(String userName){
		    String retMsg = "";
		    
		    Connection conn = DBConnection.getConnection() ;
		    PreparedStatement ps;
		    try {
				ps = conn.prepareStatement(userUnSubscribeSql());
				ps.setString(1, userName);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    
		    return retMsg;
		    
	}
	
	private String userUnSubscribeSql(){
		String sql = "update user set Subscribed=false where user_name =?";
		return sql;
	}
}
