package com.wecharttest.handler.event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.wecharttest.util.*;

public class UserSubscribeHandler {
  
  public String userSubscribe(String userName){
    boolean isSubscribed;
    String retMsg;
    
    Connection conn = DBConnection.getConnection() ;
    PreparedStatement ps;
    ResultSet rs;
    
    try {
      ps = conn.prepareStatement(isSubscribedSql());
      ps.setString(1, userName);
      rs = ps.executeQuery();
      isSubscribed = rs.next();
      
      if(isSubscribed){
        ps = conn.prepareStatement(updateSunscribedSql());
        ps.setLong(1, Calendar.getInstance().getTimeInMillis());
        ps.setString(2, userName);
        ps.executeUpdate();
        return Constants.WELCOMERESUBCRIBEMSG;
      }else{
        ps = conn.prepareStatement(insertSunscribedSql());
        ps.setString(1, userName);
        ps.setLong(2, Calendar.getInstance().getTimeInMillis());
        ps.executeUpdate();
        return Constants.WELCOMESUBCRIBEMSG;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Constants.USERERORMSG;
    
    
  }
  
  private String isSubscribedSql(){
    String sql = "select * from user where user_name = ?";
    return sql;
  }
  
  private String updateSunscribedSql(){
    String sql = "update user set subscribed = true, user_last_login = ? where user_name = ?";
    return sql;
  }
  
  private String insertSunscribedSql(){
    String sql = "insert into user values(?,?,true)";
    return sql;
  }
}
