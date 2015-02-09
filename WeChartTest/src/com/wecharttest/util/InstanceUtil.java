package com.wecharttest.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class InstanceUtil {

	public static Map<String,String> instanceMap = new HashMap<String,String>();
	
	static{
		initInstanceMap();
	}
	
	
	private static void initInstanceMap(){
		String sql = "select * from administrative.instance_map";
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
}
