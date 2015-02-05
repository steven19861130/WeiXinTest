package com.wecharttest.text;

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

public class OrderDish {
  
  public String order(String userName, String type,
      Map<String, Integer> orderDishes) {
    StringBuffer sb = new StringBuffer();
    String sql = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection conn = DBConnection.getConnection();
    try {
      if (type.equalsIgnoreCase("结束结束")) {
        sql = getOrderedDishsSql(userName);
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        if(rs.isAfterLast()){
          return "您还没有点菜，请参考菜单点菜";
        }
        sb.append("您点的菜有：\n");
        while (rs.next()) {
          sb.append(rs.getString(1));
          sb.append("   ");
          sb.append(rs.getInt(2));
          sb.append("份\n");
          if (rs.isLast()) {
            sb.append(longToTime(rs.getLong(3)));
          }
        }
      } else {
        sql = orderDishesSql();
        for (String dish : orderDishes.keySet()) {
          ps = conn.prepareStatement(sql);
          ps.setString(1, userName);
          ps.setString(2, dish);
          ps.setInt(3, orderDishes.get(dish));
          ps.setLong(4, Calendar.getInstance().getTimeInMillis());
          ps.executeUpdate();
          conn.commit();
        }
        
      }
    } catch (SQLException e) {
      if(e.getMessage().contains("foreign key constraint")){
        return "您输入点菜信息无法识别，可能因为：" +
        		"\n1.您输入的菜品不存在，请查看菜单。\n2." +
        		"您输入的格式有问题，请按照'我要吃：鱼香肉丝1,青椒肉丝2，番茄鸡蛋汤1'格式发送消息,其中数字代表份数";
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
    sql.append("where order_date > ");
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
    return ft.format("%1$tY年%1$tm月%1$td日%1$tA，%1$tT %1$tp", cal).toString();
  }
  
  private String constructReturn(String type, StringBuffer result){
    if(type.equals("结束")){
      return result.toString();
    }else{
      return "您还需要点菜吗？如果是，请继续输入菜品\n 如果您点菜结束，请输入“结束”完成本次点菜";
    }
  }
  
}
