package com.revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.model.Request;

public class RequestDAOUtil implements RequestDAO{

	@Override
	public ArrayList<Request> empReq(String username, Connection con) throws SQLException {
		String sql = "SELECT * FROM ers_db.requests WHERE username=?";

		PreparedStatement ptsmt = con.prepareStatement(sql);

		ptsmt.setString(1, username);
		
		ResultSet rs = ptsmt.executeQuery();

		ArrayList<Request> info= new ArrayList<Request>();
		Request infoItem = null;
		while (rs.next()) {
			int id = rs.getInt("request_id");
			//username
			String fName= rs.getString("first_name");
			String lName= rs.getString("last_name");
			double ra= rs.getDouble("request_ammount");
			String status= rs.getString("status");
			
			infoItem = new Request(id, username, fName, lName, ra, status);
			info.add(infoItem);
		}
		
		return info;
	}

	@Override
	public void newEmpReq(String username, double ammount, Connection con) throws SQLException {
		String sql = "INSERT INTO ers_db.requests("
				+ "				username,"
				+ "				first_name,"
				+ "				last_name,"
				+ "				request_ammount)"
				+ "				VALUES("
				+ "				?,"
				+ "				(SELECT first_name FROM ers_db.accounts WHERE username=?),"
				+ "				(SELECT last_name FROM ers_db.accounts WHERE username=?),"
				+ "				?"
				+ "				)";
		
		PreparedStatement pstmt= con.prepareStatement(sql);
		
		pstmt.setString(1, username);
		pstmt.setString(2, username);
		pstmt.setString(3, username);
		pstmt.setDouble(4, ammount);
		
		pstmt.executeUpdate();
		
	}
	
	

}
