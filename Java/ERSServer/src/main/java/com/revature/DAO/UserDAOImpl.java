package com.revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.model.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public User getLogin(String position, String username, String password, Connection con) throws SQLException {
		User user = null;

		String sql = "SELECT * FROM ers_db.accounts WHERE position = ? AND username = ? AND \"password\" = ?";

		PreparedStatement ptsmt = con.prepareStatement(sql);

		ptsmt.setString(1, position);
		ptsmt.setString(2, username);
		ptsmt.setString(3, password);

		ResultSet rs = ptsmt.executeQuery();

		if (rs.next()) {
			int id = rs.getInt("account_id");
			// postion
			// username
			// password
			String first = rs.getString("first_name");
			String last = rs.getString("last_name");

			user = new User(id, position, username, password, first, last);
		}

		return user;
	}

	@Override
	public User getUser(String username, Connection con) throws SQLException {
		User user = null;

		String sql = "SELECT * FROM ers_db.accounts WHERE username = ?";

		PreparedStatement ptsmt = con.prepareStatement(sql);

		ptsmt.setString(1, username);
		

		ResultSet rs = ptsmt.executeQuery();

		if (rs.next()) {
			int id = rs.getInt("account_id");
			String position = rs.getString("position");
			// username
			String password = rs.getString("password");
			String first = rs.getString("first_name");
			String last = rs.getString("last_name");

			user = new User(id, position, username, password, first, last);
		}

		return user;
		
	}
}
