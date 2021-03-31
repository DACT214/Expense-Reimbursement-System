package com.revature.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.DAO.RequestDAO;
import com.revature.DAO.RequestDAOUtil;
import com.revature.model.Request;
import com.revature.model.User;
import com.revature.utilities.ConnectionUtil;

public class RequestService {
	RequestDAO requestDAO;
	
	public RequestService() {
		this.requestDAO = new RequestDAOUtil();
	}
	
	public ArrayList<Request> getEmpReq(String username) throws SQLException {
		try (Connection con = ConnectionUtil.getConnection()){
			ArrayList<Request> request;
			
			request = requestDAO.empReq(username, con);
			
			if (request == null) {
				System.out.println("request were not found");

			}
			
			return request;
		}
	}
	public void newEmpReq(String username, double ammount) throws SQLException{
		try(Connection con = ConnectionUtil.getConnection()){
			requestDAO.newEmpReq(username, ammount, con);
			
		}
	}
	
	
	public User updatePro(int ID, String username, String firstName, String lastName) throws SQLException{
		try(Connection con = ConnectionUtil.getConnection()){
			User employee = requestDAO.updatePro(ID, username, firstName, lastName, con);
			
			return employee;
		}
	}
}
