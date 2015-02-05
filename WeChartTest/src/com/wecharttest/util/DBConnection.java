package com.wecharttest.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {
  
  private static String URL = "jdbc:mysql://rdsifqu3mifqu3m.mysql.rds.aliyuncs.com:3306/weixindb";
  private static String USER = "steven";
  private static String PASSWORD = "Welcome123";
    
  public static Connection getConnection(){
    Connection conn = null;
    try {
    Class.forName("com.mysql.jdbc.Driver");
    conn = DriverManager.getConnection(URL,USER,PASSWORD);
    }catch(SQLException e) {
    System.out.println("链接数据库发生异常!");
    e.printStackTrace();
    }catch(ClassNotFoundException e) {
      System.out.println("装载驱动包出现异常!请查正！");
      e.printStackTrace();
      }
    return conn;
  }
  
  public static void closeConnection(PreparedStatement ps, Connection conn){
    try {
      if(conn != null && !conn.isClosed()){
        conn.close();
      }
      if(ps != null && ps.isClosed()){
        ps.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
}
