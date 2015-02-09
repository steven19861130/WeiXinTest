package com.wecharttest.handler.client.dish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wecharttest.bean.DishBean;
import com.wecharttest.util.DBConnection;

public class GetDishHandler {

	
	public List<DishBean> getAllDish(String instance){
	Connection conn = DBConnection.getConnection();	
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		ps = conn.prepareStatement(getAllDishSql(instance));
		rs = ps.executeQuery();		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		DBConnection.closeConnection(ps, conn);
	}
	return constructDishBeanList(rs);
	
	
	}
	
	public DishBean getOneDish(String instance){
		Connection conn = DBConnection.getConnection();	
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(getOneDishSql(instance));
			rs = ps.executeQuery();		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.closeConnection(ps, conn);
		}
		return constructDishBean(rs);
		
	}
	
	
	private String getAllDishSql(String instance){
		String sql = "select * from "+instance+".menu order by dish_name";
		return sql;
	}
	
	private String getOneDishSql(String instance){
		String sql = "select * from "+instance+".menu where dish_name =?";
		return sql;
	}
	
	private List<DishBean> constructDishBeanList(ResultSet rs){
		List<DishBean> dbs = new ArrayList<DishBean>();
		if(rs == null){
			return null;
		}
		try {
			while(rs.next()){
				DishBean db = new DishBean();
				db.setDishName(rs.getString(1));
				db.setDishDesc(rs.getString(2));
				db.setPicture(rs.getString(3));
				db.setPrice(rs.getInt(4));
				dbs.add(db);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dbs;
	}
	
	private DishBean constructDishBean(ResultSet rs){
		DishBean db = new DishBean();
		if(rs == null){
			return null;
		}
		try {
			while(rs.next()){
				db.setDishName(rs.getString(1));
				db.setDishDesc(rs.getString(2));
				db.setPicture(rs.getString(3));
				db.setPrice(rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return db;
	}
	
}
