package com.revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.model.Request;
import com.revature.model.User;

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

	@Override
	public User updatePro(int ID, String username, String firstName, String lastName, Connection con) throws SQLException {
		//========================================== GRABIING OLD DATA ===============================================
		String sql = "Select username From ers_db.accounts WHERE account_id = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, ID);
		
		ResultSet rs = pstmt.executeQuery();
		String oldUser = "";
		
		if(rs.next()){
			oldUser = rs.getString("username");
		}
		
		//======================================= UPDATE PROFILE METHOD =================================================
		String sql2 = "UPDATE ers_db.accounts SET username = ?, first_name=?, last_name=? WHERE account_id = ?;";
		
		PreparedStatement pstmt2= con.prepareStatement(sql2);
		
		pstmt2.setString(1, username);
		pstmt2.setString(2, firstName);
		pstmt2.setString(3, lastName);
		pstmt2.setInt(4, ID);
		
		pstmt2.executeUpdate();
		//======================================== UPDATE REQUEST TABLE DATA ====================================
		
		String sql3 = "UPDATE ers_db.requests SET username = ?, first_name=?, last_name=? WHERE username= ?";
		PreparedStatement pstmt3= con.prepareStatement(sql3);
		
		pstmt3.setString(1, username);
		pstmt3.setString(2, firstName);
		pstmt3.setString(3, lastName);
		pstmt3.setString(4, oldUser);
		
		pstmt3.executeUpdate();
		//========================================= GRABBING NEW DATA =====================================
		String sql4 = "Select * From ers_db.accounts WHERE account_id = ?";
		PreparedStatement pstmt4=con.prepareStatement(sql4);
		
		pstmt4.setInt(1, ID);
		
		ResultSet rs2 = pstmt4.executeQuery();
		User updatedUser = null;
		if(rs.next()) {
			int id = ID;
			String usrN = rs.getString("username");
			String first = rs.getString("first_name");
			String last = rs.getString("last_name");
			String pw = rs.getString("password");
			String pos = rs.getString("position");
		
			updatedUser = new User(id, pos, usrN, pw, first, last);
		}
		
		return updatedUser;
		
	}
	
	
	

}
