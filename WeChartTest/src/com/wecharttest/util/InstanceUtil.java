package com.wecharttest.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstanceUtil {

	public static Map<String,String> instanceMap = new HashMap<String,String>();
	
	public static Map<String,List<String>> accessTokenMap = new HashMap<String,List<String>>();
	
	static{
		initInstanceMap();
		initAccessTokenMap();
	}
	
	
	private static void initInstanceMap(){
		String sql = "select * from administrative.instancemap";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		conn = DBConnection.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				instanceMap.put(rs.getString(1), rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private static void initAccessTokenMap(){
		String sql = "select * from administrative.instanceaccesstoken";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		conn = DBConnection.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				List<String> accessToken = new ArrayList<String>();
				accessTokenMap.put(rs.getString(1), accessToken);
				accessToken.add(rs.getString(2));
				accessToken.add(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
