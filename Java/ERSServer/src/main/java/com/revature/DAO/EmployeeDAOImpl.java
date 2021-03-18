package com.revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.model.Employee;

public class EmployeeDAOImpl implements EmployeeDAO{

	@Override
	public Employee getLogin(String position, String username, String password, Connection con) throws SQLException {
		Employee user = null;
		
		String sql = "SELECT * FROM ERS_DB.accounts WHERE position = ? AND username = ? AND \"password\" = ?";
		
		PreparedStatement ptsmt = con.prepareStatement(sql);
		
		ptsmt.setString(1, position);
		ptsmt.setString(2, username);
		ptsmt.setString(3, password);
		
		ResultSet rs = ptsmt.executeQuery();
		
		if (rs.next()) {
			int id = rs.getInt("id");
			//postion
			//username
			//password
			String first = rs.getString("first_name");
			String last = rs.getString("last_name");
			
			user = new Employee( id,  position,  username,  password,  first,  last);
		}
		
		return user;
	}
}
