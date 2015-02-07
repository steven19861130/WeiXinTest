package com.wecharttest.handler.text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wecharttest.util.DBConnection;

public class MenuCheckHandler {
  
  
  
  public String getMenu(){
    StringBuffer sb = new StringBuffer();
    Connection conn = null;
    PreparedStatement ps = null;
    try {
    conn = DBConnection.getConnection();
    ps = conn.prepareStatement(getSql());
    ResultSet rs = ps.executeQuery();
    int count = 1; 
    while(rs.next()){
      sb.append(count);
      sb.append(" ");
      sb.append(rs.getString(1));
      sb.append("\n");
      sb.append("菜品描述：");
      sb.append(rs.getString(2));
      sb.append("\n");
      count++;
    }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally{
    	DBConnection.closeConnection(ps, conn);
    }
    return sb.toString();
    
  }
  
  private String getSql(){
    String sql = "select * from menu order by dish_name";
    return sql;
  }
}
