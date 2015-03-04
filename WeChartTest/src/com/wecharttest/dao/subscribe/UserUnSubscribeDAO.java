package com.wecharttest.dao.subscribe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.wecharttest.util.DBConnection;

public class UserUnSubscribeDAO {

	public String userUnSubscribe(String instance, String userName) {
		String retMsg = "";

		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(userUnSubscribeSql(instance));
			ps.setString(1, userName);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.closeConnection(ps, conn);
		}

		return retMsg;

	}

	private String userUnSubscribeSql(String instance) {
		String sql = "update "+instance+".userinfo set Subscribed=false where user_name =?";
		return sql;
	}
}
