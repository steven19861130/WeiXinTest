package com.wecharttest.handler.text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Locale;
import java.util.Map;

import com.wecharttest.util.DBConnection;
import com.wecharttest.util.*;

public class OrderDishHandler {
  
  public String order(String userName, String type,
      Map<String, Integer> orderDishes) {
    StringBuffer sb = new StringBuffer();
    String sql = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection conn = DBConnection.getConnection();
    try {
      if (type.equalsIgnoreCase("点菜结束")) {
        sql = getOrderedDishsSql(userName);
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        if(!rs.next()){
          return Constants.NOTORDERMSG;
        }
        sb.append("您点的菜有：\n");
        do{
          sb.append(rs.getString(1));
          sb.append("   ");
          sb.append(rs.getInt(2));
          sb.append("份\n");
          if (rs.isLast()) {
            sb.append(longToTime(rs.getLong(3)));
          }
        }while (rs.next());
      } else {
        sql = orderDishesSql();
        for (String dish : orderDishes.keySet()) {
          ps = conn.prepareStatement(sql);
          ps.setString(1, userName);
          ps.setString(2, dish);
          ps.setInt(3, orderDishes.get(dish));
          ps.setLong(4, Calendar.getInstance().getTimeInMillis());
          ps.executeUpdate();
        }
        
      }
    } catch (SQLException e) {
    	e.printStackTrace();
      if(e.getMessage().contains("foreign key constraint")){
        return Constants.NOEXISTDISH+Constants.INPUTERR+Constants.ORDERMSG;
      }
    }finally{
      try {
        if (rs != null && !rs.isClosed()) {
          rs.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      DBConnection.closeConnection(ps, conn);
    }
    return constructReturn(type,sb);
  }
  
  private String getOrderedDishsSql(String userName) {
    StringBuffer sql = new StringBuffer(
        "select dish_name, dish_count, order_date from dishorder where user_name ='");
    sql.append(userName);
    sql.append("' ");
    sql.append("and order_date > ");
    sql.append(getOrderExpireDate());
    sql.append(" order by order_date");
    return sql.toString();
  }
  
  private String orderDishesSql() {
    StringBuffer sql = new StringBuffer("insert into dishorder values(?,?,?,?)");
    return sql.toString();
  }
  
  private long getOrderExpireDate() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, -1);
    return calendar.getTimeInMillis();
  }
  
  private String longToTime(long time) {
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(time);
    Formatter ft = new Formatter(Locale.CHINA);
    return ft.format("%1$tY年%1$tm月%1$td日%1$tA，%1$tT", cal).toString();
  }
  
  private String constructReturn(String type, StringBuffer result){
    if(type.equals("点菜结束")){
      return result.toString();
    }else{
      return Constants.ORDERFINISHMSG;
    }
  }
  
}
