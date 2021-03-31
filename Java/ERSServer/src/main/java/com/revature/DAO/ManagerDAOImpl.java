package com.revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.model.Request;

public class ManagerDAOImpl implements ManagerDAO{

	@Override
	public ArrayList<Request> getAllReq(String statusIn, Connection con) throws SQLException {
		String sql =null;
		PreparedStatement ptsmt;
		switch(statusIn) {
		case "pending":
		 sql = "SELECT * FROM ers_db.requests WHERE status = ?";
		 ptsmt = con.prepareStatement(sql);
		 ptsmt.setString(1, statusIn);
		 break;
		case "accepted":
			sql = "SELECT * FROM ers_db.requests WHERE status = ?";
			 ptsmt = con.prepareStatement(sql);
			 ptsmt.setString(1, statusIn);
			 break;
		default:
			sql = "SELECT * FROM ers_db.requests";
			ptsmt = con.prepareStatement(sql);
			break;
		}
		
		
		ResultSet rs = ptsmt.executeQuery();
		ArrayList<Request> req = new ArrayList<Request>();
		Request reqItem=null;
		
		while(rs.next()) {
			int id = rs.getInt("request_id");
			String username = rs.getString("username");
			String fName= rs.getString("first_name");
			String lName= rs.getString("last_name");
			double ra= rs.getDouble("request_ammount");
			String status= rs.getString("status");
			
			reqItem = new Request(id, username, fName, lName, ra, status);
			req.add(reqItem);
		}
		return req;
	}

	@Override
	public ArrayList<Request> getSearchedReq(String firstName, String lastName, Connection con) throws SQLException {
			
			String sql = "SELECT * FROM ers_db.requests WHERE first_name=? AND last_name=?";
			PreparedStatement ptsmt = con.prepareStatement(sql);
			ptsmt.setString(1, firstName);
			ptsmt.setString(2, lastName);
		
		
		ResultSet rs = ptsmt.executeQuery();
		ArrayList<Request> req = new ArrayList<Request>();
		Request reqItem=null;
		
		while(rs.next()) {
			int id = rs.getInt("request_id");
			String username = rs.getString("username");
			String fName= rs.getString("first_name");
			String lName= rs.getString("last_name");
			double ra= rs.getDouble("request_ammount");
			String status= rs.getString("status");
			
			reqItem = new Request(id, username, fName, lName, ra, status);
			req.add(reqItem);
		}
		return req;
	}

	@Override
	public void acceptOrDeny(String status, int reqID, Connection con) throws SQLException {
		String sql ="";
		
		switch(status) {
		case "accepted":
		sql = "UPDATE ers_db.requests SET status = 'accepted' WHERE request_id = ?;";
		break;
		case "deny":
		sql = "DELETE FROM ers_db.requests WHERE request_id = ?";
		break;
		default:
		break;
		}
		PreparedStatement pstmt= con.prepareStatement(sql);
		
		pstmt.setInt(1, reqID);
		
		pstmt.executeUpdate();
		
	}

}
