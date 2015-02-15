package com.wecharttest.dao.dish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wecharttest.bean.DishBean;
import com.wecharttest.util.DBConnection;

public class OrderDishDAO {

	private Logger log = Logger.getLogger(OrderDishDAO.class);
	
	public void addOrderDish(String instance,String userName ,List<DishBean> dbs){
		Connection conn = DBConnection.getConnection();	
		PreparedStatement ps = null;
		long dateTime = System.currentTimeMillis();
		try {
			String sql = getAddOrderDishSql(instance);
			ps = conn.prepareStatement(sql);
			for(DishBean db : dbs){
			ps.setString(1, userName);
			ps.setString(2, db.getDishName());
			ps.setInt(3, db.getCount());
			ps.setLong(4, dateTime);
			ps.setBoolean(5, new Boolean(false));
			ps.addBatch();
		  } 
			ps.executeBatch();
		}
		catch (SQLException e) {
			log.error("Add order dish db error", e);
			e.printStackTrace();
		}finally{
			DBConnection.closeConnection(ps, conn);
		}
	}
	
	public Map<String,Integer> getAllOrderedAndNoCheckDish(String instance, String userName){
		Connection conn = DBConnection.getConnection();	
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String,Integer> dishNameList = new HashMap<String,Integer>();
		try {
		String sql = getAllOrderedAndNoCheckDishSql(instance);
	    ps = conn.prepareStatement(sql);
	    ps.setString(1, userName);
	    rs = ps.executeQuery();
	    while(rs.next()){
	    	dishNameList.put(rs.getString(1),rs.getInt(2));
	    }
		} catch (SQLException e) {
			log.error("Get uncheck dish according to user error", e);
			e.printStackTrace();
		}
		return dishNameList;		
	}
	
	private String getAddOrderDishSql(String instance){
		return "insert into "+instance+".dishorder values(?,?,?,?)";
		}
	
	private String getAllOrderedAndNoCheckDishSql(String instance){
		return "select dishname,count from "+instance+".dishorder where user_name = ? and ischeck = false";
		}
	}

