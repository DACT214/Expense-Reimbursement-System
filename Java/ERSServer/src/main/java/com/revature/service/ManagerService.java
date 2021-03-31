package com.revature.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.DAO.ManagerDAO;
import com.revature.DAO.ManagerDAOImpl;
import com.revature.model.Request;
import com.revature.utilities.ConnectionUtil;

public class ManagerService {
	
	ManagerDAO managerDAO;
	
	public ManagerService() {
		this.managerDAO = new ManagerDAOImpl();
	}
	

	public ArrayList<Request> getAllReq(String statusIn) throws SQLException {
		try (Connection con = ConnectionUtil.getConnection()){
			ArrayList<Request> request;
			
			request = managerDAO.getAllReq(statusIn, con);
			
			if (request == null) {
				System.out.println("request were not found");

			}
			
			return request;
		}
	}
	
	public ArrayList<Request> getSearchedReq(String firstName, String lastName) throws SQLException {
		try (Connection con = ConnectionUtil.getConnection()){
			ArrayList<Request> request;
			
			request = managerDAO.getSearchedReq(firstName, lastName, con);
			
			if (request == null) {
				System.out.println("request were not found");

			}
			
			return request;
		}
	}
	
	public void acceptOrDeny(String status, int reqID) throws SQLException{
		try(Connection con = ConnectionUtil.getConnection()){
			managerDAO.acceptOrDeny(status, reqID, con);
			
		}
	}
	
}
