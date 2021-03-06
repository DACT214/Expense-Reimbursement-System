package com.revature.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.DAO.UserDAO;
import com.revature.DAO.UserDAOImpl;
import com.revature.exceptions.UserNotFoundException;
import com.revature.model.Request;
import com.revature.model.User;
import com.revature.utilities.ConnectionUtil;

public class UserService {
	UserDAO userDAO;

	public UserService() {
		this.userDAO = new UserDAOImpl();
	}

	public User getLogin(String postion, String username, String password)
			throws UserNotFoundException, SQLException {
		try (Connection con = ConnectionUtil.getConnection()) {
			User user;

			user = userDAO.getLogin(postion, username, password, con);

			if (user == null) {
				System.out.println(new UserNotFoundException("username or password do not match or does not exist"));

			}

			return user;
		}
	}

	public User getUser(String username) throws UserNotFoundException, SQLException{
		try (Connection con = ConnectionUtil.getConnection()){
			User user;
			
			user = userDAO.getUser(username, con);
			
			if (user == null) {
				System.out.println(new UserNotFoundException("user not found error"));
			}
			return user;
		}
		
	}
	
	public ArrayList<Request> getAllReq(String username, String statusIn) throws SQLException {
		try (Connection con = ConnectionUtil.getConnection()){
			ArrayList<Request> request;
			
			request = userDAO.getAllReq(username, statusIn, con);
			
			if (request == null) {
				System.out.println("request were not found");

			}
			
			return request;
		}
	}
}
