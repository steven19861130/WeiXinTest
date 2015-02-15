package com.wecharttest.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    System.out.println("DB Connection exception");
    e.printStackTrace();
    }catch(ClassNotFoundException e) {
      System.out.println("DB driver exception");
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
  
  public static void closeConnection(PreparedStatement ps, Connection conn,ResultSet rs){
	    try {
	      if(conn != null && !conn.isClosed()){
	        conn.close();
	      }
	      if(ps != null && ps.isClosed()){
	        ps.close();
	      }
	      if(rs != null && rs.isClosed()){
	    	  rs.close();
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }
  
}
