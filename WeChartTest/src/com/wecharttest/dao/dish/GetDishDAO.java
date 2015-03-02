package com.wecharttest.dao.dish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.wecharttest.bean.DishBean;
import com.wecharttest.util.DBConnection;

public class GetDishDAO {

	private Logger log = Logger.getLogger(GetDishDAO.class);
	
	public List<DishBean> getAllDish(String instance){
		Connection conn = DBConnection.getConnection();	
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(getAllDishSql(instance));
			rs = ps.executeQuery();		
		} catch (SQLException e) {
			log.error("Get All dish db error", e);
			e.printStackTrace();
		}finally{
			DBConnection.closeConnection(ps, conn);
		}
		return constructDishBeanList(rs);
		
		
		}
		
		public List<DishBean> getDishes(String instance,Set<String> dishes){
			Connection conn = DBConnection.getConnection();	
			PreparedStatement ps = null;
			ResultSet rs = null;
			int count = 1;
			try {
				ps = conn.prepareStatement(getDishesSql(instance,dishes.size()));
				for(String dish:dishes){
					ps.setString(count,dish);
					count++;
				}
				rs = ps.executeQuery();		
			} catch (SQLException e) {
				log.error("Get dish db error", e);
				e.printStackTrace();
			}finally{
				DBConnection.closeConnection(ps,conn,rs);
			}
			return constructDishBeanList(rs);
			
		}
		
		
		private String getAllDishSql(String instance){
			String sql = "select * from "+instance+".menu order by dish_name";
			return sql;
		}
		
	private String getDishesSql(String instance, int count) {

		StringBuffer sql = new StringBuffer("select * from " + instance
				+ ".menu where ");
		for (int i = 0; i< count;i++) {
			sql.append("dish_name = ? ");
			if (i == (count -1)) {
				break;
			}
			sql.append("or ");
		}
		return sql.toString();
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
