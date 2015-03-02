package com.wecharttest.dao.subscribe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.wecharttest.util.Constants;
import com.wecharttest.util.DBConnection;

public class UserSubscribeDAO {

	private Logger log = Logger.getLogger(UserSubscribeDAO.class);

	 public String userSubscribe(String instance, String userName){
		    boolean isSubscribed;
		    Connection conn = DBConnection.getConnection() ;
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    try {
		      ps = conn.prepareStatement(isSubscribedSql(instance));
		      ps.setString(1, userName);
		      rs = ps.executeQuery();
		      isSubscribed = rs.next();
		      
		      if(isSubscribed){
		        ps = conn.prepareStatement(updateSunscribedSql(instance));
		        ps.setLong(1, Calendar.getInstance().getTimeInMillis());
		        ps.setString(2, userName);
		        ps.executeUpdate();
		        return Constants.WELCOMERESUBCRIBEMSG;
		      }else{
		        ps = conn.prepareStatement(insertSunscribedSql(instance));
		        ps.setString(1, userName);
		        ps.setLong(2, Calendar.getInstance().getTimeInMillis());
		        ps.executeUpdate();
		        return Constants.WELCOMESUBCRIBEMSG;
		      }
		    } catch (SQLException e) {
		    	log.error("user subscribe db error", e);
		      e.printStackTrace();
		    }finally{
		    	DBConnection.closeConnection(ps, conn, rs);
		    }
		    return Constants.USERERORMSG;
		    
		    
		  }
		  
		  private String isSubscribedSql(String instance){
		    String sql = "select * from "+instance+".user where user_name = ?";
		    return sql;
		  }
		  
		  private String updateSunscribedSql(String instance){
		    String sql = "update "+instance+".user set subscribed = true, user_last_login = ? where user_name = ?";
		    return sql;
		  }
		  
		  private String insertSunscribedSql(String instance){
		    String sql = "insert into "+instance+".user values(?,?,true)";
		    return sql;
		  }
}
