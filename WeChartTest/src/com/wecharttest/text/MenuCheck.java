package com.wecharttest.text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wecharttest.util.DBConnection;

public class MenuCheck {
  
  
  
  public String getMenu(){
    StringBuffer sb = new StringBuffer();
    try {
    Connection conn = DBConnection.getConnection();
    PreparedStatement ps = conn.prepareCall(getSql());
    ResultSet rs = ps.executeQuery();
    int count = 1; 
    while(rs.next()){
      sb.append(count);
      sb.append(" ");
      sb.append(rs.getString(1));
      sb.append("\n");
      sb.append("菜品描述：");
      sb.append(rs.getString(2));
    }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return sb.toString();
    
  }
  
  private String getSql(){
    String sql = "select * from menu order by dish_name";
    return sql;
  }
}
