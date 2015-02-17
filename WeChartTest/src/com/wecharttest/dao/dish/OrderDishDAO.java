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

	public void addOrderDish(String instance, String userName,
			List<DishBean> dbs) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int orderId = 0;
		// Add order dish info
		String addOrderDishInfoSql = getAddOrderDishInfoSql(instance);
		try {
			ps = conn.prepareStatement(addOrderDishInfoSql);

			ps.setString(1, userName);
			ps.setBoolean(2, false);
			ps.setLong(3, System.currentTimeMillis());
			ps.setInt(4, 0);
			ps.executeUpdate();

			// Get added order id

			String getMaxOrderIdSql = getMaxOrderId(instance);
			ps = conn.prepareStatement(getMaxOrderIdSql);
			ps.setString(1, userName);
			rs = ps.executeQuery();
			if (rs.next()) {
				orderId = rs.getInt(1);
				orderId++;
			}

		} catch (SQLException e) {
			log.error("Add order dish info db error", e);
			e.printStackTrace();
		}
		addOrderDishDetail(instance, orderId, dbs);

	}

	public void addOrderDishDetail(String instance, int orderId,
			List<DishBean> dbs) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = null;

		try {
			String sql = getAddOrderDishDetailSql(instance);
			ps = conn.prepareStatement(sql);
			for (DishBean db : dbs) {
				ps.setInt(1, orderId);
				ps.setString(2, db.getDishName());
				ps.setInt(3, db.getCount());
				ps.setLong(4, System.currentTimeMillis());
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (SQLException e) {
			log.error("Add order dish detail db error", e);
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(ps, conn);
		}
	}

	public Map<String, Integer> getAllOrderedAndNoCheckDish(String instance,
			String userName) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, Integer> dishNameList = new HashMap<String, Integer>();
		try {
			String sql = getAllOrderedAndNoCheckDishSql(instance);
			ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			rs = ps.executeQuery();
			while (rs.next()) {
				dishNameList.put(rs.getString(1), rs.getInt(2));
			}
		} catch (SQLException e) {
			log.error("Get uncheck dish according to user error", e);
			e.printStackTrace();
		}
		return dishNameList;
	}

	public Map<String, Integer> getAllCheckedDish(String instance,
			String userName) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, Integer> dishNameList = new HashMap<String, Integer>();
		try {
			String sql = getAllCheckDishSql(instance);
			ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			rs = ps.executeQuery();
			while (rs.next()) {
				dishNameList.put(rs.getString(1), rs.getInt(2));
			}
		} catch (SQLException e) {
			log.error("Get checked dish according to user error", e);
			e.printStackTrace();
		}
		return dishNameList;
	}

	private String getAddOrderDishDetailSql(String instance) {
		return "insert into " + instance + ".dishorder values(?,?,?,?)";
	}

	private String getAddOrderDishInfoSql(String instance) {
		return "insert into "
				+ instance
				+ ".orderinfo"
				+ "(user_name,is_checked,check_datetime,total_price) values(?,?,?,?)";
	}

	private String getAllOrderedAndNoCheckDishSql(String instance) {
		return "select dish_name,dish_count from " + instance
				+ ".dishorder where user_name = ? and is_check = false";
	}

	private String getMaxOrderId(String instance) {
		return "select max(order_id) from " + instance
				+ ".orderinfo where user_name = ?";
	}

	private String getAllCheckDishSql(String instance) {
		StringBuffer sb = new StringBuffer();
		sb.append("select do.dish_name, do.dish_count from ");
		sb.append(instance);
		sb.append(".dishorder do,");
		sb.append(instance);
		sb.append(".orderinfo oi where ");
		sb.append("do.order_id = oi.order_id and oi.user_name = ?");
		return sb.toString();

	}
}
